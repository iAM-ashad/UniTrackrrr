package com.iamashad.unitrackrrr.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iamashad.unitrackrrr.R
import com.iamashad.unitrackrrr.ui.theme.onPrimaryContainerDarkHighContrast
import com.iamashad.unitrackrrr.ui.theme.onPrimaryContainerLight
import com.iamashad.unitrackrrr.ui.theme.primaryContainerLight
import com.iamashad.unitrackrrr.utils.customFonts

@Composable
fun EmailInputField(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    onAction: KeyboardActions = KeyboardActions.Default,
) {

    OutlinedTextField(
        value = emailState.value,
        onValueChange = {
            emailState.value = it
        },
        label = {
            Text(
                text = "Email"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = null
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        keyboardActions = onAction,
        maxLines = 1,
        shape = RoundedCornerShape(35.dp),
        modifier = modifier
    )
}

@Composable
fun PasswordInputField(
    modifier: Modifier,
    passwordState: MutableState<String>,
    isVisible: MutableState<Boolean>,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = {
            Text(
                text = "Password"
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null
            )
        },
        trailingIcon = {
            when (isVisible.value) {
                true -> Image(
                    painter = painterResource(R.drawable.eyeopen),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            isVisible.value = !isVisible.value
                        }
                )
                else -> Image(
                    painter = painterResource(id = R.drawable.eyeclose),
                    contentDescription = null,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            isVisible.value = !isVisible.value
                        }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        visualTransformation = when (isVisible.value) {
            true -> VisualTransformation.None
            else -> PasswordVisualTransformation()
        },
        keyboardActions = onAction,
        maxLines = 1,
        shape = RoundedCornerShape(35.dp),
        modifier = modifier
    )
}
@Composable
fun LoginRegisterButton(
    buttonText: String,
    validInputs: Boolean,
    modifier: Modifier,
    onButtonClicked: () -> Unit,
) {
    Button(
        onClick = onButtonClicked,
        shape = RoundedCornerShape(35.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 15.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(226,180,117,255),
            disabledContainerColor = Color(241,223,200,255)

        ),
        enabled = validInputs,
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navController: NavController,
    isHome: Boolean,
    title: String
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1f),
        color = primaryContainerLight
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 5.dp, top = 30.dp, end = 5.dp, bottom = 15.dp)
        ){
            if (isHome) {
                Image(
                    painter = painterResource(R.drawable.nitm),
                    contentDescription = "College Logo",
                    modifier = Modifier
                        .padding(5.dp)
                )
                Column (
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                ) {
                    Text(
                        text = title,
                        fontSize = 37.sp,
                        fontFamily = customFonts(GoogleFont("Unna")),
                        color = onPrimaryContainerLight,
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                    )
                    Text(
                        text = "Recover, Resolve, Stay on Track",
                        fontSize = 22.sp,
                        fontFamily = customFonts(GoogleFont("Unna")),
                        color = onPrimaryContainerLight,
                        modifier = Modifier
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .scale(.7f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.logout__2_),
                        contentDescription = "Log Out",
                        tint = onPrimaryContainerDarkHighContrast,
                        modifier = Modifier

                    )
                }
            } else  {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Go Back"
                    )
                }
            }
        }
    }
}