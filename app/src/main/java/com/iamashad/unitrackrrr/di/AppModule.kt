package com.iamashad.unitrackrrr.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.iamashad.unitrackrrr.repository.FoundItemRepository
import com.iamashad.unitrackrrr.repository.LostItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @LostItemsQuery
    @Provides
    fun provideLostItemsQuery(firestore: FirebaseFirestore): Query {
        return firestore.collection("lostitems")
    }

    @Provides
    fun provideLostItemRepository(@LostItemsQuery itemQuery: Query): LostItemRepository {
        return LostItemRepository(itemQuery)
    }

    @FoundItemsQuery
    @Provides
    fun provideFoundItemsQuery(firestore: FirebaseFirestore): Query {
        return firestore.collection("founditems")
    }

    @Provides
    fun provideFoundItemRepository(@FoundItemsQuery itemQuery: Query): FoundItemRepository {
        return FoundItemRepository(itemQuery)
    }
}