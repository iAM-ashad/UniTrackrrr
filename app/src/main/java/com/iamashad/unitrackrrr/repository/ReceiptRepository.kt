package com.iamashad.unitrackrrr.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.iamashad.unitrackrrr.model.FeeReceipt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReceiptRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private val receiptsQuery: Query
) {

    fun getUserReceipts(): Flow<List<FeeReceipt>> = flow {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val userId = currentUser.uid

            try {
                val snapshot = receiptsQuery
                    .whereEqualTo("userId", userId)
                    .get()
                    .await()

                val receipts = snapshot.documents.mapNotNull { document ->
                    document.toObject(FeeReceipt::class.java)
                }

                emit(receipts)
                Log.d("ReceiptRepository", "Fetched ${receipts.size} receipts for user: $userId")
            } catch (e: Exception) {
                Log.e("ReceiptRepository", "Error fetching receipts", e)
                emit(emptyList())
            }
        } else {
            Log.w("ReceiptRepository", "No user logged in")
            emit(emptyList())
        }
    }
    private val receiptsCollection = firestore.collection("feeReceipts") // Adjust collection name if necessary

    suspend fun getReceiptsBySemester(semesterId: Int): List<FeeReceipt> {
        return try {
            val snapshot = receiptsCollection
                .whereEqualTo("semester", semesterId)
                .get()
                .await()
            snapshot.documents.mapNotNull { document ->
                document.toObject(FeeReceipt::class.java)
            }
        } catch (e: Exception) {
            emptyList() // Handle exceptions as needed
        }
    }
}