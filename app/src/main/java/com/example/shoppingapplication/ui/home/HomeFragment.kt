package com.example.shoppingapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.shoppingapplication.R
import com.example.shoppingapplication.databinding.FragmentHomeBinding
import com.example.shoppingapplication.ui.LoginActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        lifecycleScope.launch {

            homeViewModel.dataShareState.collect {

            }

        }

        textView.setOnClickListener {
//            findNavController().navigate(R.id.action_nav_home_to_counterFragment)
            // log a custom analytic event
            Firebase.analytics.logEvent(
                FirebaseAnalytics.Event.SELECT_CONTENT,
                bundleOf(
                    FirebaseAnalytics.Param.ITEM_ID to "232",
                    FirebaseAnalytics.Param.ITEM_NAME to "Number",
                    FirebaseAnalytics.Param.CONTENT_TYPE to "Dummy",
                )
            )

            // Crash our application
//            throw RuntimeException("Test Crash") // Force a crash

            // Sign out the user and send back to login
            FirebaseAuth.getInstance().signOut()
            AuthUI.getInstance()
                .signOut(requireActivity())
                .addOnCompleteListener {
                    // ...
                    startActivity(Intent(activity, LoginActivity::class.java)).also { activity?.finish() }
                }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}