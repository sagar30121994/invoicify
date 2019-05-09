package com.invoicify.data.customer

import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.clientSubCollection
import com.invoicify.domain.customer.model.CustomerModel
import com.invoicify.domain.customer.repository.CustomerRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : CustomerRepository {

    var TAG = "CustomerRepositoryImpl"

    @SuppressLint("CheckResult")
    override fun updateCustomer(hashmap: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            emitter.onComplete()
            emitter.onError(Exception("e"))

        }
    }

    override fun getCustomers(path: String): Observable<List<CustomerModel>> {
        return Observable.create { emitter ->
            db.collection("Users").document(path).collection(path + clientSubCollection).document(path)
                    .addSnapshotListener { snapshot, e ->

                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }

                        if (snapshot!!.exists()) {
                            val result: List<CustomerModel> = snapshot as List<CustomerModel>
                            emitter.onNext(result)
                        } else {
                            emitter.onError(Exception("data on available"))
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

    override fun addNewCustomer(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            init()
            var accountKey = map.get("accountKey") as String
            var data: CustomerModel = map.get("data") as CustomerModel
            db.collection("Users").document(accountKey).collection(accountKey + clientSubCollection).document(accountKey)
                    .set(data)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { e ->
                        emitter.onError(e)
                        Log.e(TAG, e.localizedMessage)
                    }


        }
    }

}






