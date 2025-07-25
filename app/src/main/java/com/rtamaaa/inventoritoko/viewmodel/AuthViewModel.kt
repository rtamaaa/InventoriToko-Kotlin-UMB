package com.rtamaaa.inventoritoko.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rtamaaa.inventoritoko.api.ApiClient
import com.rtamaaa.inventoritoko.model.LoginRequest
import com.rtamaaa.inventoritoko.model.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    var loginMessage = mutableStateOf("")
    var loginSuccess = mutableStateOf(false)

    private val _registerState = MutableStateFlow("")

    val registerState: StateFlow<String> = _registerState

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.login(LoginRequest(email, password))
                if (response.isSuccessful && response.body()?.success == true) {
                    loginMessage.value = "Login berhasil!"
                    loginSuccess.value = true
                    onResult(true)
                } else {
                    loginMessage.value = response.body()?.message ?: "Login gagal!"
                    loginSuccess.value = false
                    onResult(false)
                }
            } catch (e: Exception) {
                loginMessage.value = "Error: ${e.message}"
                loginSuccess.value = false
                onResult(false)
            }
        }
    }

    fun register(name: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.register(
                    RegisterRequest(name, email, password)
                )
                if (response.isSuccessful && response.body()?.success == true) {
                    _registerState.value = "Register berhasil"
                    onResult(true)
                } else {
                    _registerState.value = "Gagal register"
                    onResult(false)
                }
            } catch (e: Exception) {
                _registerState.value = "Error: ${e.message}"
                onResult(false)
            }
        }
    }
}
