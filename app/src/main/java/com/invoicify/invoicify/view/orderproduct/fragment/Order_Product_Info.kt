package com.invoicify.invoicify.view.orderproduct.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.invoicify.data.core.InvoicePreference
import com.invoicify.domain.products.model.ProductModel
import com.invoicify.invoicify.R
import com.invoicify.invoicify.core.injector
import com.invoicify.invoicify.interfacefragment.FragmentInteraction
import com.invoicify.invoicify.utils.SnackBarUtils
import com.invoicify.invoicify.view.orderproduct.viewmodel.OrderProductViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_order__item.*


class Order_Product_Info : Fragment() {
    internal var revenueFragmentInteractor: FragmentInteraction? = null
    private val factory = injector.orderProductViewModelFactory()
    private lateinit var viewModel: OrderProductViewModel
    private val disposables = CompositeDisposable()
    private var isInclusive = false
    lateinit var invoicePreference: InvoicePreference
    var finalKey: Int = 0
    lateinit var snackBarUtils: SnackBarUtils
    var datamap: HashMap<String, Any> = HashMap()
    var uploadmap: HashMap<String, Any> = HashMap()
    var secondarymap: HashMap<String, Any> = HashMap()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentInteraction) {
            revenueFragmentInteractor = context

            revenueFragmentInteractor!!.onAttached(R.string.add_item_fragment)

        } else {
            throw RuntimeException("$context must implement revenueFragmentInteractor")
        }
    }

    override fun onDetach() {
        super.onDetach()
        revenueFragmentInteractor!!.onDettached(R.string.add_item_fragment)
        revenueFragmentInteractor = null
        disposables.dispose()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order__item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(OrderProductViewModel::class.java)

        invoicePreference = InvoicePreference(activity!!)
        snackBarUtils = SnackBarUtils(activity!!, activity!!.findViewById(R.id.mainCoordinator))

        var accountKey: String = invoicePreference.getAccountKey()

        disposables.add(
                viewModel.getAllProducts(accountKey)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {

                            uploadmap.clear()
                            datamap.clear()
                            secondarymap.clear()

                            try {
                                datamap = it.get("products") as HashMap<String, Any>

                                datamap.keys.forEach {
                                    secondarymap.put(it, datamap.get(it) as HashMap<String, Any>)
                                    finalKey = it.toInt()
                                    Log.e("index", finalKey.toString())
                                    Log.e("index", finalKey.toString() + "----" + datamap.get(it))


                                }

                                // datamap.put("products",secondarymap)

                            } catch (e: Exception) {
                                finalKey = 0
                                datamap.put("products", secondarymap)
                            }


                        }
        )


        next_button.setOnClickListener {


            var itemName = tiet_item_name.text.toString()
            var itemUnit = tiet_unit_type.text.toString()


            var Cgst: Double = 0.0
            try {
                Cgst = tiet_cgst.text.toString().toDouble()
            } catch (e: Exception) {
                Cgst = 0.0
                til_cgst.error = "Enter valid CGST"
            }

            var Sgst: Double = 0.0
            try {
                Sgst = tiet_sgst.text.toString().toDouble()
            } catch (e: Exception) {
                Sgst = 0.0
                til_sgst.error = "Enter valid SGST"


            }

            var Igst: Double = 0.0
            try {
                Igst = tiet_igst.text.toString().toDouble()
            } catch (e: Exception) {
                Igst = 0.0
                til_igst.error = "Enter valid IGST"

            }

            var itemQuantity: Int = 0
            try {
                itemQuantity = tiet_quanty.text.toString().toInt()
            } catch (e: Exception) {
                itemQuantity = 0
                til_stock.error = "Enter valid stock quantity"
            }

            var prize: Double = 0.0
            try {
                prize = tiet_unit_cost.text.toString().toDouble()
            } catch (e: Exception) {
                prize = 0.0
                til_unit_cost.error = "Enter valid prise"
            }


            var productModel = ProductModel(
                    itemName,
                    itemQuantity,
                    itemUnit,
                    isInclusive,
                    Cgst,
                    Sgst,
                    Igst,
                    prize,
                    5)


            var maindata: HashMap<String, Any> = HashMap()

            maindata["item_name"] = productModel.item_name
            maindata["stock"] = productModel.item_stock
            maindata["tax_type"] = productModel.item_tax_type
            maindata["prize"] = productModel.item_prize
            maindata["unit_type"] = productModel.item_unit
            maindata["low_quantity"] = productModel.low_quantity
            maindata["cgst"] = productModel.cgst
            maindata["sgst"] = productModel.sgst
            maindata["igst"] = productModel.igst




            secondarymap.keys.forEach {
                if ((finalKey) <= it.toInt()) {
                    finalKey = it.toInt() + 1
                }

            }

            secondarymap.put((finalKey).toString(), maindata)


            uploadmap.put("products", secondarymap)

            Log.e("datamap data", uploadmap.toString())
            var map: HashMap<String, Any> = HashMap()
            map.put("accountKey", accountKey)
            map.put("data", uploadmap)



            disposables.add(
                    viewModel.validateDetails(productModel)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {

                                if (it == 1) {
                                    til_item_name.error = "Enter valid porduct name"
                                }
                                if (it == 2) {
                                    til_unit_cost.error = "Enter valid prise"

                                }
                                if (it == 3) {
                                    til_stock.error = "Enter valid stock quantity"
                                }

                                if (it == 4) {
                                    snackBarUtils.displayMaterialSnackBar("Enter valid low quantity warning")

                                }
                                if (it == 5) {
                                    til_cgst.error = "Enter valid CGST"
                                }
                                if (it == 6) {
                                    til_sgst.error = "Enter valid SGST"

                                }
                                if (it == 7) {
                                    til_igst.error = "Enter valid IGST"

                                }

                                if (it == 99) {
                                    snackBarUtils.displayMaterialSnackBar("Please enter valid details")
                                }

                                if (it == 999) {

                                    viewModel.addNewProduct(map).observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(
                                                    {
                                                        Log.e("product model", "update loaded")
                                                        snackBarUtils.displayMaterialSnackBar("Product adeed succesfully")
                                                        datamap.clear()
                                                        uploadmap.clear()
                                                        clearData()

                                                    }
                                                    ,
                                                    {
                                                        snackBarUtils.displayMaterialSnackBar("Error while adding")
                                                        Log.e("product model ", "failed" + it.localizedMessage)
                                                        datamap.clear()
                                                        secondarymap.clear()

                                                    })
                                }
                            }
            )
        }
    }

    private fun clearData() {
        tiet_item_name.setText("")
        tiet_quanty.setText("")
        tiet_unit_type.setText("")
        tiet_cgst.setText("")
        tiet_sgst.setText("")
        tiet_igst.setText("")
        tiet_unit_cost.setText("")
    }


}