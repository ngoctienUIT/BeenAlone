package com.tnt.beenalone.presentation.rank

import androidx.lifecycle.ViewModel
import com.tnt.beenalone.network.api.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RankViewModel @Inject constructor(private val userApi: UserApi) : ViewModel() {
}