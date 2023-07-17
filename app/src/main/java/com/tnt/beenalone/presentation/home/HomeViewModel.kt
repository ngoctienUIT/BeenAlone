package com.tnt.beenalone.presentation.home

import androidx.lifecycle.ViewModel
import com.tnt.beenalone.data.local.repository.BeenAloneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val beenAloneRepository: BeenAloneRepository) :
    ViewModel() {
}