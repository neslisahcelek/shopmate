package com.example.shoppingscanner.ui.fragment

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.shoppingscanner.databinding.FragmentBarcodeScannerBinding
import com.example.shoppingscanner.model.Product
import com.example.shoppingscanner.viewmodel.ProductViewModel
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BarcodeScannerFragment : Fragment() {
    @Suppress("InlinedApi")
    private var visible: Boolean = false
    private var fullscreenContent: View? = null
    private var fullscreenContentControls: View? = null

    // views
    private var _binding: FragmentBarcodeScannerBinding? = null
    private var addToCartButton: Button? = null
    private var buyNowButton: Button? = null
    private var cameraPreview: ImageView? = null
    private var nameTextView: TextView? = null
    private var priceTextView: TextView? = null
    private var totalPriceTextView: TextView? = null

    // texts
    private var productText:String? = null
    private var priceText:String? = null
    private var totalPriceText:String? = null

    // barcode scanner
    private var barcodeScannerOptions: BarcodeScannerOptions? = null
    private var barcodeScanner: BarcodeScanner? = null

    // image
    private var imageBitmap:Bitmap? = null
    private val REQUEST_IMAGE_CAPTURE=1

    private var product: Product? = null

    private val viewModel: ProductViewModel by viewModels()
    private val binding get() = _binding!!

    val CAMERA_PERMISSION_REQUEST_CODE =100

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBarcodeScannerBinding.inflate(inflater, container, false)

        cameraPreview = binding.cameraPreview

        nameTextView = binding.productName
        priceTextView = binding.productPrice
        totalPriceTextView = binding.totalPrice

        productText = "Ürün: "
        priceText = "Fiyat: "
        totalPriceText = "Toplam Tutar: "


        buyNowButton = binding.btnbuynow
        addToCartButton = binding.btnaddtocart
        barcodeScannerOptions = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_ALL_FORMATS)
            .build()
        barcodeScanner = BarcodeScanning.getClient(barcodeScannerOptions!!)


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        visible = true

        startCamera()

        buyNowButton?.setOnClickListener(){
            Log.d("buyNowButton", "buyNowButton: $product")
            product?.let { it1 -> addtoCart(it1) } // add to cart
            navigate(it) // navigate to cart screen
        }

        addToCartButton?.setOnClickListener(){
            this.product?.let { it1 -> addtoCart(it1) } // add to cart
        }

    }

    private fun addtoCart(product: Product) {
        Log.d("addtoCart", "addtoCart: $product")

        var productPrice=0.0
        try{
            productPrice = product.stores?.get(0)?.price?.toDouble()!!
        }catch (e:Exception){
            Log.e("Error",e.message.toString())
        }
        var cart = viewModel.cartProducts

        if (cart.get(product) != null) {
            cart.set(product, cart.get(product)!! + 1)
            if (productPrice != null) {
                productPrice *= cart.get(product)!!
                Log.d("addtoCart", "productPrice: $productPrice")
            }
        }else{
            cart.put(product, 1)
            Log.d("addtoCart", "1 productQuantity: $cart.get(product)")
        }
        totalPriceTextView!!.text = totalPriceText + " " + productPrice + " ₺"
        Log.d("addtoCart", "products: ${viewModel.cartProducts}")
    }


    private fun startCamera() {
        Log.d("start camera", "start")
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
        } catch (e: Exception) {
            Log.e("Error taking image", e.message.toString())
            showToast("Please try again.")
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==REQUEST_IMAGE_CAPTURE && resultCode== RESULT_OK){
            val extras: Bundle? = data?.extras
            imageBitmap= extras?.get("data") as Bitmap

            if (imageBitmap!=null) {
                binding.cameraPreview.setImageBitmap(imageBitmap)
                Log.d("onactivityresult","Image not null")
                processImage()
            }else{
                startCamera()
            }
        }
    }


    private fun processImage() {
        if (imageBitmap!=null){
            val inputImage = InputImage.fromBitmap(imageBitmap!!,0)
            val scanner = BarcodeScanning.getClient(barcodeScannerOptions!!)
            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->

                    if (barcodes.toString() == "[]") {
                        showToast("Please try again.")
                        startCamera()
                    }

                    for (barcode in barcodes) {
                        Log.d("barcode", barcode.rawValue.toString())
                        getProductDetails("3614272049529")
                        //getProductDetails(barcode.rawValue.toString())
                    }
                }
                .addOnFailureListener {

                }
        }
        else{
            showToast("Please take an image")
        }
    }


    private fun getProductDetails(barcode: String) {
        viewModel.getDataFromAPI(barcode)
        Log.d("get data ", barcode)
        viewModel.product.observe(viewLifecycleOwner) { product ->
            product?.let {
                Log.d("observe product", product.toString())
                this.product=product
                val productName = product.title
                var productPrice = "0.0"
                try {
                    productPrice = product.stores?.get(0)?.price.toString()
                }catch (e:Exception){

                }
                nameTextView!!.text = productText + " " + productName
                priceTextView!!.text = priceText + " " + productPrice + " ₺"
                totalPriceTextView!!.text = totalPriceText + " " + productPrice + " ₺"
            } ?: run {
                showToast("No product found or an error occurred.")
                Log.e("Product Details Error", "No product found or an error occurred.")
            }
        }
    }


    fun navigate(view:View){
        val intent = BarcodeScannerFragmentDirections.actionBarcodeScannerFragmentToCartFragment()
        Navigation.findNavController(view).navigate(intent)
    }

    fun showToast(message:String){
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
        buyNowButton = null
        addToCartButton = null
        fullscreenContent = null
        fullscreenContentControls = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}