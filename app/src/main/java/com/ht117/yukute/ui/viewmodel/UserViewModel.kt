package com.ht117.yukute.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.ht117.yukute.repository.UserRepository

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel()