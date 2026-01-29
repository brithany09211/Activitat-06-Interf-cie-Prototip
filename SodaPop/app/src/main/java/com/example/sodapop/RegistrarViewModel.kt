package com.example.sodapop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrarViewModel : ViewModel() {
    // LiveData que indica si el registre és correcte
    private val _registreCorrecte = MutableLiveData<Boolean>(false)
    val registreCorrecte: LiveData<Boolean> = _registreCorrecte

    // LiveData per missatges d’error
    private val _missatgeError = MutableLiveData<String>()
    val missatgeError: LiveData<String> = _missatgeError

    fun validarDades(nom: String, cognoms: String, correu: String, contrasenya: String, repetirContrasenya: String) {
        when {
            nom.isBlank() -> {
                _missatgeError.value = "El nom no pot estar buit"
            }
            cognoms.isBlank() -> {
                _missatgeError.value = "Els cognoms no poden estar buits"
            }
            !correu.contains("@") || !correu.contains(".") -> {
                _missatgeError.value = "El correu no és vàlid"
            }
            contrasenya.length < 6 -> {
                _missatgeError.value = "La contrasenya ha de tenir almenys 6 caràcters"
            }
            contrasenya != repetirContrasenya -> {
                _missatgeError.value = "Les contrasenyes no coincideixen"
            }
            else -> {
                _registreCorrecte.value = true
            }
        }
    }
}