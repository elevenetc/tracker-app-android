package com.elevenetc.motoalarm.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.ui.BaseFragment
import com.elevenetc.motoalarm.core.utils.RxUtils.Companion.btnObservable


class LogInFragment : BaseFragment() {

    private lateinit var viewModel: LogInViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val btnLogIn = view.findViewById<View>(R.id.btn_login)
        val btnRegister = view.findViewById<View>(R.id.btn_register)
        val editEmail = view.findViewById<TextView>(R.id.edit_email)
        val editPassword = view.findViewById<TextView>(R.id.edit_password)

        viewModel = components.signIn().viewModel()

        subs.add(viewModel.stateStream().subscribe { render(it) })

        viewModel.intent(btnObservable(btnLogIn) {
            LogInViewModel.Intents.LogIn(editEmail.text.toString(), editPassword.text.toString())
        })

        viewModel.intent(btnObservable(btnRegister) {
            LogInViewModel.Intents.Register(editEmail.text.toString(), editPassword.text.toString())
        })
    }

    private fun render(it: LogInViewModel.States?) {
        when (it) {
            is LogInViewModel.States.Progress -> {
                view!!.findViewById<TextView>(R.id.edit_password).isEnabled = false
                view!!.findViewById<TextView>(R.id.edit_email).isEnabled = false
                view!!.findViewById<View>(R.id.btn_login).isEnabled = false
            }
            is LogInViewModel.States.Success -> {
                components.navigation().onLoggedIn()
            }
            is LogInViewModel.States.Error -> {
                it.error.printStackTrace()
                view!!.findViewById<TextView>(R.id.edit_password).isEnabled = true
                view!!.findViewById<TextView>(R.id.edit_email).isEnabled = true
                view!!.findViewById<View>(R.id.btn_login).isEnabled = true
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LogInFragment()
    }
}
