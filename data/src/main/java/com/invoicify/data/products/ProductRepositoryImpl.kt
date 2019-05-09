package com.invoicify.data.products

import com.google.firebase.firestore.FirebaseFirestore
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.itemsSubCollection
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.userCollection
import com.invoicify.domain.products.model.ProductModel
import com.invoicify.domain.products.repository.ProductRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : ProductRepository {
    override fun addNewProduct(hashMap: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->


            var accountKey: String = hashMap.get("accountKey") as String
            var data = hashMap.get("data") as HashMap<String, Any>

            db.collection(userCollection).document(accountKey).collection(accountKey + itemsSubCollection).document(accountKey)
                    .update(data)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }

        }
    }

    override fun updateProduct(hashMap: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            var accountKey: String = hashMap.get("accountKey") as String
            var data: ProductModel = hashMap.get("data") as ProductModel
            var index: String = hashMap.get("key") as String
            db.collection(userCollection).document(accountKey).collection(accountKey + itemsSubCollection).document(accountKey)
                    .update((index to data) as MutableMap<String, Any>)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }

        }
    }

    override fun deleteProduct(hashMap: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            var accountKey: String = hashMap.get("accountKey") as String
            var data: ProductModel = hashMap.get("data") as ProductModel
            var index: String = hashMap.get("index") as String
            db.collection(userCollection).document(accountKey).collection(accountKey + itemsSubCollection).document(accountKey)
                    .update((index to data) as MutableMap<String, Any>)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }

        }
    }

    override fun getProduct(t: String): Observable<MutableMap<String, Any>> {
        return Observable.create { emitter ->

            db.collection(userCollection).document(t).collection(t + itemsSubCollection).document(t)
                    .addSnapshotListener { snapshot, e ->

                        if (e != null) {
                            emitter.onError(e)
                            return@addSnapshotListener
                        }

                        if (snapshot!!.exists()) {
                            var productModel1: MutableMap<String, Any> = snapshot.data!!.toMutableMap()

                            emitter.onNext(productModel1)

                        } else {
                            emitter.onError(Exception("Data Not Available"))

                        }

                    }
        }
    }


}