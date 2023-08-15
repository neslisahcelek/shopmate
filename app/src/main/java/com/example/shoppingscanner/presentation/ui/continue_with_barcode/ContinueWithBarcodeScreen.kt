package com.example.shoppingscanner.presentation.ui.continue_with_barcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.presentation.ui.navigation.Screen
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopTexts

@Composable
fun ContinueWithBarcodeScreen(
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

    ){
        Card (
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.LightGray),
            content = {
                ShopTexts.BodyRegular(
                    text = stringResource(R.string.continue_info),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(20.dp),
                    textAlign = TextAlign.Center
                )
            }
        )
        Spacer(modifier = Modifier.height(60.dp))

        ShopButtons.Primary(
            text = stringResource(R.string.continue_with_barcode),
            onClick = {
                navController.navigate(Screen.BarcodeScannerScreen.route) },
        )

    }

}

@Composable
@Preview(showBackground = true)
fun ContinueWithBarcodeScreenPreview() {
    ContinueWithBarcodeScreen(navController = rememberNavController())
}