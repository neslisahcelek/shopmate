package com.example.shoppingscanner.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppingscanner.R
import com.example.shoppingscanner.Screen
import com.example.shoppingscanner.compose.ShopButtons
import com.example.shoppingscanner.ui.theme.PurplePrimary


@Composable
fun DontWaitScreen(
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(top = 200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        ShopButtons.Primary(
            text = stringResource(R.string.dont_wait),
            onClick = {
                navController.navigate(Screen.ContinueWithBarcodeScreen.route) },
        )

        if (navController.currentBackStackEntry?.destination?.route == Screen.DontWaitScreen.route){
            Image(
                imageVector = Icons.Default.List,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .padding(start = 8.dp)
            )
        }


        LazyColumn(){
            items(10){
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 8.dp),
                    tint = PurplePrimary
                )
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun DontWaitScreenPreview() {
    DontWaitScreen(navController = rememberNavController())
}
