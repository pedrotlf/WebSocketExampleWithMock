package br.com.pedrotlf.trabalho_iot.presentation.data.repository

import br.com.pedrotlf.trabalho_iot.presentation.data.dto.PetDataDTO
import br.com.pedrotlf.trabalho_iot.presentation.domain.repository.PetRepository
import java.util.*
import kotlin.concurrent.timer

object PetSocketMock : PetRepository(){

    private var loop: Timer? = null

    override fun connect(): Boolean {
        return true
    }

    override fun disconnect() {
        loop = null
    }

    override fun startFetchingPetData() {
        loop = timer("AlertsRequest", false, 0, 4000){
            onDataReceived?.invoke(
                PetDataDTO(
                    (90..220).random(),
                    ((358..405).random() / 10.0)
                )
            )
        }
    }
}