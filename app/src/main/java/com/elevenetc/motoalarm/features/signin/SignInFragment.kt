package com.elevenetc.motoalarm.features.signin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.user.UserManagerImpl


class SignInFragment : Fragment() {

    val viewModel = SignInViewModel(UserManagerImpl())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val btnSignIn = view.findViewById<View>(R.id.btn_sing_in)
        val editEmail = view.findViewById<TextView>(R.id.edit_email)
        val editPassword = view.findViewById<TextView>(R.id.edit_password)

        viewModel.stateStream().subscribe {
            if (it is SignInViewModel.States.SignInSuccess) {

            }
        }

        btnSignIn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            viewModel.intent(SignInViewModel.Intents.SignIn(email, password))
        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}
