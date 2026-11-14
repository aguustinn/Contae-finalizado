package com.example.contae.data.dao
import androidx.room.*
import com.example.contae.data.model.Categoria
import kotlinx.coroutines.flow.Flow
@Dao
interface CategoriaDao {
 @Insert suspend fun inserir(categoria: Categoria): Long
 @Query("SELECT * FROM categorias ORDER BY nome COLLATE NOCASE") fun buscarTodos(): Flow<List<Categoria>>
 @Query("SELECT * FROM categorias WHERE id = :id LIMIT 1") suspend fun buscarPorId(id: Int): Categoria?
 @Update suspend fun atualizar(categoria: Categoria)
 @Delete suspend fun deletar(categoria: Categoria)
}