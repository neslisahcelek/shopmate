package com.example.shoppingscanner.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary

object ShopButtons {
    @Composable
    fun Primary(
        text: String,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .height(48.dp)
                .width(300.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = PurplePrimary,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
            onClick = onClick,
            shape = RoundedCornerShape(10.dp)
        ){
            Text(text = text, fontSize = 20.sp)
        }
    }

    @Composable
    fun Small(
        text: String,
        enabled: Boolean = true,
        onClick: () -> Unit,
        modifier: Modifier
    ) {
        Button(
            modifier = modifier
                .height(48.dp)
                .width(160.dp),
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = PurplePrimary,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
            onClick = onClick,
            shape = RoundedCornerShape(16.dp)
        ){
            Text(text = text)
        }
    }
}
@Composable
@Preview(showBackground = true)
fun ButtonsPreview(){
    Column {
        ShopButtons.Primary(text = "Primary", onClick = {})
        Spacer(modifier = Modifier.height(10.dp))
        ShopButtons.Small(text = "Small", onClick = {}, modifier = Modifier)
    }

}