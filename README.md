# Shopping Scanner
 This application allows you to scan the barcode of products and retrieve information such as product name and price. You can also add products to your cart and make purchases through the app. This document provides instructions on how to install and use the application effectively.

## Features
- Scan barcodes using your device's camera to get product information.
- View product details, including the product name and price.
- Add products to your shopping cart for later purchase.
- Complete the purchase process within the app.

## Usage
- Launch the app from your home screen or app drawer.
- You will see an information screen, click the button and continue to scanner screen. 
- You will see a barcode scanning screen; point the camera at the barcode of the product you want to scan.
- The app will automatically recognize and decode the barcode.
- Once the barcode is successfully scanned, you will see the product's name and price on the screen.
- If you want to purchase the item, tap the "Sepete Ekle" button, and it will be added to your cart.
- To see your shopping cart, tap the "Satın Al" button. 
- To complete purchase, tap the "Pay with Hadi" button.

Please note that the app requires a stable internet connection to fetch product information from the server.

## Technologies Used
- Dagger Hilt: The app uses Dagger Hilt, a dependency injection library, to manage and provide dependencies throughout the application.
- Android Jetpack Compose: Jetpack Compose is used to build the user interface of the app, providing a declarative way to create UI components.
- Android Navigation Component: The Navigation Component from Android Jetpack is used for navigation between different screens in the app.
- ML Kit Barcode Scanning: The app leverages the ML Kit Barcode Scanning API provided by Google, enabling fast and accurate barcode scanning functionality.
- Retrofit: Retrofit is used to handle network requests and communication with the server, making it easier to fetch product information.
- Moshi: Moshi is used for JSON parsing, helping to convert JSON responses from the server into Kotlin objects.
- Coil: Coil is used for image loading with Jetpack Compose, providing efficient and optimized image loading capabilities.
- OkHttp: OkHttp is used as an HTTP client, providing easy-to-use APIs for making network requests and handling responses.

