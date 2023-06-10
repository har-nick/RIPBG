package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import domain.model.Torrent
import java.awt.Desktop
import java.net.URI

private fun openInBrowser(uri: URI) {
    val osName by lazy(LazyThreadSafetyMode.NONE) { System.getProperty("os.name").lowercase() }
    val desktop = Desktop.getDesktop()
    when {
        Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE) -> desktop.browse(uri)
        "mac" in osName -> Runtime.getRuntime().exec("open $uri")
        "nix" in osName || "nux" in osName -> Runtime.getRuntime().exec("xdg-open $uri")
        else -> throw RuntimeException("cannot open $uri")
    }
}

@Composable
fun Torrent.toTorrentEntry() {
    Column(
        Modifier
            .clip(MaterialTheme.shapes.large)
            .clickable { openInBrowser(URI(magnet)) }
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(all = 20.dp)
    ) {
        Text(title, color = MaterialTheme.colorScheme.onPrimaryContainer)
    }
}