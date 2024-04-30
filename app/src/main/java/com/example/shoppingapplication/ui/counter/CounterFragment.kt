package com.example.shoppingapplication.ui.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppingapplication.databinding.FragmentCounterBinding

class CounterFragment : Fragment() {

    //use of abstraction principle to avoid exposing the changable property
//    private var _binding: FragmentCounterBinding? = null
    lateinit var binding: FragmentCounterBinding
    lateinit var viewModel: CounterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCounterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        binding.apply {
            // set the initial value
            counterValue.text = viewModel.counterValue.toString()

            minus.setOnClickListener {
                if (viewModel.counterValue > 0)
                    viewModel.counterValue--
                counterValue.text = viewModel.counterValue.toString()
            }
            plus.setOnClickListener {
                viewModel.counterValue++
                counterValue.text = viewModel.counterValue.toString()
            }
        }

    }

}