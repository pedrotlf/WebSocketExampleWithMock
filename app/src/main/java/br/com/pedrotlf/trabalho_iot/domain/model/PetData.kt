package br.com.pedrotlf.trabalho_iot.domain.model

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import br.com.pedrotlf.trabalho_iot.R
import br.com.pedrotlf.trabalho_iot.data.dto.PetDataDTO
import kotlin.math.pow
import kotlin.math.sqrt

data class PetData (
    val acceleration: Double,
    @ColorRes val movementColor: Int,
    @StringRes val movementText: Int,
    val temp: Double,
    @ColorRes val tempColor: Int,
    @StringRes val tempText: Int,
)

fun PetDataDTO.toPetData(): PetData {
    val acFinal = sqrt(acx.pow(2) + acy.pow(2) + acz.pow(2)) * 9.8

    val hbText: Int
    val hbColor: Int
    when{
        acFinal > 6 -> {
            hbText = R.string.movement_high
            hbColor = R.color.hot
        }
        acFinal < 2 -> {
            hbText = R.string.movement_low
            hbColor = R.color.cold
        }
        else -> {
            hbText = R.string.movement_ok
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

    return PetData(acFinal, hbColor, hbText, temp, tempColor, tempText)
}