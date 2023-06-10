package presentation

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import domain.model.RepoState.Loaded
import presentation.components.SearchResultList
import presentation.components.TopBar

@Composable
@Preview
fun App(mainState: MainState, onEvent: (MainEvent) -> Unit) {
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = { TopBar(mainState.searchInput, mainState.repoState, onEvent) }
    ) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
            SearchResultList(
                isVisible = mainState.repoState is Loaded,
                torrents = mainState.movies + mainState.shows,
                searchInput = mainState.searchInput
            )
        }
    }
}
