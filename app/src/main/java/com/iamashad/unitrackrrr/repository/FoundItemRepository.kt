package com.iamashad.unitrackrrr.repository

import com.google.firebase.firestore.Query
import com.iamashad.unitrackrrr.di.FoundItemsQuery
import com.iamashad.unitrackrrr.model.FoundItem
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FoundItemRepository @Inject constructor(
    @FoundItemsQuery private val itemQuery: Query
) {
    suspend fun getAllFoundItems(): List<FoundItem> {
        return try {
            val snapshot = itemQuery.get().await()
            snapshot.documents.map { document ->
                document.toObject(FoundItem::class.java)!!
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}