package com.iamashad.unitrackrrr.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.iamashad.unitrackrrr.repository.FoundItemRepository
import com.iamashad.unitrackrrr.repository.LostItemRepository
import com.iamashad.unitrackrrr.repository.ReceiptRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    // Provide the Query for the "lostitems" collection
    @LostItemsQuery
    @Singleton
    @Provides
    fun provideLostItemsQuery(firestore: FirebaseFirestore): Query {
        return firestore.collection("lostitems")
    }

    @Singleton
    @Provides
    fun provideLostItemRepository(@LostItemsQuery itemQuery: Query): LostItemRepository {
        return LostItemRepository(itemQuery)
    }

    // Provide the Query for the "founditems" collection
    @FoundItemsQuery
    @Singleton
    @Provides
    fun provideFoundItemsQuery(firestore: FirebaseFirestore): Query {
        return firestore.collection("founditems")
    }

    @Singleton
    @Provides
    fun provideFoundItemRepository(@FoundItemsQuery itemQuery: Query): FoundItemRepository {
        return FoundItemRepository(itemQuery)
    }

    // Provide the Query for the "receipts" collection
    @ReceiptsQuery
    @Singleton
    @Provides
    fun provideReceiptsQuery(firestore: FirebaseFirestore): Query {
        return firestore.collection("receipts")
    }

    @Singleton
    @Provides
    fun provideReceiptRepository(
        firestore: FirebaseFirestore,
        auth: FirebaseAuth,
        @ReceiptsQuery receiptsQuery: Query
    ): ReceiptRepository {
        return ReceiptRepository(firestore, auth, receiptsQuery)
    }
}
