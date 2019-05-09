package com.invoicify.data.suplier

import com.google.firebase.firestore.FirebaseFirestore
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.suplierSubCollection
import com.invoicify.data.firestoredetails.CollectionInfo.Companion.userCollection
import com.invoicify.domain.suplier.model.SuplierModel
import com.invoicify.domain.suplier.repository.SuplierRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SuplierRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : SuplierRepository {
    override fun addNewSuplier(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            var accountKey: String = map.get("accountKey") as String
            var data: SuplierModel = map.get("data") as SuplierModel
            db.collection(userCollection).document(accountKey).collection(accountKey + suplierSubCollection).document(accountKey)
                    .set(data)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }


        }
    }

    override fun updateSuplier(map: HashMap<String, Any>): Completable {
        return Completable.create { emitter ->
            var accountKey: String = map.get("accountKey") as String
            var data: SuplierModel = map.get("data") as SuplierModel
            var index: String = map.get("index") as String
            db.collection(userCollection).document(accountKey).collection(accountKey + suplierSubCollection).document(accountKey)
                    .update((index to data) as MutableMap<String, Any>)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }


        }
    }

    override fun getSupliers(t: String): Observable<List<SuplierModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}