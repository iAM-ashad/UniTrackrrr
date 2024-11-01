package com.iamashad.unitrackrrr.model

import com.google.firebase.firestore.PropertyName

data class LostItem(
    var name: String? = null,
    var uuid: String? = null,
    var date: String? = null,
    @get:PropertyName("last_location")
    @set:PropertyName("last_location")
    var lastLocation: String? = null,
    var image: String? = null
)
