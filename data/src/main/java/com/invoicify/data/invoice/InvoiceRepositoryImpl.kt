package com.invoicify.data.invoice

import com.google.firebase.firestore.FirebaseFirestore
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.invoiceSubCollection
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.userCollection
import com.invoicify.domain.invoice.model.InvoiceModel
import com.invoicify.domain.invoice.repository.InvoiceRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class InvoiceRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : InvoiceRepository {
    override fun getInvoice(path: HashMap<String, String>): Observable<List<InvoiceModel>> {
        return Observable.create { emitter ->
            val accountKey: String = path.get("accountKey") as String
            val absolutepath: String = path.get("path") as String

            db.collection(userCollection).document(accountKey).collection(accountKey + invoiceSubCollection).document(absolutepath)
                    .addSnapshotListener { snapshot, e ->
                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }

                        if (snapshot!!.exists()) {
                            val result: List<InvoiceModel> = snapshot as List<InvoiceModel>
                            emitter.onNext(result)
                        } else {
                            emitter.onError(Exception("data not availbale"))
                        }
                    }

        }
    }

    override fun newInvoice(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            val accountKey: String = map.get("acccountKey") as String
            val absolutePath: String = map.get("absolutePath") as String
            val data: InvoiceModel = map.get("data") as InvoiceModel
            db.collection(userCollection).document(accountKey).collection(accountKey + invoiceSubCollection).document(absolutePath)
                    .set(data)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }
        }
    }

    override fun updateInvoice(t: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            val accountKey: String = t.get("acccountKey") as String
            val absolutePath: String = t.get("absolutePath") as String
            val data: InvoiceModel = t.get("data") as InvoiceModel
            val index: String = t.get("index") as String
            db.collection(userCollection).document(accountKey).collection(accountKey + invoiceSubCollection).document(absolutePath)
                    .update((index to data) as MutableMap<String, Any>)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }
        }
    }
}