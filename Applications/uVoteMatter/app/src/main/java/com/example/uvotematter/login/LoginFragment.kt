package com.example.uvotematter.login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.uvotematter.Constants
import com.example.uvotematter.databinding.FragmentLoginBinding
import com.example.uvotematter.models.User
import com.google.firebase.database.*

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    private lateinit var ref: DatabaseReference
    private lateinit var listOfUsers: List<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        checkFirstTimeLogin()
        listOfUsers = listOf()

        val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.listOfUsers.observe(viewLifecycleOwner, Observer {
            listOfUsers = it
        })

        ref = FirebaseDatabase.getInstance().getReference("users")
        binding.nextButton.setOnClickListener {
            saveUser()
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        return binding.root
    }

    private fun saveUser() {
        val nickname = binding.userName.text.toString().trim()

        if (nickname.isEmpty()) {
            binding.userName.error = "Enter a nickname"
            return
        }

        val userId = ref.push().key

        if (checkIfUserIsAlreadyRegistered(nickname)) {
            return
        }

        userId?.let { userId ->
            val user = User(userId, nickname)
            ref.child(userId).setValue(user).addOnCompleteListener {
                saveFirstTimeLogin(user.nickname)
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginToVoteList(user.nickname))
            }
        }
    }

    private fun checkIfUserIsAlreadyRegistered(nickname: String): Boolean {
        if (!listOfUsers.isNullOrEmpty()) {
            for (user in listOfUsers) {
                if (user.nickname.trim().toLowerCase() == nickname.trim().toLowerCase()) {
                    saveFirstTimeLogin(nickname)
                    this.findNavController()
                        .navigate(LoginFragmentDirections.actionLoginToVoteList(nickname))
                    return true
                }
            }
        }
        return false
    }

    private fun checkFirstTimeLogin() {
        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isLogged = prefs.getBoolean(Constants.LOGIN_KEY, false)
        if (isLogged) {
            val nicknameFromPrefs = prefs.getString(Constants.NICKNAME_KEY, null)
            nicknameFromPrefs?.let { nickname ->
                this.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginToVoteList(nickname))
            }
        }
    }

    private fun saveFirstTimeLogin(nickname: String) {
        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putBoolean(Constants.LOGIN_KEY, true)
            putString(Constants.NICKNAME_KEY, nickname)
            commit()
        }
    }

}