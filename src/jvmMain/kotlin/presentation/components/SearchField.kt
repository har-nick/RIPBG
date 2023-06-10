package presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import presentation.MainEvent
import presentation.MainEvent.UpdateSearchInput

@Composable
fun SearchField(enabled: Boolean, inputValue: String, onEvent: (MainEvent) -> Unit) {
    TextField(
        value = inputValue,
        onValueChange = { newInput -> onEvent(UpdateSearchInput(newInput)) },
        modifier = Modifier.fillMaxWidth(0.6f),
        enabled = enabled,
        singleLine = true,
        placeholder = { Text("Enter query...") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
            focusedBorderColor = MaterialTheme.colorScheme.primary
        )
    )
}