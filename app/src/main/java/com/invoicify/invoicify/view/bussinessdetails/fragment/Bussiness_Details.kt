package com.invoicify.invoicify.view.bussinessdetails.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.invoicify.data.core.InvoicePreference
import com.invoicify.data.sharepreference.Constants.Companion.primarytype
import com.invoicify.data.sharepreference.Constants.Companion.secondarytype
import com.invoicify.data.sharepreference.Constants.Companion.ternarytype
import com.invoicify.domain.user.model.UserModel
import com.invoicify.invoicify.R
import com.invoicify.invoicify.activity.home.view.Home
import com.invoicify.invoicify.core.injector
import com.invoicify.invoicify.view.bussinessdetails.viewmodel.BussinessViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_bussiness__details.*


class Bussiness_Details : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null


    var isUserExists = false
    var isValidate: Boolean = true
    /**
     * Called immediately after [.onCreateView]
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by [.onCreateView].
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    internal lateinit var next_button: MaterialButton

    private val factory = injector.bussinessViewModelFactory()
    private lateinit var viewModel: BussinessViewModel
    private val disposables = CompositeDisposable()

    lateinit var invoicePreference: InvoicePreference


    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        invoicePreference = InvoicePreference(activity!!)
        invoicePreference.init()


        if (!invoicePreference.getAccountKey().equals("none", true)) {
            activity!!.startActivity(Intent(activity, Home::class.java))
            activity!!.finish()
        }

        viewModel = ViewModelProviders.of(this, factory).get(BussinessViewModel::class.java)
        tiet_personal_date_of_birth.setOnClickListener {
            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                tiet_personal_date_of_birth.setText(" " + dayOfMonth + " /" + (monthOfYear + 1) + " /" + year)
            }, 2000, 0, 1)
            dpd.show()
        }

        tiet_date.setOnClickListener {
            val dpd = DatePickerDialog(activity, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                tiet_date.setText(" " + dayOfMonth + " /" + (monthOfYear + 1) + " /" + year)
            }, 2000, 0, 1)
            dpd.show()
        }
        tiet_personal_name.setText(arguments!!.getString("name"))
        tiet_personal_email.setText(arguments!!.getString("email"))
        tiet_personal_phone.setText(arguments!!.getString("phoneNumber"))
        tiet_personal_date_of_birth.setText(arguments!!.getString("userId"))



        til_gstn.editText!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                var documentPath = s.toString()
                Log.d("USerDetails", count.toString())

                if (documentPath.length == 15) {

                    progress_circular.visibility = View.VISIBLE
                    disposables.add(
                            viewModel.getUserDetails(documentPath)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe({
                                        tiet_email.setText(it.bussiness_Email)
                                        var map: HashMap<String, String> = it.Admins.get("primary") as HashMap<String, String>
                                        var primaryEmail = map.get("email")
                                        var ternaryEmail = ""
                                        var secondaryEmail = ""
                                        try {
                                            map = it.Admins.get("secondary")!!
                                            secondaryEmail = map.get("email") as String
                                        } catch (e: Exception) {
                                            secondaryEmail = ""
                                        }

                                        try {
                                            map = it.Admins.get("ternary")!!
                                            ternaryEmail = map.get("email") as String
                                        } catch (e: Exception) {
                                            var ternaryEmail = ""

                                        }

                                        if (tiet_personal_email.text.toString().contentEquals(primaryEmail!!)) {
                                            Log.e(TAG, "primary admin")
                                            invoicePreference.setAdminType(primarytype)

                                            tiet_personal_name.setText(it.Admins.get("primary")!!.get("name"))
                                            tiet_personal_email.setText(it.Admins.get("primary")!!.get("email"))
                                            tiet_personal_phone.setText(it.Admins.get("primary")!!.get("mobile"))
                                            tiet_personal_date_of_birth.setText(it.Admins.get("primary")!!.get("date"))

                                        } else if (tiet_personal_email.text.toString().contentEquals(secondaryEmail)) {
                                            Log.e(TAG, "secondary admin")
                                            invoicePreference.setAdminType(secondarytype)

                                            tiet_personal_name.setText(it.Admins.get("secondary")!!.get("name"))
                                            tiet_personal_email.setText(it.Admins.get("secondary")!!.get("email"))
                                            tiet_personal_phone.setText(it.Admins.get("secondary")!!.get("mobile"))
                                            tiet_personal_date_of_birth.setText(it.Admins.get("secondary")!!.get("date"))

                                        } else if (tiet_personal_email.text.toString().contentEquals(ternaryEmail)) {
                                            Log.e(TAG, "ternary admin")
                                            invoicePreference.setAdminType(ternarytype)
                                            tiet_personal_name.setText(it.Admins.get("ternary")!!.get("name"))
                                            tiet_personal_email.setText(it.Admins.get("ternary")!!.get("email"))
                                            tiet_personal_phone.setText(it.Admins.get("ternary")!!.get("mobile"))
                                            tiet_personal_date_of_birth.setText(it.Admins.get("ternary")!!.get("date"))

                                        }


                                        tiet_company_name.setText(it.companyName)

                                        tiet_date.setText(it.establishedDate)
                                        tiet_contact.setText(it.Contact)
                                        tiet_email.setText(it.bussiness_Email)
                                        tiet_address_line_1.setText(it.Address.get("line1"))
                                        tiet_address_line_2.setText(it.Address.get("line2"))

                                        tiet_pin.setText(it.Address.get("pincode"))
                                        tiet_state.setText(it.Address.get("state"))
                                        tiet_city.setText(it.Address.get("city"))
                                        tiet_contry.setText(it.Address.get("contry"))

                                        invoicePreference.setAccountKey(tiet_gstn.text.toString())

                                        progress_circular.visibility = View.GONE



                                        isUserExists = true
                                    }, {
                                        Log.e("USerDetails", it.localizedMessage)
                                        progress_circular.visibility = View.GONE
                                        if (it.localizedMessage.toString().contentEquals("User Not Exist")) {
                                            isUserExists = false
                                            progress_circular.visibility = View.GONE

                                        }
                                    })
                    )
                }
            }
        })



        next_button = view.findViewById(R.id.next_button)
        next_button.setOnClickListener {


            isValidate = true
            if (!isUserExists) {


                if (!tiet_personal_name.text.isNullOrEmpty()) {

                }


                if (tiet_personal_phone.text.isNullOrEmpty()) {
                    til_personal_phone.error = "Enter valid number"
                    isValidate = false

                }

                if (tiet_personal_date_of_birth.text.isNullOrEmpty()) {
                    til_personal_date_of_birth.error = "Enter valid date"
                    isValidate = false

                }



                if (!tiet_personal_email.text.isNullOrBlank()) {
                    if (!tiet_personal_email.text!!.matches(Regex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$"))) {
                        til_personal_email.error = "Enter valid email"
                        isValidate = false

                    }
                }


                if (!tiet_company_name.text.isNullOrBlank()) {

                } else {
                    isValidate = false
                    til_company_name.error = "Enter valid orgnization  name"

                }





                if (!tiet_gstn.text.isNullOrEmpty()) {
                    if (!tiet_gstn.text!!.matches(Regex("^[0-9]{15}\$"))) {
                        til_gstn.error = "Enter valid GSTN"
                        isValidate = false
                    }
                } else {
                    isValidate = false
                    til_gstn.error = "Enter GSTN"
                }

                if (!tiet_email.text.isNullOrEmpty()) {
                    if (!tiet_email.text!!.matches(Regex("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}\$"))) {
                        isValidate = false
                        til_email.error = "Enter valid email"
                    }
                }





                if (!tiet_contact.text.isNullOrEmpty()) {
                    if (!tiet_contact.text!!.matches(Regex("^[+ 0-9]{6,10}\$"))) {
                        isValidate = false
                        til_contact_no.error = "Enter valid contact"
                    }
                }



                if (!tiet_pin.text.isNullOrEmpty()) {
                }
                if (isValidate) {
                    var MainMapForAddree: HashMap<String, String> = HashMap<String, String>()
                    MainMapForAddree.put("city", tiet_city.text.toString())
                    MainMapForAddree.put("line1", tiet_address_line_1.text.toString())
                    MainMapForAddree.put("line2", tiet_address_line_2.text.toString())
                    MainMapForAddree.put("state", tiet_state.text.toString())
                    MainMapForAddree.put("contry", tiet_contry.text.toString())
                    MainMapForAddree.put("pin", tiet_pin.text.toString())

                    invoicePreference.setAccountKey(tiet_gstn.text.toString())


                    var mainAdminMap: HashMap<String, HashMap<String, String>> = HashMap<String, HashMap<String, String>>()

                    var primaryadmin: HashMap<String, String> = HashMap<String, String>()
                    var subscription: HashMap<String, String> = HashMap<String, String>()

                    subscription.put("last_paid", "")
                    subscription.put("last_renew", "")
                    subscription.put("type", "")


                    primaryadmin.put("date", tiet_personal_date_of_birth.text.toString())
                    primaryadmin.put("email", tiet_personal_email.text.toString())
                    primaryadmin.put("mobile", tiet_personal_phone.text.toString())
                    primaryadmin.put("name", tiet_personal_name.text.toString())
                    mainAdminMap.put("primary", primaryadmin)

                    var userModel: UserModel = UserModel(
                            tiet_company_name.text.toString(),
                            tiet_gstn.text.toString(),
                            tiet_date.text.toString(),
                            tiet_email.text.toString(),
                            tiet_contact.text.toString(),
                            MainMapForAddree,
                            "",
                            mainAdminMap,
                            subscription,
                            ""
                    )

                    addNewUser(userModel)
                    activity!!.startActivity(Intent(activity, Home::class.java))
                    activity!!.finish()

                }

            } else {

                activity!!.startActivity(Intent(activity, Home::class.java))
                activity!!.finish()

            }


        }
    }


    fun addNewUser(userModel: UserModel) {


        disposables.add(
                viewModel.setUserData(userModel)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    Log.e("USerDetails", "Details Added")

                                },
                                { Log.e("USerDetails", "Filed to add details  " + it.localizedMessage) }
                        ))


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bussiness__details, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event


    companion object {

        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        private val TAG = "PdfCreatorActivity"
    }


}// Required empty public constructor
