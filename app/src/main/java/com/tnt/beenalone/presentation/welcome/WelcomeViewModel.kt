package com.tnt.beenalone.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import com.tnt.beenalone.data.local.store.DataStoreManager
import com.tnt.beenalone.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val beenAloneRepository: BeenAloneRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    fun saveProfile(user: User) {
        viewModelScope.launch {
            val myUser = beenAloneRepository.getUser().first()
            if (myUser == null) {
                beenAloneRepository.upsertUser(user.toUserEntity())
            } else {
                beenAloneRepository.upsertUser(user.toUserEntity(myUser.idMongo, myUser.id))
            }
        }
    }

    fun saveSetting(dateAlone: LocalDate, title: String) {
        viewModelScope.launch {
            dataStoreManager.setString("dateAlone", formatter.format(dateAlone))
        }
        viewModelScope.launch {
            dataStoreManager.setString("title", title)
        }
    }
}