package com.example.santepriceindex.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Dashboard : Route
    @Serializable
    data object Calculator : Route
    @Serializable
    data object PriceBoard : Route
    @Serializable
    data object Trends : Route
}
