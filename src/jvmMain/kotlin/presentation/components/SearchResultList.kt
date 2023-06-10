package presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.Torrent

@Composable
fun SearchResultList(
    isVisible: Boolean,
    torrents: List<Torrent>,
    searchInput: String,
) {
    val lazyListState = LazyListState()

    val filteredList = torrents.filter { torrent -> torrent.title.contains(searchInput) }

    Row {
        AnimatedVisibility(
            isVisible,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight * 2 }
            ),
            exit = fadeOut()
        ) {
            LazyColumn(
                Modifier.fillMaxWidth(fraction = 0.99f),
                state = lazyListState,
                contentPadding = PaddingValues(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(filteredList) { torrent -> torrent.toTorrentEntry() }
            }
        }

        AnimatedVisibility(
            isVisible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            VerticalScrollbar(
                modifier = Modifier.fillMaxHeight(),
                adapter = rememberScrollbarAdapter(lazyListState)
            )
        }
    }
}