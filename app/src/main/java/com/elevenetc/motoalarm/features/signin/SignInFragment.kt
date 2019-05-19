package com.elevenetc.motoalarm.features.signin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.elevenetc.motoalarm.R
import com.elevenetc.motoalarm.core.user.UserManagerImpl
import com.elevenetc.motoalarm.core.utils.RxUtils


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
            render(it)
        }

        viewModel.intent(RxUtils.btnObservable(btnSignIn) {
            SignInViewModel.Intents.SignIn(
                    editEmail.text.toString(),
                    editPassword.text.toString()
            )
        })

//        viewModel.intent(
//                Observable.merge(
//                        Observable.just(SignInViewModel.Intents.Idle),
//                        RxUtils.btnObservable(btnSignIn) { SignInViewModel.Intents.SignIn }
//                )
//        )

    }

    private fun render(it: SignInViewModel.States?) {
        Log.d("render", it.toString())
        if (it is SignInViewModel.States.Progress) {
            view!!.findViewById<TextView>(R.id.edit_password).isEnabled = false
            view!!.findViewById<TextView>(R.id.edit_email).isEnabled = false
            view!!.findViewById<View>(R.id.btn_sing_in).isEnabled = false
        } else if (it is SignInViewModel.States.Success) {

        } else if (it is SignInViewModel.States.Error) {
            view!!.findViewById<TextView>(R.id.edit_password).isEnabled = true
            view!!.findViewById<TextView>(R.id.edit_email).isEnabled = true
            view!!.findViewById<View>(R.id.btn_sing_in).isEnabled = true
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SignInFragment()
    }
}
