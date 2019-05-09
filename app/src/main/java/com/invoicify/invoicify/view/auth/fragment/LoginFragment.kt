package com.invoicify.invoicify.view.auth.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

import com.invoicify.invoicify.R
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    private val RC_SIGN_IN = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank_fragment3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {

            val bundle = Bundle()
            bundle.putString("email", auth.currentUser!!.email.toString())
            bundle.putString("phoneNumber", auth.currentUser!!.phoneNumber.toString())
            bundle.putString("name", auth.currentUser!!.displayName.toString())
            bundle.putString("providerId", auth.currentUser!!.providerId.toString())
            bundle.putString("pictureUrl", auth.currentUser!!.photoUrl.toString())

            view.findNavController().navigate(R.id.nav_bussiness_details, bundle)

        } else {

            /*  ActionCodeSettings actionCodeSettings = ActionCodeSettings.newBuilder()
                  .setAndroidPackageName(*//*yourPackageName*//*, *//*installIfNotAvailable*//*true, *//*minimumVersion*//*null)
                .setHandleCodeInApp(true)
                .setUrl("https://google.com") // This URL needs to be whitelisted
                .build();*/

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setLogo(R.mipmap.ic_launcher)
                            .setAvailableProviders(
                                    Arrays.asList(
                                            AuthUI.IdpConfig.GoogleBuilder().build(),
                                            AuthUI.IdpConfig.EmailBuilder().build(),
                                            AuthUI.IdpConfig.PhoneBuilder().build()
                                    )
                            )
                            .setIsSmartLockEnabled(false, true)
                            .setTosAndPrivacyPolicyUrls(
                                    "https://superapp.example.com/terms-of-service.html",
                                    "https://superapp.example.com/privacy-policy.html"
                            )

                            .build(),
                    RC_SIGN_IN
            )
        }


    }


    @SuppressLint("RestrictedApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {

                val bundle = Bundle()
                bundle.putString("email", response!!.user.email)
                bundle.putString("phoneNumber", response!!.user.phoneNumber)
                bundle.putString("name", response!!.user.name)
                bundle.putString("providerId", response!!.user.providerId)
                bundle.putString("pictureUrl", response!!.user.photoUri.toString())
                Log.e("Main Activity", "picture uri: " + response!!.user.photoUri.toString())
                findNavController().navigate(R.id.nav_bussiness_details, bundle)

                Log.e("Main Activity", "Sign-in error:  OK")

            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.e("Main Activity", "Sign-in error:  Canceled")
                    return
                }

                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    Log.e("Main Activity", "Sign-in error:  No Network")
                    return
                }

                //   showSnackbar(R.string.unknown_error)
                Log.e("Main Activity", "Sign-in error: ", response.error)
            }
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onCheckLoginStatus(flag: Boolean) {
        listener?.isAlreadyLogin(flag)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun isAlreadyLogin(flag: Boolean)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                LoginFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
