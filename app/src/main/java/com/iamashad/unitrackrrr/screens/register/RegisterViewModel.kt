package com.iamashad.unitrackrrr.screens.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.iamashad.unitrackrrr.model.User
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    fun signInWithEmailPassword(
        email:String,
        password:String,
        home: () -> Unit
    ) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {task->
                    if (task.isSuccessful) {
                        home()
                    } else {
                        Log.d("NAY", "task: ${task.result}")
                    }
                }
        } catch (ex: Exception) {
            Log.d("TAG", "signIn: ${ex.message}")
        }
    }
    fun createWithEmailPassword(
        email: String,
        password: String,
        home: () -> Unit
    ) = viewModelScope.launch {
        if (_loading.value == false) {
            _loading.value = true
            try {
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener{ task ->
                        if (task.isSuccessful) {
                            val displayName = task.result.user?.email?.split("@")?.get(0)
                            createUser(displayName)
                            home()
                        } else {
                            Log.d("NAY", "task: ${task.result}")
                        }
                    }
            } catch (ex: Exception) {
                Log.d("TAG", "Register: ${ex.message}")
            }
        }
        _loading.value = false
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = User(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            profession = "Android Developer",
            quote = "Everybody wants to know what I'd do if I didn't win. I guess we'll never know!",
            id = null
        ).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }

}