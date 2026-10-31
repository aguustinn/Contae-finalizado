package com.example.contae.viewmodel
import com.example.contae.data.model.Categoria
import com.example.contae.data.model.Despesa
data class DespesaUiState(
 val despesas:List<Despesa> = emptyList(),
 val categorias:List<Categoria> = emptyList(),
 val carregando:Boolean = true,
 val mensagem:String = ""
)
