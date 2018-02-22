package com.dp.cloudcounter.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.dp.cloudcounter.data.storage.CCDatabase
import com.dp.cloudcounter.data.storage.DB_NAME
import com.dp.cloudcounter.data.storage.PreferenceStorage
import com.dp.cloudcounter.data.storage.PreferenceStorageImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Provides
    @Singleton
    fun providePreferenceStorage(context: Context): PreferenceStorage = PreferenceStorageImpl(context)

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): CCDatabase = Room.databaseBuilder(context, CCDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(context: Context): DatabaseReference = FirebaseDatabase.getInstance().reference
}