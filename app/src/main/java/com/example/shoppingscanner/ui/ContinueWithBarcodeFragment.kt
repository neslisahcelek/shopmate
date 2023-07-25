package com.example.shoppingscanner.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shoppingscanner.R
import com.example.shoppingscanner.databinding.FragmentContinueWithBarcodeBinding
import com.google.android.material.button.MaterialButton


class ContinueWithBarcodeFragment : Fragment() {

    private var visible: Boolean = false
    private var fullscreenContent: View? = null
    private var fullscreenContentControls: View? = null

    private var _binding: FragmentContinueWithBarcodeBinding? = null
    private var continueWithBarcodeButton: Button? = null
    private var cameraRequest:ActivityResultLauncher<String>?= null
    private var cardView:CardView? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContinueWithBarcodeBinding.inflate(inflater, container, false)
        continueWithBarcodeButton = binding.btncontinuewithbarcode
        (continueWithBarcodeButton as MaterialButton).setOnClickListener(){
            onclick(it)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visible = true
        cardView = binding.cardView
    }

    fun onclick(view:View) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            Log.d("permission", "denied ")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                123
            )
        } else {
            Log.d("permission", "granted ")
            navigateToBarcodeScannerFragment(view)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 123) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("permission", "granted ")
                navigateToBarcodeScannerFragment(requireView())
            } else {
                Log.d("permission", "denied ")
            }
        }
    }

    fun navigateToBarcodeScannerFragment(view:View) {
        Log.d("navigate", "to barcode scanner fragment")
        val action = ContinueWithBarcodeFragmentDirections.actionContinueWithBarcodeFragmentToBarcodeScannerFragment()
        Navigation.findNavController(view).navigate(action)
    }


    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
        continueWithBarcodeButton = null
        fullscreenContent = null
        fullscreenContentControls = null
        cardView = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}