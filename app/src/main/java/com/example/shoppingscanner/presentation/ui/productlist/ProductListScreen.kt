package com.example.shoppingscanner.presentation.ui.productlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.shoppingscanner.R
import com.example.shoppingscanner.component.ShopButtons
import com.example.shoppingscanner.component.ShopDialogs
import com.example.shoppingscanner.component.ShopList
import com.example.shoppingscanner.component.ShopTexts
import com.example.shoppingscanner.domain.dto.ListProduct
import com.example.shoppingscanner.presentation.ui.base.BaseEvent
import com.example.shoppingscanner.presentation.ui.navigation.NavActions
import com.example.shoppingscanner.presentation.ui.theme.PurpleGrey80
import com.example.shoppingscanner.presentation.ui.theme.PurplePrimary
import com.example.shoppingscanner.util.ProductCategory
import com.example.shoppingscanner.util.showShortToast


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    action: NavActions.ProductListActions,
    viewModel : ProductListViewModel
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    var isShoppingListVisible by remember {
        mutableStateOf(false)
    }

    state.messageId?.let {
        showShortToast(
            context = context,
            message = stringResource(id = it)
        )

        viewModel.onEvent(
            BaseEvent.OnHandledMessage(
                stringResource(id = it)
            )
        )
    }

    LaunchedEffect(key1 = viewModel){
        viewModel.onEvent(BaseEvent.GetData(category = ProductCategory.DECORATION.categoryName))
    }

    val list by rememberUpdatedState(newValue = viewModel.shoppingListState.value.shoppingList)
    var isDialogOpen by remember {
        mutableStateOf(false)
    }

    val baseEvent by viewModel.baseEvent.collectAsState(null)
    when (baseEvent) {
        is BaseEvent.RemoveProduct -> {
            viewModel.onHandledEvent()
        }

        else -> {}
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
    ){
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ShopList.CategoryList(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        start = 10.dp
                    ),
                viewModel = viewModel
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),

            ){
                viewModel.state.value.productList?.let {
                    ShopList.ProductList(
                        productList = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp, vertical = 5.dp),
                        onClick = { product ->
                            viewModel.addToList(product)
                        }
                    )
                }
                ConstraintLayout (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 10.dp)
                ){
                    val (icon, button) = createRefs()

                    ShopButtons.Small(
                        text = stringResource(R.string.continue_with_barcode),
                        onClick = {
                            isDialogOpen = true
                        },
                        modifier = Modifier
                            .constrainAs(button) {
                                bottom.linkTo(parent.bottom)
                                centerHorizontallyTo(parent)
                            }
                            .width(200.dp)
                    )

                    ListBadgedBox(
                        quantity = "${list.size}",
                        onClick = { isShoppingListVisible = !isShoppingListVisible },
                        modifier = Modifier
                            .constrainAs(icon) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom)
                                centerVerticallyTo(button)
                            }
                    )
                }
                if (isShoppingListVisible) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            isShoppingListVisible = false
                        }
                    )
                    {
                        BottomSheetContent(
                            list = list,
                            viewModel = viewModel
                        )
                    }
                }

                if (isDialogOpen) {
                    if (list.isNullOrEmpty()){
                        ShopDialogs.ShopDialog(
                            modifier = Modifier,
                            onDismissRequest = {
                                isDialogOpen = false },
                            title = { Text(text = stringResource(R.string.product_list_dialog_title)) },
                            text = { Text(text = stringResource(R.string.product_list_dialog_message),) },
                            confirmButton = {
                                ShopButtons.Small(
                                    text = stringResource(R.string.dialog_confirm_button),
                                    onClick = {
                                        action.productListToBarcodeScannerAction.invoke()
                                    },
                                    modifier = Modifier
                                        .width(100.dp)
                                )
                            },
                            dismissButton = {
                                ShopButtons.Small(
                                    text = stringResource(R.string.dialog_dismiss_button),
                                    onClick = {isDialogOpen = false},
                                    modifier = Modifier
                                        .width(100.dp)
                                )
                            },
                        )
                    }else{
                        isDialogOpen = false
                        action.productListToBarcodeScannerAction.invoke()
                    }
                }
            }
        }
    }
}

@Composable
fun BottomSheetContent(
    list : List<ListProduct>,
    viewModel: ProductListViewModel
) {
    ShopTexts.Title1Bold(
        text = stringResource(id = R.string.my_shopping_list),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp),
        textAlign = TextAlign.Center,
        color = PurplePrimary,
        textDecoration = TextDecoration.Underline
    )
    list.let {
        ShopList.BottomSheetProductList(
            productList = it,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            viewModel = viewModel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBadgedBox(
    quantity: String?,
    modifier: Modifier,
    onClick: () -> Unit
){
    Box(
        modifier = modifier
            .width(60.dp)
            .height(50.dp)
            .background(
                color = PurpleGrey80,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .padding(top = 10.dp, end = 3.dp),
        contentAlignment = Alignment.Center,
    ){
        BadgedBox(
            modifier = Modifier,
            badge = {
                Badge(
                    contentColor = PurplePrimary,
                    containerColor = Color.Transparent,
                ){
                    ShopTexts.BodyBold(
                        text = quantity ?: "",
                        fontSize = 14.sp
                    )
                }
            }
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.List,
                    tint = PurplePrimary,
                    contentDescription = null
                )
            }
        }
    }

}





