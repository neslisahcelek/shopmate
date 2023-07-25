package com.example.shoppingscanner.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.shoppingscanner.R
import com.example.shoppingscanner.databinding.FragmentDontWaitBinding
import com.google.android.material.button.MaterialButton

class DontWaitFragment : Fragment() {
    private val hideHandler = Handler(Looper.myLooper()!!)

    @Suppress("InlinedApi")
    private val hidePart2Runnable = Runnable {
        (activity as? AppCompatActivity)?.supportActionBar?.hide()
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        fullscreenContentControls?.visibility = View.VISIBLE
    }
    private var visible: Boolean = false
    private var fullscreenContent: View? = null
    private var fullscreenContentControls: View? = null

    private var _binding: FragmentDontWaitBinding? = null
    private var dontWaitButton: Button? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDontWaitBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        visible = true

        dontWaitButton = binding.btndontwait
        (dontWaitButton as MaterialButton).setOnClickListener{
            navigate(view)
        }

    }

    fun navigate(view:View){
        val action = DontWaitFragmentDirections.actionDontWaitFragmentToContinueWithBarcodeFragment()
        Navigation.findNavController(view).navigate(action)
    }
    override fun onResume() {
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
        dontWaitButton = null
        fullscreenContent = null
        fullscreenContentControls = null
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}