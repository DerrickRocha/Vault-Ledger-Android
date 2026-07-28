package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.VaultLedgerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideVaultLedgerDatabase(@ApplicationContext applicationContext: Context): VaultLedgerDatabase = Room.databaseBuilder(
        applicationContext,
        VaultLedgerDatabase::class.java,
        "vault-ledger-db"
    ).build()
}