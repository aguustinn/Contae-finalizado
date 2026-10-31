package com.example.contae.data.repository
import com.example.contae.data.dao.CategoriaDao
import com.example.contae.data.model.Categoria
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
class CategoriaRepository(private val dao:CategoriaDao) {
 fun buscarTodos():Flow<List<Categoria>> = dao.buscarTodos()
 suspend fun buscarTodosAgora():List<Categoria> = dao.buscarTodos().first()
 suspend fun buscarPorId(id:Int):Categoria? = dao.buscarPorId(id)
 suspend fun inserir(item:Categoria)=dao.inserir(item)
 suspend fun atualizar(item:Categoria)=dao.atualizar(item)
 suspend fun deletar(item:Categoria)=dao.deletar(item)
}