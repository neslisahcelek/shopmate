package com.example.shoppingscanner.data.repository

import com.example.shoppingscanner.domain.repository.BarcodeRepository
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BarcodeRepositoryImpl @Inject constructor(
    private val scanner : GmsBarcodeScanner
): BarcodeRepository{

    var isPauseScan = false
    override fun scan(): Flow<String?> {
        return callbackFlow {
            scanner.startScan()
                .addOnSuccessListener { barcode ->

                        println("scan success")

                        launch {
                            send(getBarcodeValue(barcode))
                        }


            }
                .addOnFailureListener {
                    println("scan fail" + it)
                    it.printStackTrace()
                }

            awaitClose()
        }
    }

    private fun getBarcodeValue(barcode: com.google.mlkit.vision.barcode.common.Barcode):String? {
        println("barcode: " +barcode)
        return barcode.rawValue
    }


}