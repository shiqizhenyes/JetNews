package me.nice.jetnews.util

import androidx.compose.runtime.*
import kotlinx.coroutines.channels.Channel
import me.nice.jetnews.ui.state.UiState
import me.nice.jetnews.ui.state.copyWithResult
import me.nice.jetnews.data.Result


data class ProducerResult<T>(
    val result: State<T>,
    val onRefresh: () -> Unit,
    val onClearError: () -> Unit
)

@Composable
fun <Producer, T> launchUiStateProducer(
    producer: Producer,
    block: suspend Producer.() -> Result<T>
): ProducerResult<UiState<T>> = launchUiStateProducer(producer = producer,
    Unit, block = block)


@OptIn(ExperimentalComposeApi::class)
@Composable
fun <Producer, T> launchUiStateProducer(
    producer: Producer,
    key: Any?,
    block: suspend Producer.() -> Result<T>
): ProducerResult<UiState<T>> {
    val producerState = remember {
        mutableStateOf(UiState<T>(loading = true))
    }

    val refreshChannel = remember { Channel<Unit>(Channel.CONFLATED) }

    val refresh: () -> Unit = {refreshChannel.offer(Unit)}

    val clearError: () -> Unit = {
        producerState.value = producerState.value.copy(exception = null)
    }


    launchInComposition(producer, key) {
        producerState.value = UiState(loading = false)
        refreshChannel.send(Unit)

        for (refreshEvent in refreshChannel) {
            producerState.value = producerState.value.copy(loading = true)
            producerState.value = producerState.value
                .copyWithResult(producer.block())
        }

    }

    return ProducerResult(producerState, refresh, clearError)

}