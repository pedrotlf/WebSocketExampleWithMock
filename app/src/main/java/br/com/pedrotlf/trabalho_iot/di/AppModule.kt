package br.com.pedrotlf.trabalho_iot.di

import android.content.Context
import br.com.pedrotlf.trabalho_iot.BuildConfig
import br.com.pedrotlf.trabalho_iot.data.repository.PetSocketConnection
import br.com.pedrotlf.trabalho_iot.data.repository.PetSocketMock
import br.com.pedrotlf.trabalho_iot.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePetSocketConnection(@ApplicationContext context: Context): PetRepository =
        if(BuildConfig.DEBUG)
            PetSocketMock(context)
        else
            PetSocketConnection

}