package com.example.santepriceindex.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil

data class PricingState(
    val purchasePrice: String = "",
    val transportCost: String = "",
    val wastagePercent: String = "5",
    val profitMarginPercent: String = "20",
    val commodityName: String = "Onion",
    val recommendedPrice: Double = 0.0
)

class PricingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PricingState())
    val uiState: StateFlow<PricingState> = _uiState.asStateFlow()

    fun onPurchasePriceChange(value: String) {
        _uiState.update { it.copy(purchasePrice = value) }
        calculateRRP()
    }

    fun onTransportCostChange(value: String) {
        _uiState.update { it.copy(transportCost = value) }
        calculateRRP()
    }

    fun onWastageChange(value: String) {
        _uiState.update { it.copy(wastagePercent = value) }
        calculateRRP()
    }

    fun onProfitMarginChange(value: String) {
        _uiState.update { it.copy(profitMarginPercent = value) }
        calculateRRP()
    }

    fun onCommodityNameChange(value: String) {
        _uiState.update { it.copy(commodityName = value) }
    }

    private fun calculateRRP() {
        val purchase = _uiState.value.purchasePrice.toDoubleOrNull() ?: 0.0
        val transport = _uiState.value.transportCost.toDoubleOrNull() ?: 0.0
        val wastage = (_uiState.value.wastagePercent.toDoubleOrNull() ?: 0.0) / 100.0
        val profit = (_uiState.value.profitMarginPercent.toDoubleOrNull() ?: 0.0) / 100.0

        if (purchase > 0) {
            // Formula: Total Cost = (Purchase Price + Transport) / (1 - Wastage%)
            // RRP = Total Cost * (1 + Profit Margin%)
            val totalCost = (purchase + transport) / (1.0 - wastage)
            val rrp = totalCost * (1.0 + profit)
            
            // Round up to nearest whole number for easier cash transactions in markets
            _uiState.update { it.copy(recommendedPrice = ceil(rrp)) }
        } else {
            _uiState.update { it.copy(recommendedPrice = 0.0) }
        }
    }
}
