package com.example.contae.ui.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.contae.data.AppDatabase
import com.example.contae.data.repository.*
import com.example.contae.ui.screens.*
import com.example.contae.viewmodel.*
object Rotas {
 const val INICIO="inicio"; const val LOGIN="login"; const val HOME="home"; const val CATEGORIAS="categorias"
 const val DETALHES_CATEGORIA="detalhes_categoria/{id}"; const val DESPESAS="despesas"; const val DETALHES_DESPESA="detalhes_despesa/{id}"
 const val ORCAMENTO="orcamento"; const val RELATORIO="relatorio"
 fun detalhesCategoria(id:Int)="detalhes_categoria/$id"
 fun detalhesDespesa(id:Int)="detalhes_despesa/$id"
}
@Composable fun AppNavigation() {
 val navController= rememberNavController(); val context=LocalContext.current
 val db= remember { AppDatabase.getDatabase(context) }
 val cr= remember { CategoriaRepository(db.categoriaDao()) }; val dr= remember { DespesaRepository(db.despesaDao()) }
 val cv:CategoriaViewModel=viewModel(factory=CategoriaViewModelFactory(cr,dr))
 val dv:DespesaViewModel=viewModel(factory=DespesaViewModelFactory(dr,cr))
 NavHost(navController, startDestination=Rotas.INICIO) {
  composable(Rotas.INICIO){TelaInicial{navController.navigate(Rotas.LOGIN)}}
  composable(Rotas.LOGIN){TelaLogin{navController.navigate(Rotas.HOME){popUpTo(Rotas.LOGIN){inclusive=true}}}}
  composable(Rotas.HOME){TelaHome(navController,cv,dv)}
  composable(Rotas.CATEGORIAS){TelaCategorias(navController,cv,dv)}
  composable(Rotas.DETALHES_CATEGORIA){e->e.arguments?.getString("id")?.toIntOrNull()?.let{TelaDetalhesCategoria(it,navController,cv,dv)}}
  composable(Rotas.DESPESAS){TelaDespesas(navController,dv)}
  composable(Rotas.DETALHES_DESPESA){e->e.arguments?.getString("id")?.toIntOrNull()?.let{TelaDetalhesDespesa(it,navController,dv)}}
  composable(Rotas.ORCAMENTO){TelaOrcamento(navController,cv,dv)}
  composable(Rotas.RELATORIO){TelaRelatorio(navController,cv,dv)}
 }
}