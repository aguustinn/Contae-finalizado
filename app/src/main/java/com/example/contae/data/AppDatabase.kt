package com.example.contae.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.contae.data.dao.CategoriaDao
import com.example.contae.data.dao.DespesaDao
import com.example.contae.data.model.Categoria
import com.example.contae.data.model.Despesa
@Database(entities=[Categoria::class, Despesa::class], version=1, exportSchema=false)
abstract class AppDatabase: RoomDatabase() {
 abstract fun categoriaDao(): CategoriaDao
 abstract fun despesaDao(): DespesaDao
 companion object {
  @Volatile private var INSTANCE: AppDatabase? = null
  fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
   INSTANCE ?: Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "contae_database").build().also { INSTANCE=it }
  }
 }
}