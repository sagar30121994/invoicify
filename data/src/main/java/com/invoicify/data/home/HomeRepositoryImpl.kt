package com.invoicify.data.home

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.clientSubCollection
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.invoiceSubCollection
import com.invoicify.domain.home.model.*
import com.invoicify.domain.home.repository.HomeRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : HomeRepository {

    override fun getLast3Products(documentPath: HashMap<String, String>): Single<Last3ProductsWithLowquantity> {
        return Single.create { emmiter ->
            init()
            val ref = db.collection("Users/${documentPath.get("accountkey")}/${documentPath.get("accountkey")}$invoiceSubCollection/").document(documentPath.get("documentpath").toString())
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        emmiter.onSuccess(Last3ProductsWithLowquantity(
                        ))
                    } else {
                        emmiter.onError(Exception("Data Not Available"))

                    }
                } else {
                    emmiter.onError(task.exception!!)
                }

            }
        }

    }

    fun init() {
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        db.firestoreSettings = settings
    }

    override fun getThidMonthsTotal(documentPath: String): Observable<ThisMonthsTotalModel> {
        return Observable.create { emmiter ->
            init()
            val ref = db.collection("Users").document(documentPath).collection(documentPath + invoiceSubCollection).document(documentPath)
            ref.addSnapshotListener { snapshot, e ->


                if (e != null) {
                    emmiter.onError(e)
                    return@addSnapshotListener
                }

                if (snapshot!!.exists()) {
                    emmiter.onNext(ThisMonthsTotalModel(
                            metaData = snapshot.get("metadata") as ArrayList<HashMap<String, String>>
                    ))
                } else {
                    emmiter.onError(Exception("Data Not Available"))

                }


            }
        }
    }

    override fun getToadysSoFar(documentPath: HashMap<String, String>): Observable<TodaysSoFarModel> {
        return Observable.create { emmiter ->
            init()

            val ref = db.collection("Users").document(documentPath.get("accountkey")!!).collection(documentPath.get("accountkey") + invoiceSubCollection).document(documentPath.get("path")!!)
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        emmiter.onNext(TodaysSoFarModel(
                                invoices = document.get("invoices") as ArrayList<HashMap<String, String>>
                        ))
                    } else {
                        emmiter.onError(Exception("Data Not Available"))

                    }
                } else {
                    emmiter.onError(task.exception!!)
                }

            }
        }
    }

    override fun getLast3Invoices(documentPath: HashMap<String, String>): Single<Last3InvoicesModel> {
        return Single.create { emmiter ->
            init()
            val ref = db.collection("Users/${documentPath.get("accountkey")}/${documentPath.get("accountkey")}$invoiceSubCollection/").document(documentPath.get("documentpath").toString())
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        emmiter.onSuccess(Last3InvoicesModel(
                                last3Invoice = document.get("invoices") as HashMap<String, HashMap<String, String>>
                        ))
                    } else {
                        emmiter.onError(Exception("Data Not Available"))

                    }
                } else {
                    emmiter.onError(task.exception!!)
                }

            }
        }

    }

    override fun getLast3Customers(documentPath: HashMap<String, String>): Single<Last3Customers> {
        return Single.create { emmiter ->
            init()
            val ref = db.collection("Users/${documentPath.get("accountkey")}/${documentPath.get("accountkey")}$clientSubCollection/").document(documentPath.get("documentpath").toString())
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        emmiter.onSuccess(Last3Customers(
                                last3Customers = document.get("invoices") as HashMap<String, HashMap<String, String>>
                        ))
                    } else {
                        emmiter.onError(Exception("Data Not Available"))

                    }
                } else {
                    emmiter.onError(task.exception!!)
                }

            }
        }
    }


}