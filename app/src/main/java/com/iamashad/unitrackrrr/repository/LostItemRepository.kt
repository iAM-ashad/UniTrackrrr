package com.iamashad.unitrackrrr.repository

import com.google.firebase.firestore.Query
import com.iamashad.unitrackrrr.di.LostItemsQuery
import com.iamashad.unitrackrrr.model.LostItem
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LostItemRepository @Inject constructor(
    @LostItemsQuery private val itemQuery: Query
) {
    suspend fun getAllLostItems(): List<LostItem> {
        return try {
            val snapshot = itemQuery.get().await()
            snapshot.documents.map { document ->
                document.toObject(LostItem::class.java)!!
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
