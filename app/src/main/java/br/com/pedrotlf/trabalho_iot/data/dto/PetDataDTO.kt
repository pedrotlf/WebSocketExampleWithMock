package br.com.pedrotlf.trabalho_iot.data.dto

data class PetDataDTO(
    val acx: Double,
    val acy: Double,
    val acz: Double,
    val temp: Double
)

fun String.toPetDataDTO() : PetDataDTO {
    val parts = split("|")

    val acx = parts[0].takeNumber()
    val acy = parts[1].takeNumber()
    val acz = parts[2].takeNumber()

    val temp = parts[3].takeNumber()

    return PetDataDTO(acx, acy, acz, temp)
}

private fun String.takeNumber(): Double = replace("[^0-9^.-]".toRegex(), "").toDouble()