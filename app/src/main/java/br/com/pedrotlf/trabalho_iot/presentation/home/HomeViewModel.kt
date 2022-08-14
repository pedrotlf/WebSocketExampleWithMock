package br.com.pedrotlf.trabalho_iot.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pedrotlf.trabalho_iot.presentation.domain.model.PetData
import br.com.pedrotlf.trabalho_iot.presentation.domain.use_case.FetchPetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPetDataUseCase: FetchPetDataUseCase
) : ViewModel() {

    private val _petData: MutableStateFlow<PetData?> = MutableStateFlow(null)
    val petData: Flow<PetData?> = _petData

    init {
        fetchPetDataUseCase.invoke {
            viewModelScope.launch {
                _petData.emit(it)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        fetchPetDataUseCase.closeConnection()
    }
}