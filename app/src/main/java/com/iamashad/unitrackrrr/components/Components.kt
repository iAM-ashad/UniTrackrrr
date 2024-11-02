package com.iamashad.unitrackrrr.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.iamashad.unitrackrrr.R
import com.iamashad.unitrackrrr.navigation.TrackrScreens
import com.iamashad.unitrackrrr.ui.theme.onPrimaryContainerDarkHighContrast
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
                text = "Email",
                color = Color(130,30,150)
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
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedContainerColor = Color(224, 240, 255),
            focusedContainerColor = Color(224, 240, 255))
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
                text = "Password",
                color=Color(130,30,150)
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
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedContainerColor = Color(224, 240, 255),
            focusedContainerColor = Color(224, 240, 255)),
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
            disabledContainerColor = Color(72, 191, 145),
            containerColor = Color(72, 191, 145),
        ),
        enabled = validInputs,
        modifier = modifier
    ) {
        Text(
            text = buttonText,
            color = Color(101, 67, 33),
            fontSize = 20.sp,
            fontFamily = customFonts(GoogleFont("Aldrich"))
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
            .fillMaxHeight(.12f)
            .padding(top=1.dp),
        color = primaryContainerLight
    ) {
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 40.dp, end = 5.dp, bottom = 15.dp)
        )
        {
            if (isHome)
            {
                Surface(modifier = Modifier
                    .size(70.dp)
                    .aspectRatio(1f)
                    .padding(top = 5.dp),
                    color = primaryContainerLight,
                    shape= CircleShape
                ){
                    Image(
                        painter = painterResource(R.drawable.nitm),
                        contentDescription = "College Logo",
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
                Column (
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding( start=30.dp)
                ) {
                    Text(
                        text = title,
                        fontSize = 37.sp,
                        fontFamily = customFonts(GoogleFont("Great Vibes")),
                        color = Color(34, 40, 49),
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
                IconButton(
                    onClick = {
                        Firebase.auth.signOut().run {
                            navController.navigate(TrackrScreens.LOGINSCREEN.name)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .scale(1f)
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

@Composable
fun RadioButtonList(
    selectedValue: (Int) -> Unit
) {
    val options = listOf("Semester I", "Semester II", "Semester III", "Semester IV", "Semester V", "Semester VI", "Semester VII", "Semester VIII")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column () {
        options.forEachIndexed { index, option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .clickable {
                        selectedOption = option
                        selectedValue(index+1)
                    }
            ) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick = {
                        selectedOption = option
                        selectedValue(index+1)
                    }
                )
                Text(
                    text = option,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}

fun showToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG)
        .show()
}

