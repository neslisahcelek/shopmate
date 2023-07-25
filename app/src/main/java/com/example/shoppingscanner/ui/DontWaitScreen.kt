package com.example.shoppingscanner.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.Screen

@Composable
fun DontWaitScreen(
    navController: NavController
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.dont_wait),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )
        Button(
            onClick = {Modifier.clickable { 
                navController.navigate(Screen.ContinueWithBarcodeScreen.route)
            } },
            shape = RoundedCornerShape(20.dp)
        )
        {
            Text(text = stringResource(R.string.dont_wait))

        }

    }
    // Spacer(modifier = Modifier.weight(1f))
}

@Composable
@Preview(showBackground = true)
fun DontWaitScreenPreview() {
    DontWaitScreen(navController = rememberNavController())
}
