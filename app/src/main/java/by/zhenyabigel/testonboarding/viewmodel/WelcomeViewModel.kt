package by.zhenyabigel.testonboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(

):ViewModel() {

    fun nextOnBoardingScreen(){
        viewModelScope.launch {

        }
    }
}