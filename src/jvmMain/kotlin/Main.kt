import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import presentation.App
import presentation.MainViewModel

fun main() = application {
    val windowState = rememberWindowState(size = DpSize(800.dp, 600.dp))

    Window(
        onCloseRequest = ::exitApplication,
        resizable = false,
        state = windowState,
        title = "RIPBG"
    ) {
        val client = HttpClient(CIO)

        val mainVMScope = CoroutineScope(Dispatchers.Default)
        val mainViewModel = MainViewModel(client, mainVMScope)
        val mainState by mainViewModel.state.collectAsState(context = mainVMScope.coroutineContext)

        MaterialTheme { App(mainState, mainViewModel::onEvent) }
    }
}
