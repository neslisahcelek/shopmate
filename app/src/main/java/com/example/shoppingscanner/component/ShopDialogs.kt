package com.example.shoppingscanner.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shoppingscanner.R

object ShopDialogs {
    @Composable
    fun ShopDialog(
        modifier: Modifier = Modifier,
        onDismissRequest: () -> Unit,
        title: @Composable () -> Unit,
        text: @Composable () -> Unit,
        confirmOnClick: () -> Unit,
        dismissOnClick: () -> Unit
    ) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = title,
            text = {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    text()
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalArrangement = Arrangement.Start
                ) {
                    ShopButtons.Dialog(
                        text = stringResource(R.string.dialog_confirm_button),
                        onClick = confirmOnClick,
                    )
                }
            },
            dismissButton = {
                ShopButtons.Dialog(
                    text = stringResource(R.string.dialog_dismiss_button),
                    onClick = dismissOnClick
                )
            },
            shape = MaterialTheme.shapes.medium,
            modifier = modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(8.dp)),
        )
    }
}