package br.com.pedrotlf.trabalho_iot.presentation.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import br.com.pedrotlf.trabalho_iot.R
import br.com.pedrotlf.trabalho_iot.presentation.data.dto.PetDataDTO

data class PetData (
    val heartbeat: Int,
    @ColorRes val heartbeatColor: Int,
    @StringRes val heartbeatText: Int,
    val temp: Double,
    @ColorRes val tempColor: Int,
    @StringRes val tempText: Int,
)

fun PetDataDTO.toPetData(): PetData {
    val hbText: Int
    val hbColor: Int
    when{
        heartbeat > 160 -> {
            hbText = R.string.heartbeat_high
            hbColor = R.color.hot
        }
        heartbeat < 120 -> {
            hbText = R.string.heartbeat_low
            hbColor = R.color.cold
        }
        else -> {
            hbText = R.string.heartbeat_ok
            hbColor = R.color.normal
        }
    }

    val tempText: Int
    val tempColor: Int
    when{
        temp > 39.1 -> {
            tempText = R.string.temp_high
            tempColor = R.color.hot
        }
        temp < 37.2 -> {
            tempText = R.string.temp_low
            tempColor = R.color.cold
        }
        else -> {
            tempText = R.string.temp_ok
            tempColor = R.color.normal
        }
    }

    return PetData(heartbeat, hbColor, hbText, temp, tempColor, tempText)
}