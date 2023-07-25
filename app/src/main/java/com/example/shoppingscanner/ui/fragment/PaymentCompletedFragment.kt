package com.example.shoppingscanner.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.shoppingscanner.adapter.ProductAdapter
import com.example.shoppingscanner.databinding.FragmentPaymentCompletedBinding
import com.example.shoppingscanner.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentCompletedFragment : Fragment() {
    private var visible: Boolean = false
    private var homeButton: Button? = null

    private lateinit var adapter: ProductAdapter

    private lateinit var viewModel: ProductViewModel

    private var _binding: FragmentPaymentCompletedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        _binding = FragmentPaymentCompletedBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartProducts  = viewModel.cartProducts
        viewModel.cartProducts.clear()
        viewModel.cartProducts.putAll(cartProducts)
        adapter= ProductAdapter(viewModel.cartProducts)

        visible = true

        with(binding) {
            cartRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            cartRecyclerView.adapter = adapter
            homeButton = btnHome
        }

        homeButton?.setOnClickListener(View.OnClickListener {
            navigateToHome(it)
        })
    }

    private fun navigateToHome(view: View) {
       val intent =
           PaymentCompletedFragmentDirections.actionPaymentCompletedFragmentToBarcodeScannerFragment()
        Navigation.findNavController(view).navigate(intent)
    }

    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        // Clear the systemUiVisibility flag
        activity?.window?.decorView?.systemUiVisibility = 0
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}