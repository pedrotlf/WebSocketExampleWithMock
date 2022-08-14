package br.com.pedrotlf.trabalho_iot.presentation.domain.repository

import br.com.pedrotlf.trabalho_iot.presentation.data.dto.PetDataDTO

abstract class PetRepository {

    protected var onDataReceived: ((PetDataDTO?) -> Unit)? = null

    fun startRepo(callback: (PetDataDTO?) -> Unit) {
        onDataReceived = callback
        if (connect()) startFetchingPetData()
    }

    fun stopRepo() {
        disconnect()
    }

    protected abstract fun connect(): Boolean
    protected abstract fun disconnect()
    protected abstract fun startFetchingPetData()
}