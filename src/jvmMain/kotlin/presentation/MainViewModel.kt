package presentation

import androidx.compose.material.SnackbarDuration
import domain.model.RepoState.*
import domain.model.Torrent
import domain.model.toTorrent
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import presentation.MainEvent.StartDownload
import presentation.MainEvent.UpdateSearchInput

class MainViewModel(private val client: HttpClient, private val vmScope: CoroutineScope) {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        loadSources()
    }

    private companion object {
        const val SOURCE_URL = "https://raw.githubusercontent.com/2004content/rarbg/main/sorted/-rarbg."
    }

    private val movieSourceUrls = listOf(
        SOURCE_URL + "720p.movies.txt",
        SOURCE_URL + "264.1080p.movies.txt",
        SOURCE_URL + "x265.bluray.1080p.movies.txt",
        SOURCE_URL + "x265.webrip.1080p.movies.txt"
    )

    private val showSourceUrls = listOf(
        SOURCE_URL + "720p.shows.txt",
        SOURCE_URL + "264.1080p.shows.txt",
        SOURCE_URL + "x265.bluray.1080p.shows.txt",
        SOURCE_URL + "x265.webrip.1080p.shows.txt"
    )

    fun onEvent(event: MainEvent) {
        when (event) {
            StartDownload -> loadSources()
            is UpdateSearchInput -> {  _state.value = state.value.copy(searchInput = event.newInput) }
        }
    }

    private fun showSnackbar(message: String, actionLabel: String) {
        vmScope.launch {
            state.value.snackbarState.showSnackbar(message, actionLabel, SnackbarDuration.Short)
        }
    }

    private fun loadSources() {
        _state.value = state.value.copy(repoState = Loading)

        vmScope.launch {
            try {
                val movieResponses = movieSourceUrls.map { url ->
                    async { fetchSource(url) }
                }.awaitAll().flatten()

                val showResponses = showSourceUrls.map { url ->
                    async { fetchSource(url) }
                }.awaitAll().flatten()

                _state.value = state.value.copy(
                    movies = movieResponses,
                    shows = showResponses,
                    repoState = Loaded
                )

            } catch (e: Exception) {
                println(e.stackTrace)
                _state.value = state.value.copy(repoState = Error(e))
            }
        }
    }

    private suspend fun fetchSource(sourceUrl: String): List<Torrent> {
        val response = client.get(sourceUrl).bodyAsText()
        return response.split("\\R".toRegex()).map { it.toTorrent() }
    }
}