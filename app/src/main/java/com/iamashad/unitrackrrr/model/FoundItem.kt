package com.iamashad.unitrackrrr.model

import com.google.firebase.firestore.PropertyName

data class FoundItem(
    var name: String? = null,
    var uuid: String? = null,
    var date: String? = null,
    @get:PropertyName("found_where")
    @set:PropertyName("found_where")
    var foundWhere: String? = null,
    var image: String? = null
)