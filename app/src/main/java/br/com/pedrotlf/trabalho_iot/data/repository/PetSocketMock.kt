package br.com.pedrotlf.trabalho_iot.data.repository

import br.com.pedrotlf.trabalho_iot.data.dto.PetDataDTO
import br.com.pedrotlf.trabalho_iot.domain.repository.PetRepository
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
                    (0..5).random() / 100.0,
                    (0..96).random() / 100.0,
                    (0..5).random() / 100.0,
                    (358..405).random() / 10.0
                )
            )
        }
    }
}