package com.example.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ledger.LedgerScreen
import com.example.ui.VaultLedgerTheme

@Composable
fun App() {
    VaultLedgerTheme() {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LedgerScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}