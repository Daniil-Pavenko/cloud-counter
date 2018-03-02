package com.dp.cloudcounter.di.module

import android.content.Context
import com.dp.cloudcounter.domain.NetworkHelper
import com.dp.cloudcounter.domain.impl.NetworkHelperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(AppModule::class)])
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkHelper(context: Context): NetworkHelper = NetworkHelperImpl(context)
}