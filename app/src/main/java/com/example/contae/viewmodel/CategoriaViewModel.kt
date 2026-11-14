package com.example.contae.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contae.data.model.Categoria
import com.example.contae.data.model.Despesa
import com.example.contae.data.repository.CategoriaRepository
import com.example.contae.data.repository.DespesaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class CategoriaViewModel(private val repository:CategoriaRepository, private val despesaRepository:DespesaRepository):ViewModel() {
 private val _uiState=MutableStateFlow(CategoriaUiState())
 val uiState:StateFlow<CategoriaUiState> = _uiState.asStateFlow()
 init { viewModelScope.launch { combine(repository.buscarTodos(),despesaRepository.buscarTodos()){ cats,desps -> CategoriaUiState(cats,desps,false) }.collect { _uiState.value=it } } }
 fun salvarCategoria(item:Categoria) { viewModelScope.launch { runCatching { if(item.id==0) repository.inserir(item) else repository.atualizar(item) }.onFailure { _uiState.value=_uiState.value.copy(erro=it.message) } } }
 fun deletarCategoria(item:Categoria) { viewModelScope.launch { runCatching { despesaRepository.buscarPorCategoria(item.id).forEach { despesaRepository.deletar(it) }; repository.deletar(item) }.onFailure { _uiState.value=_uiState.value.copy(erro=it.message) } } }
}