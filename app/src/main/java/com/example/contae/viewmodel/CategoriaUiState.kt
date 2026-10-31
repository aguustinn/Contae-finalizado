package com.example.contae.viewmodel
import com.example.contae.data.model.Categoria
import com.example.contae.data.model.Despesa
data class CategoriaUiState(
 val categorias:List<Categoria> = emptyList(),
 val despesas:List<Despesa> = emptyList(),
 val carregando:Boolean = true,
 val erro:String? = null
)
