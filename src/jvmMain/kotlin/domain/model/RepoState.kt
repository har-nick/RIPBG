package domain.model

sealed interface RepoState {
    object Missing : RepoState
    object Loading : RepoState
    object Loaded : RepoState
    data class Error(val exception: Throwable) : RepoState
}