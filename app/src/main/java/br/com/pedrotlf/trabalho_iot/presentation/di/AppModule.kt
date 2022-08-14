package br.com.pedrotlf.trabalho_iot.presentation.di

import br.com.pedrotlf.trabalho_iot.BuildConfig
import br.com.pedrotlf.trabalho_iot.presentation.data.repository.PetSocketConnection
import br.com.pedrotlf.trabalho_iot.presentation.data.repository.PetSocketMock
import br.com.pedrotlf.trabalho_iot.presentation.domain.repository.PetRepository
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