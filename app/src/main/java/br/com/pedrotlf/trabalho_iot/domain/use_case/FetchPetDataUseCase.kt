package br.com.pedrotlf.trabalho_iot.domain.use_case

import br.com.pedrotlf.trabalho_iot.domain.model.PetData
import br.com.pedrotlf.trabalho_iot.domain.model.toPetData
import br.com.pedrotlf.trabalho_iot.domain.repository.PetRepository
import javax.inject.Inject

class FetchPetDataUseCase @Inject constructor(
    private val petRepository: PetRepository
) {

    operator fun invoke(callback: (PetData?) -> Unit) {
        petRepository.startRepo{
            callback.invoke(it?.toPetData())
        }
    }

    fun closeConnection() {
        petRepository.stopRepo()
    }
}