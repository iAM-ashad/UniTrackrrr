package com.iamashad.unitrackrrr.model

import com.google.firebase.firestore.PropertyName

data class FeeReceipt(
    var id: String? = null,
    @get:PropertyName("receipt_url")
    @set:PropertyName("receipt_url")
    var receiptUrl: String? = null,
    var semester: Int? = null,
    var userId: String? = null
)
