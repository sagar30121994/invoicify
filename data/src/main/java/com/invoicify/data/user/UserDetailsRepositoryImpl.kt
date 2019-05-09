package com.invoicify.data.user

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.invoicify.domain.user.model.UserModel
import com.invoicify.domain.user.repository.UserRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDetailsRepositoryImpl @Inject constructor(private val db: FirebaseFirestore) : UserRepository {


    fun init() {
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        db.firestoreSettings = settings
    }

    override fun getUserDetails(documentPath: String): Single<UserModel> {
        return Single.create(SingleOnSubscribe<UserModel> { emitter ->
            init()
            val ref = db.collection("Users").document(documentPath)
            ref.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document!!.exists()) {
                        emitter.onSuccess(UserModel
                        (
                                companyName = document.get("companyName").toString(),
                                gstn = document.get("gstn").toString(),
                                establishedDate = document.get("establishedDate").toString(),
                                bussiness_Email = document.get("bussiness_Email").toString(),
                                Contact = document.get("contact").toString(),
                                Address = document.get("address") as HashMap<String, String>,
                                SignatureURL = document.get("signature_url").toString(),
                                Admins = document.get("admins") as HashMap<String, HashMap<String, String>>,
                                Subscription = document.get("subscription") as HashMap<String, String>,
                                LogoURL = document.get("logo_url").toString())
                        )

                    } else {
                        emitter.onError(Exception("User Not Exist"))

                    }
                } else {
                    emitter.onError(task.exception!!)
                }
            }
        }).subscribeOn(Schedulers.io())


    }


    override fun setUserDetails(userModel: UserModel): Completable {
        return Completable.create { emitter ->
            init()
            db.collection("Users")
                    .document(userModel.gstn)
                    .set(userModel)
                    .addOnSuccessListener { emitter.onComplete() }
                    .addOnFailureListener { emitter.onError(it) }
        }.subscribeOn(Schedulers.io())


    }

}



