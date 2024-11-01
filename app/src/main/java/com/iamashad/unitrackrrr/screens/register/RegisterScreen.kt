package com.iamashad.unitrackrrr.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.iamashad.unitrackrrr.R
import com.iamashad.unitrackrrr.components.EmailInputField
import com.iamashad.unitrackrrr.components.LoginRegisterButton
import com.iamashad.unitrackrrr.components.PasswordInputField
import com.iamashad.unitrackrrr.navigation.TrackrScreens
import com.iamashad.unitrackrrr.utils.customFonts

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 80.sp,
            fontFamily = customFonts(GoogleFont("Great Vibes")),
            color = Color(240,205,156),
            modifier = Modifier
                .padding(5.dp)
        )
        UsersForm("Register") {email, password ->
            viewModel.createWithEmailPassword(email,password) {
                navController.navigate(TrackrScreens.HOMESCREEN.name)
            }
        }
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(
                text = "Already Have an Account?"
            )
            Text(
                text = " Sign In",
                color =  Color.Blue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable {
                        navController.navigate(TrackrScreens.LOGINSCREEN.name)
                    }
            )
        }
    }
}

@Composable
fun UsersForm (
    buttonText: String,
    onDone: (String, String) -> Unit = {email, password ->}
) {
    val email = rememberSaveable {
        mutableStateOf("")
    }
    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordFocusRequest = FocusRequester.Default
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(email.value, password.value) {
        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }
    val isVisible = rememberSaveable {
        mutableStateOf(false)
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        EmailInputField(
            emailState = email,
            onAction = KeyboardActions{
                passwordFocusRequest.requestFocus()
            },
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
        )
        PasswordInputField(
            passwordState = password,
            isVisible = isVisible,
            modifier = Modifier
                .padding(bottom = 15.dp)
        )
        LoginRegisterButton (
            buttonText = buttonText,
            validInputs = valid,
            modifier = Modifier
                .padding(bottom = 20.dp)
        ) {

            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }

}