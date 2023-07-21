package com.tnt.beenalone.presentation.edit_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val beenAloneRepository: BeenAloneRepository) :
    ViewModel() {
    private val _editProfileUIState = MutableStateFlow(EditProfileUIState())
    val editProfileUIState: StateFlow<EditProfileUIState> = _editProfileUIState

    var showToastSuccess by mutableStateOf(false)

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            beenAloneRepository.getUser().collect {
                if (it.isEmpty()) {
                    _editProfileUIState.value = EditProfileUIState()
                } else {
                    _editProfileUIState.value = EditProfileUIState(it[0].toUser())
                }
            }
        }
    }

    fun saveProfile(user: User) {
        viewModelScope.launch {
            val myUser = beenAloneRepository.getUser().first()
            if (myUser.isEmpty()) {
                beenAloneRepository.upsertUser(user.toUserEntity())
            } else {
                beenAloneRepository.upsertUser(user.toUserEntity(myUser[0].idMongo, myUser[0].id))
            }
            showToastSuccess = true
        }
    }
}