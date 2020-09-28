package com.ht117.yukute.ui.screen.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.channels.Channel

interface IAction

interface IState

interface IModel<State: IState, Intent: IAction> {
    val intents: Channel<Intent>
    val state: LiveData<State>
}

interface IView<State: IState> {
    fun render(state: State)
}

suspend fun<T: IState> updateState(newState: MutableLiveData<T>, oldState: LiveData<T>, handler: suspend(state: T) -> T) {
    newState.postValue(handler(oldState.value!!))
}