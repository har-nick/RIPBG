package presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import domain.model.RepoState
import presentation.MainEvent

@Composable
fun DownloadButton(sourceState: RepoState, onEvent: (MainEvent) -> Unit) {
    IconButton(
        onClick = remember { { onEvent(MainEvent.StartDownload) } },
        enabled = sourceState !is RepoState.Loading,
        content = {
            Box(
                Modifier
                    .clip(CircleShape)
                    .aspectRatio(1f)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (sourceState is RepoState.Loading) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
                } else {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Download Sources",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }
    )
}