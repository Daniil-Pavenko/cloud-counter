package com.dp.cloudcounter.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.dp.cloudcounter.data.repository.CountersRepository
import com.dp.cloudcounter.data.repository.CountersRepositoryImpl
import com.dp.cloudcounter.data.repository.annotation.Local
import com.dp.cloudcounter.data.repository.annotation.Remote
import com.dp.cloudcounter.data.repository.datasource.CountersDataSource
import com.dp.cloudcounter.data.repository.datasource.LocalCountersDataSource
import com.dp.cloudcounter.data.repository.datasource.RemoteCountersDataSource
import com.dp.cloudcounter.data.storage.CCDatabase
import com.dp.cloudcounter.data.storage.DB_NAME
import com.dp.cloudcounter.data.storage.PreferenceStorage
import com.dp.cloudcounter.data.storage.PreferenceStorageImpl
import com.dp.cloudcounter.domain.NetworkHelper
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(NetworkModule::class)])
class RepositoryModule {

    @Provides
    @Singleton
    fun providePreferenceStorage(context: Context): PreferenceStorage = PreferenceStorageImpl(context)

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): CCDatabase = Room.databaseBuilder(context.applicationContext, CCDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference

    @Provides
    @Singleton
    @Local
    fun provideLocalCounterDataSource(ccDatabase: CCDatabase): CountersDataSource = LocalCountersDataSource(ccDatabase)

    @Provides
    @Singleton
    @Remote
    fun provideRemoteCounterDataSource(firebaseDatabase: DatabaseReference): CountersDataSource = RemoteCountersDataSource(firebaseDatabase)

    @Provides
    @Singleton
    fun provideCounterRepository(@Local localCountersDataSource: CountersDataSource,
                                 @Remote remoteCountersDataSource: CountersDataSource,
                                 networkHelper: NetworkHelper)
            : CountersRepository = CountersRepositoryImpl(localCountersDataSource, remoteCountersDataSource, networkHelper)
}