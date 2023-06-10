package presentation

import androidx.compose.material.SnackbarHostState
import domain.model.RepoState
import domain.model.RepoState.Missing
import domain.model.Torrent

data class MainState(
    val movies: List<Torrent> = emptyList(),
    val searchInput: String = "",
    val shows: List<Torrent> = emptyList(),
    val snackbarState: SnackbarHostState = SnackbarHostState(),
    val repoState: RepoState = Missing
)
