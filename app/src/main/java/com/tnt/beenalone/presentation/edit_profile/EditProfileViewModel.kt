package com.tnt.beenalone.presentation.edit_profile

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(private val beenAloneRepository: BeenAloneRepository) :
    ViewModel() {

    var name by mutableStateOf("")
    var gender by mutableStateOf(true)
    var datePicker: LocalDate by mutableStateOf(LocalDate.now())
    var date: LocalDate by mutableStateOf(LocalDate.now())
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var imageUri by mutableStateOf<Uri?>(null)

    var showToastSuccess by mutableStateOf(false)

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            beenAloneRepository.getUser().collect { user ->
                if (user != null) {
                    name = user.name
                    datePicker = LocalDate.parse(user.birthday, formatter)
                    date = LocalDate.parse(user.birthday, formatter)
                    gender = user.gender
                    imageUri = if (user.avatar.isNotEmpty()) Uri.parse(user.avatar) else null
                }
            }
        }
    }

    fun saveProfile() {
        viewModelScope.launch {
            val user = User(
                name,
                true,
                formatter.format(datePicker),
                imageUri?.toString() ?: "",
                LocalDate.now(),
            )
            val myUser = beenAloneRepository.getUser().first()
            if (myUser == null) {
                beenAloneRepository.upsertUser(user.toUserEntity())
            } else {
                beenAloneRepository.upsertUser(user.toUserEntity(myUser.idMongo, myUser.id))
            }
            showToastSuccess = true
        }
    }
}