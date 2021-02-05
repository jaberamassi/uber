package com.jaber.uber.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.jaber.uber.Common
import com.jaber.uber.model.TokenModel
import com.jaber.uber.service.MyFirebaseMessagingService

object UserUtils {
    fun updateUser(view: View, updateData:Map<String,Any>){
        FirebaseDatabase.getInstance().getReference(Common.DRIVER_INFO_REFERENCE)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .updateChildren(updateData)
            .addOnFailureListener { e->
                Snackbar.make(view,e.message.toString(),Snackbar.LENGTH_LONG).show()
            }.addOnSuccessListener {
                Snackbar.make(view,"update information success",Snackbar.LENGTH_LONG).show()

            }
    }

    fun updateToken(context: Context, token: String) {
        val tokenModel = TokenModel()
        tokenModel.token = token

        FirebaseDatabase.getInstance().getReference(Common.TOKEN_REFERENCE)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(token)
            .addOnFailureListener { e-> Toast.makeText(context,e.message.toString(),Toast.LENGTH_LONG).show() }
            .addOnSuccessListener {}
    }
}