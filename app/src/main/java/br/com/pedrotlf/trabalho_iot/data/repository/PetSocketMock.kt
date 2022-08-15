package br.com.pedrotlf.trabalho_iot.data.repository

import android.content.Context
import br.com.pedrotlf.trabalho_iot.data.dto.PetDataDTO
import br.com.pedrotlf.trabalho_iot.domain.repository.PetRepository
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

class PetSocketMock @Inject constructor(
    private val context: Context
) : PetRepository(){

    private var loop: Job? = null

    override fun connect(): Boolean {
        return true
    }

    override fun disconnect() {
        loop?.cancel()
        loop = null
    }

    override fun startFetchingPetData() {
//        loop = timer("AlertsRequest", false, 0, 4000){
//            onDataReceived?.invoke(
//                PetDataDTO(
//                    (0..5).random() / 100.0,
//                    (0..96).random() / 100.0,
//                    (0..5).random() / 100.0,
//                    (358..405).random() / 10.0
//                )
//            )
//        }

        loop = CoroutineScope(Dispatchers.IO).launch {
            val lines = context.loadJSONFromAssets("pet_data_mocked.txt").lines()

            var x = 0.0
            var y = 0.0
            var z: Double
            var temp = 37.2
            lines.forEachIndexed { i, line ->
                if((i+1) % 30 == 0){
                    temp = (371..374).random() / 10.0
                }
                when((i+1) % 3){
                    1 -> x = line.toDouble()
                    2 -> y = line.toDouble()
                    else -> {
                        z = line.toDouble()
                        onDataReceived?.invoke(PetDataDTO(x, y, z, temp))
                        delay(250)
                    }
                }
            }
        }
    }

    private fun Context.loadJSONFromAssets(path: String): String = assets.open(path).use {
        it.readBytes().toString(Charsets.UTF_8)
    }
}