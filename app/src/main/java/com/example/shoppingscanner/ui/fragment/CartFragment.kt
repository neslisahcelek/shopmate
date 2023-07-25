package com.example.shoppingscanner.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.shoppingscanner.adapter.ProductAdapter
import com.example.shoppingscanner.databinding.FragmentCartBinding
import com.example.shoppingscanner.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var visible: Boolean = false
    private var cartText:TextView? = null
    private var payButton: Button? = null

    private lateinit var adapter: ProductAdapter

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartProducts  = viewModel.cartProducts
        viewModel.cartProducts.clear()
        viewModel.cartProducts.putAll(cartProducts)
        adapter= ProductAdapter(viewModel.cartProducts)

        visible = true
        Log.d("cart", "onViewCreated: ${viewModel.cartProducts} ")


        with(binding) {
            cartRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            cartRecyclerView.adapter = adapter
            Log.d("cart", "onViewCreated: ${viewModel.cartProducts} $adapter")
            cartText  = myCart
            payButton = btnPayWithHadi
        }

        payButton?.setOnClickListener(View.OnClickListener {
            navigateToPay(it)
        })
    }

    private fun navigateToPay(view: View) {
        val intent = CartFragmentDirections.actionCartFragmentToPaymentCompletedFragment()
        Navigation.findNavController(view).navigate(intent)
    }


    override fun onResume() {
        super.onResume()
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    override fun onPause() {
        super.onPause()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        activity?.window?.decorView?.systemUiVisibility = 0
    }

    override fun onDestroy() {
        super.onDestroy()
        cartText = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}