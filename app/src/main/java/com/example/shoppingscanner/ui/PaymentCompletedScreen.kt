package com.example.shoppingscanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.Screen
import com.example.shoppingscanner.compose.ShopButtons
import com.example.shoppingscanner.compose.ShopTexts


@Composable
fun PaymentCompletedScreen(
    navController: NavController
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
) {
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.layer_payment),
            contentDescription = "barcode",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
            )
        ShopTexts.Title1Bold(
            text = stringResource(R.string.payment_successfull),
            textAlign = TextAlign.Center,
            color = Color.Green,
            modifier = Modifier.fillMaxWidth()
        )
        ShopButtons.Primary(
            onClick = {
                navController.navigate(Screen.BarcodeScannerScreen.route)
            },
            text = stringResource(R.string.home_screen),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PaymentCompletedScreenPreview() {
    PaymentCompletedScreen(navController = rememberNavController())
}