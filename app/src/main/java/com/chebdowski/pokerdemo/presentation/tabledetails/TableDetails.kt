package com.chebdowski.pokerdemo.presentation.tabledetails

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class TableDetails(
    val name: String,
    val gameType: String,
    val minBuyIn: Long,
    val maxBuyIn: Long
) : Parcelable