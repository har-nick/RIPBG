package presentation

sealed interface MainEvent {
    object StartDownload : MainEvent
    data class UpdateSearchInput(val newInput: String) : MainEvent
}