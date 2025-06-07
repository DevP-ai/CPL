package com.`in`.cpl.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.`in`.cpl.foundation.theme.darkMaroon
import com.`in`.cpl.foundation.theme.deepAubergine

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

    // Root Box with gradient background
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder for login UI content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Login Screen",
                color = Color.White,
                fontSize = 24.sp
            )

            // You can add login fields and buttons here
        }
    }
}
