package br.com.pedrotlf.trabalho_iot.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import br.com.pedrotlf.trabalho_iot.BaseFragment
import br.com.pedrotlf.trabalho_iot.R
import br.com.pedrotlf.trabalho_iot.databinding.FragmentHomeBinding
import br.com.pedrotlf.trabalho_iot.presentation.domain.model.PetData
import kotlinx.coroutines.launch


class HomeFragment: BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.petData.collect{ petData ->
                petData?.let {
                    updateTempInfo(it)
                    updateHeartbeatInfo(it)
                }
            }
        }
    }

    private fun updateTempInfo(petData: PetData) {
        binding.apply {
            val textColor = ContextCompat.getColor(requireContext(), petData.tempColor)

            homeTempTitle.setTextColor(textColor)

            homeTempValue.text = getString(R.string.home_temp_value, petData.temp)
            homeTempValue.setTextColor(textColor)

            homeTempText.text = getString(petData.tempText)
            homeTempText.setTextColor(textColor)
        }
    }

    private fun updateHeartbeatInfo(petData: PetData) {
        binding.apply {
            val textColor = ContextCompat.getColor(requireContext(), petData.heartbeatColor)

            homeHeartbeatTitle.setTextColor(textColor)

            homeHeartbeatValue.text = getString(R.string.home_heartbeat_value, petData.heartbeat)
            homeHeartbeatValue.setTextColor(textColor)

            homeHeartbeatText.text = getString(petData.heartbeatText)
            homeHeartbeatText.setTextColor(textColor)
        }
    }
}
