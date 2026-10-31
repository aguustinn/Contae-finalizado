package com.example.contae.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contae.data.model.Categoria
import com.example.contae.data.model.Despesa
import com.example.contae.data.repository.CategoriaRepository
import com.example.contae.data.repository.DespesaRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
class DespesaViewModel(private val repository:DespesaRepository, private val categoriaRepository:CategoriaRepository):ViewModel() {
 private val _uiState=MutableStateFlow(DespesaUiState())
 val uiState:StateFlow<DespesaUiState> = _uiState.asStateFlow()
 init { viewModelScope.launch { combine(repository.buscarTodos(),categoriaRepository.buscarTodos()){ desps,cats -> DespesaUiState(desps,cats,false) }.collect { _uiState.value=it } } }
 fun salvarDespesa(item:Despesa, aoConcluir:(Boolean,String)->Unit={_,_->}) { viewModelScope.launch {
  if(categoriaRepository.buscarPorId(item.categoriaId)==null){ aoConcluir(false,"Erro ao salvar: a categoria informada não existe."); return@launch }
  runCatching { if(item.id==0) repository.inserir(item) else repository.atualizar(item) }
   .onSuccess { aoConcluir(true,"Despesa salva com sucesso!") }
   .onFailure { aoConcluir(false,"Erro ao salvar: ${it.localizedMessage ?: "erro desconhecido"}") }
 } }
 fun deletarDespesa(item:Despesa) { viewModelScope.launch { runCatching { repository.deletar(item) }.onFailure { _uiState.value=_uiState.value.copy(mensagem=it.message ?: "Erro ao excluir despesa") } } }
}