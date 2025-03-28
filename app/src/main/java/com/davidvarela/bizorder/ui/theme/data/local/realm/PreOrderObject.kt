package com.davidvarela.bizorder.ui.theme.data.local.realm

import androidx.compose.ui.util.fastCbrt
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class PreOrderObject: RealmObject {
    @PrimaryKey
    var id: Long = 0L
    var customerName: String = ""
    var item: String = ""
    var isSent: Boolean = false
}