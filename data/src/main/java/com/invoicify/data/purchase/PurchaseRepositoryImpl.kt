package com.invoicify.data.purchase

import com.google.firebase.firestore.FirebaseFirestore
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.purchaseSubCollection
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.userCollection
import com.invoicify.domain.purchase.model.PurchaseModel
import com.invoicify.domain.purchase.repository.PurchaseRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class PurchaseRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : PurchaseRepository {
    override fun updatePurchase(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            var accountKey: String = map.get("accountKey") as String
            var data: PurchaseModel = map.get("data") as PurchaseModel
            var index: String = map.get("index") as String
            db.collection(userCollection).document(accountKey).collection(accountKey + purchaseSubCollection).document(accountKey)
                    .update((index to data) as MutableMap<String, Any>)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }
        }
    }

    override fun getPurchase(path: String): Observable<List<PurchaseModel>> {
        return Observable.create { emitter ->
            db.collection(userCollection).document(path).collection(path + purchaseSubCollection).document(path)
                    .addSnapshotListener { snapshot, e ->
                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }
                        if (snapshot!!.exists())
                            emitter.onNext(snapshot as List<PurchaseModel>
                            ) else {
                            emitter.onError(Exception("data not available"))
                        }

                    }

        }

    }

    override fun deletePurchase(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->

            var path: String = map.get("accountKey") as String
            db.collection(userCollection).document(path).collection(path + purchaseSubCollection).document(path)
                    .delete()
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }


        }
    }

    override fun addNewPurchase(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            var path: String = map.get("accountKey") as String
            var data: PurchaseModel = map.get("data") as PurchaseModel

            db.collection(userCollection).document(path).collection(path + purchaseSubCollection).document(path)
                    .set(data)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }


        }

    }
}