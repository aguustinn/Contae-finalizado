package com.example.contae.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contae.data.repository.CategoriaRepository
import com.example.contae.data.repository.DespesaRepository
class CategoriaViewModelFactory(private val categoriaRepository:CategoriaRepository, private val despesaRepository:DespesaRepository):ViewModelProvider.Factory {
 @Suppress("UNCHECKED_CAST") override fun <T:ViewModel> create(modelClass:Class<T>):T { if(modelClass.isAssignableFrom(CategoriaViewModel::class.java)) return CategoriaViewModel(categoriaRepository,despesaRepository) as T; throw IllegalArgumentException("ViewModel desconhecido") }
}
class DespesaViewModelFactory(private val despesaRepository:DespesaRepository, private val categoriaRepository:CategoriaRepository):ViewModelProvider.Factory {
 @Suppress("UNCHECKED_CAST") override fun <T:ViewModel> create(modelClass:Class<T>):T { if(modelClass.isAssignableFrom(DespesaViewModel::class.java)) return DespesaViewModel(despesaRepository,categoriaRepository) as T; throw IllegalArgumentException("ViewModel desconhecido") }
}