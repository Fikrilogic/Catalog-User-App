package com.fikrisandi.loginapp.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.fikrisandi.loginapp.R
import com.fikrisandi.loginapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginStatus.collectLatest {
                    if (it) {
                        findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
                    }
                }
            }
        }


        binding.apply {
            btnSignIn.setOnClickListener {
                val email = loginEmailTexfield.editText?.text.toString()
                val password = loginPasswordTexfield.editText?.text.toString()
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.signIn(
                        email = email,
                        password = password,
                    ) {
                        if (it) {
                            Toast.makeText(requireContext(), "Berhasil Login", Toast.LENGTH_SHORT)
                                .show()
                            viewLifecycleOwner.lifecycleScope.launch {
                                delay(1000)
                                findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
                            }
                        } else {
                            Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

//                observeLoginStatus()
            }
        }


        return binding.root
    }

    private fun observeLoginStatus() = viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.loginStatus.collectLatest {
                if (it) {
                    Toast.makeText(requireContext(), "Berhasil Login", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Login Gagal", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

}