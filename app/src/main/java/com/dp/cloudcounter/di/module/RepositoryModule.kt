package com.dp.cloudcounter.di.module

import com.dp.cloudcounter.data.repository.CountersRepository
import com.dp.cloudcounter.data.repository.CountersRepositoryImpl
import com.dp.cloudcounter.data.repository.annotation.Local
import com.dp.cloudcounter.data.repository.annotation.Remote
import com.dp.cloudcounter.data.repository.datasource.CountersDataSource
import com.dp.cloudcounter.data.repository.datasource.LocalCountersDataSource
import com.dp.cloudcounter.data.repository.datasource.RemoteCountersDataSource
import com.dp.cloudcounter.data.storage.CCDatabase
import com.dp.cloudcounter.domain.NetworkHelper
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(NetworkModule::class)])
class RepositoryModule {

    @Provides
    @Local
    fun provideLocalCounterDataSource(ccDatabase: CCDatabase): CountersDataSource = LocalCountersDataSource(ccDatabase)

    @Provides
    @Remote
    fun provideRemoteCounterDataSource(firebaseDatabase: DatabaseReference): CountersDataSource = RemoteCountersDataSource(firebaseDatabase)

    @Provides
    @Singleton
    fun provideCounterRepository(@Local localCountersDataSource: LocalCountersDataSource,
                                 @Remote remoteCountersDataSource: RemoteCountersDataSource,
                                 networkHelper: NetworkHelper)
            : CountersRepository = CountersRepositoryImpl(localCountersDataSource, remoteCountersDataSource, networkHelper)
}