package br.com.pedrotlf.trabalho_iot.presentation.data.repository

import android.util.Log
import br.com.pedrotlf.trabalho_iot.presentation.data.dto.PetDataDTO
import br.com.pedrotlf.trabalho_iot.presentation.domain.repository.PetRepository
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object PetSocketConnection : PetRepository() {

    private var mSocket: Socket? = null

    @Synchronized
    private fun setSocket() {
        try {
            mSocket = IO.socket("10.0.0.1")
        } catch (e: URISyntaxException){
            Log.e("PetSocketHandler", e.message.toString())
        } catch (e: RuntimeException){
            Log.e("PetSocketHandler", e.message.toString())
        }
    }

    @Synchronized
    override fun connect(): Boolean {
        setSocket()
        mSocket?.connect() ?: return false
        return true
    }

    @Synchronized
    override fun disconnect() {
        mSocket?.close()
    }

    override fun startFetchingPetData() {
        mSocket?.on("???"){ args ->
            if(args[0] != null){
                try {
                    val data = args[0] as PetDataDTO
                    onDataReceived?.invoke(data)
                } catch (e: Exception) {
                    onDataReceived?.invoke(null)
                }
            }
        }
    }
}