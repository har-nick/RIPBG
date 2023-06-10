package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.RepoState
import presentation.MainEvent

@Composable
fun TopBar(
    searchInput: String,
    sourceState: RepoState,
    onEvent: (MainEvent) -> Unit
) {
    Row(
        Modifier
            .height(65.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SearchField(
            enabled = sourceState is RepoState.Loaded,
            inputValue = searchInput,
            onEvent
        )

        DownloadButton(sourceState, onEvent = onEvent)
    }
}