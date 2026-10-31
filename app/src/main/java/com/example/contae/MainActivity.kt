package com.example.contae
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.contae.data.AppDatabase
import com.example.contae.data.model.*
import com.example.contae.ui.navigation.AppNavigation
import com.example.contae.ui.theme.ContaeTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
class MainActivity:ComponentActivity(){
 override fun onCreate(savedInstanceState:Bundle?){super.onCreate(savedInstanceState); seed(); setContent{ContaeTheme{AppNavigation()}}}
 private fun seed(){val prefs=getSharedPreferences("contae_local",MODE_PRIVATE);lifecycleScope.launch(Dispatchers.IO){
  val db=AppDatabase.getDatabase(applicationContext)
  if(!prefs.getBoolean("dados_iniciais_criados",false)){
   val c=db.categoriaDao();val d=db.despesaDao()
   if(c.buscarTodos().first().isEmpty()) listOf(Categoria(nome="Alimentação",limite=850.0),Categoria(nome="Transporte",limite=500.0),Categoria(nome="Moradia",limite=1200.0),Categoria(nome="Lazer",limite=250.0),Categoria(nome="Saúde",limite=200.0)).forEach{c.inserir(it)}
   if(d.buscarTodos().first().isEmpty()){val ids=c.buscarTodos().first().associate{it.nome to it.id};val ex=listOf(Triple("Mercado",250.0,"Alimentação") to "04/10/2026",Triple("Uber",35.0,"Transporte") to "03/10/2026",Triple("Cinema",50.0,"Lazer") to "02/10/2026",Triple("Farmácia",80.0,"Saúde") to "01/10/2026");ex.forEach{(x,date)->val id=ids[x.third]?:return@forEach;d.inserir(Despesa(descricao=x.first,valor=x.second,categoriaId=id,data=date))}}
   withContext(Dispatchers.Main){prefs.edit().putBoolean("dados_iniciais_criados",true).apply()}
  }
 }}
}