package br.com.pedrotlf.trabalho_iot.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.pedrotlf.trabalho_iot.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}