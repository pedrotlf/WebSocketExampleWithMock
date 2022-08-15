package br.com.pedrotlf.trabalho_iot.di

import br.com.pedrotlf.trabalho_iot.BuildConfig
import br.com.pedrotlf.trabalho_iot.data.repository.PetSocketConnection
import br.com.pedrotlf.trabalho_iot.data.repository.PetSocketMock
import br.com.pedrotlf.trabalho_iot.domain.repository.PetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePetSocketConnection(): PetRepository =
        if(BuildConfig.DEBUG)
            PetSocketMock
        else
            PetSocketConnection

}