package com.iamashad.unitrackrrr.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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
import com.iamashad.unitrackrrr.screens.register.RegisterViewModel
import com.iamashad.unitrackrrr.utils.customFonts

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
) {

    Surface(color = Color(224, 240, 255,255)) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Surface(
                shape = CircleShape,
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 55.dp),
                color = Color(224, 240, 255, 255)
            ) {
                Image(
                    painterResource(id = R.drawable.nitm),
                    contentDescription = "Logo",

                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = 80.sp,
                fontFamily = customFonts(GoogleFont("Great Vibes")),
                color = Color(34, 40, 49),
                modifier = Modifier
                    .padding(10.dp)
            )
            UsersForm("Login") { email, password ->
                viewModel.signInWithEmailPassword(email, password) {
                    navController.navigate(TrackrScreens.HOMESCREEN.name)
                }
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Don't have an account?",
                    fontSize = 20.sp,
                    color = Color(45, 52, 54)
                )
                Text(
                    text = " Sign Up",
                    color = Color(0, 102, 204),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = customFonts(GoogleFont("Aldrich")),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(TrackrScreens.REGISTERSCREEN.name)
                        }
                )
            }
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

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        EmailInputField(
            emailState = email,
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            },
            modifier = Modifier
                .padding(top = 65.dp, bottom = 5.dp)
        )
        PasswordInputField(
            passwordState = password,
            isVisible = isVisible,
            modifier = Modifier
                .padding(top = 15.dp, bottom = 15.dp)
        )
        LoginRegisterButton(
            buttonText = buttonText,
            validInputs = valid,
            modifier = Modifier
                .padding(top = 95.dp, bottom = 20.dp)
        ) {

            onDone(email.value.trim(), password.value.trim())
            keyboardController?.hide()
        }
    }
}