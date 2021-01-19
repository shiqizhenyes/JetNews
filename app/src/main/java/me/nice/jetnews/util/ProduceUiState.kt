package me.nice.jetnews.util

import androidx.compose.runtime.State

class ProduceUiState {
}

data class ProducerResult<T>(
        val result: State<T>,
        val onRefresh: () -> Unit,
        val onClearError: () -> Unit
)