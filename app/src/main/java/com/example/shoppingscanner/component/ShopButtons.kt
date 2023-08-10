package com.example.shoppingscanner.component

import android.graphics.Paint.Align
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoppingscanner.presentation.ui.theme.Purple80
import com.example.shoppingscanner.presentation.ui.theme.PurpleGrey40
import com.example.shoppingscanner.presentation.ui.theme.PurpleGrey80
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary

object ShopButtons {
    @Composable
    fun Primary(
        text: String,
        enabled: Boolean = true,
        onClick: () -> Unit,
        modifier: Modifier? = Modifier
    ) {
        Button(
            modifier = modifier
                ?.height(48.dp)
                ?.width(300.dp) ?: Modifier
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
            Text(
                text = text,
                fontSize = 18.sp)
        }
    }
    @Composable
    fun AddList(
        enabled: Boolean = true,
        onClick: () -> Unit,
        modifier: Modifier
    ) {
        Button(
            modifier = modifier
                .width(50.dp)
                .height(50.dp),
            enabled = enabled,
            border = BorderStroke(2.dp, PurplePrimary),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Purple80
            ),
            onClick = onClick,
            shape = RoundedCornerShape(16.dp)
        ){
            Text(
                text = "+",
                textAlign= TextAlign.Center,
                color = PurplePrimary,
                fontSize = 24.sp,
                )
        }
    }

    @Composable
    fun Category(
        enabled: Boolean = true,
        onClick: () -> Unit,
        modifier: Modifier,
        text: String,
        isSelected: Boolean
    ) {
        val buttonColors = if (isSelected) {
            ButtonDefaults.buttonColors(
                containerColor = PurplePrimary,
                contentColor = Color.White
            )
        } else {
            ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = PurplePrimary
            )
        }
        Button(
            modifier = modifier,
            enabled = enabled,
            border = BorderStroke(2.dp, PurplePrimary),
            colors = buttonColors,
            onClick = onClick,
            shape = RoundedCornerShape(16.dp)
        ){
            Text(
                text = text,
                textAlign= TextAlign.Center,
                fontSize = 18.sp,
            )
        }
    }
}
@Composable
@Preview(showBackground = true)
fun ButtonsPreview(){
    Column {
        ShopButtons.Category( onClick = { /*TODO*/ }, modifier = Modifier, text = "Ayakkabı", isSelected = true)
    }

}