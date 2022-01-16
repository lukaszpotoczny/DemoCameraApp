package com.example.democameraapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.democameraapp.R
import com.example.democameraapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToViewModel()
        binding.signInButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.signIn()
        }
    }

    private fun subscribeToViewModel() {
        viewModel.loginSuccessfulLiveData.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                binding.progressBar.apply {
                    visibility = View.GONE
                    findNavController().navigate(R.id.action_loginFragment_to_camerasListFragment)
                }
            } else {
                // Handle error
            }
        }
    }
}