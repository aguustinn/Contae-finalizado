package com.example.contae.data.dao
import androidx.room.*
import com.example.contae.data.model.Despesa
import kotlinx.coroutines.flow.Flow
@Dao
interface DespesaDao {
 @Insert suspend fun inserir(despesa: Despesa): Long
 @Query("SELECT * FROM despesas ORDER BY id DESC") fun buscarTodos(): Flow<List<Despesa>>
 @Query("SELECT * FROM despesas WHERE id = :id LIMIT 1") suspend fun buscarPorId(id: Int): Despesa?
 @Query("SELECT * FROM despesas WHERE categoriaId = :categoriaId ORDER BY id DESC") suspend fun buscarPorCategoria(categoriaId: Int): List<Despesa>
 @Update suspend fun atualizar(despesa: Despesa)
 @Delete suspend fun deletar(despesa: Despesa)
}