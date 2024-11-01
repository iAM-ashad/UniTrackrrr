package com.iamashad.unitrackrrr.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LostItemsQuery

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FoundItemsQuery