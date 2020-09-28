package com.ht117.yukute.ui.screen.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ht117.yukute.ui.screen.base.IModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class LandingViewModel: ViewModel(), IModel<LandingState, LandingAction> {
    override val intents: Channel<LandingAction> = Channel(Channel.UNLIMITED)
    private val _state = MutableLiveData<LandingState>().apply { value = LandingState() }
    override val state: LiveData<LandingState> get() = _state

    init {
        handleIntents()
    }

    private fun handleIntents() = viewModelScope.launch {
        intents.consumeAsFlow().collect {
            when (it) {
                LandingAction.CheckAuthorize -> {
                    checkUserAuthorize()
                }
            }
        }
    }

    private suspend fun checkUserAuthorize() {
        // TODO :: This is empty - fix linter warning
    }
}