package com.example.contae.data.repository
import com.example.contae.data.dao.DespesaDao
import com.example.contae.data.model.Despesa
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
class DespesaRepository(private val dao:DespesaDao) {
 fun buscarTodos():Flow<List<Despesa>> = dao.buscarTodos()
 suspend fun buscarTodosAgora():List<Despesa> = dao.buscarTodos().first()
 suspend fun buscarPorId(id:Int):Despesa? = dao.buscarPorId(id)
 suspend fun buscarPorCategoria(id:Int):List<Despesa> = dao.buscarPorCategoria(id)
 suspend fun inserir(item:Despesa)=dao.inserir(item)
 suspend fun atualizar(item:Despesa)=dao.atualizar(item)
 suspend fun deletar(item:Despesa)=dao.deletar(item)
}