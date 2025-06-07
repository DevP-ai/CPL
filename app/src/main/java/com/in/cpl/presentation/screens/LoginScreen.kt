package com.`in`.cpl.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.`in`.cpl.foundation.theme.darkMaroon
import com.`in`.cpl.foundation.theme.deepAubergine
import com.`in`.cpl.foundation.theme.textFieldBorder
import com.`in`.cpl.foundation.theme.textFieldLabel
import com.`in`.cpl.foundation.theme.textFieldText

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginSuccess: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {}
) {
    // Define your gradient
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            darkMaroon,
            deepAubergine
        )
    )

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    // Root Box with gradient background
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder for login UI content
        Column(
            modifier = Modifier
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Welcome Back",
                color = Color.White,
                fontSize = 28.sp
            )

            CustomBoxTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "Email",
                placeholder = "Enter your email address",
                keyboardType = KeyboardType.Email
            )

            CustomBoxTextField(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "Enter your password",
                isPassword = true,
                keyboardType = KeyboardType.Password
            )

            Button(
                onClick = onLoginSuccess,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Login",
                    color = Color.White,
                    fontSize = 24.sp
                )
            }

            TextButton(onClick = onCreateAccountClick) {
                Text(
                    text = "Create an account",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CustomBoxTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    textColor: Color = Color.White,
    unfocusedBorderColor: Color = Color.White,
    focusedBorderColor: Color = Color(0xFF9A4D4D),
    backgroundColor: Color = Color.Black,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp)
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }


    val animatedBorderColor by animateColorAsState(
        targetValue = if (isFocused) focusedBorderColor else unfocusedBorderColor,
        label = "borderColor"
    )

    val animatedBackgroundColor by animateColorAsState(
        targetValue = if (passwordVisible) Color.Red else backgroundColor,
        label = "borderColor"
    )

    val animatedTextColor by animateColorAsState(
        targetValue = if (passwordVisible) Color.White else focusedBorderColor,
        label = "borderColor"
    )

    Column(modifier = modifier
        .fillMaxWidth()
    ) {
        Text(text = label, color = unfocusedBorderColor, fontSize = 14.sp)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 6.dp)
                .background(animatedBackgroundColor, shape)
                .border(width = 1.dp, color = animatedBorderColor, shape = shape)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = textColor,
                    fontSize = 16.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                visualTransformation = if (isPassword && !passwordVisible)
                    PasswordVisualTransformation()
                else
                    VisualTransformation.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (value.isEmpty()) {
                                Text(
                                    text = placeholder,
                                    color = Color.LightGray,
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }

                        if (isPassword) {
                            Text(
                                text = if (passwordVisible) "Hide" else "Show",
                                color = animatedTextColor,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .clickable { passwordVisible = !passwordVisible }
                            )
                        }
                    }
                }
            )
        }
    }
}



