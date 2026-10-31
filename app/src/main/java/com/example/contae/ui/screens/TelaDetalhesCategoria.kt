package com.example.contae.ui.screens
import androidx.compose.runtime.collectAsState
import com.example.contae.viewmodel.CategoriaViewModel
import com.example.contae.viewmodel.DespesaViewModel


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contae.data.model.Categoria
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.CardVerde
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDetalhesCategoria(id: Int, navController: NavController, categoriaViewModel: CategoriaViewModel, despesaViewModel: DespesaViewModel) {
 val categoriaState by categoriaViewModel.uiState.collectAsState()
 val despesaState by despesaViewModel.uiState.collectAsState()

 val categoria = categoriaState.categorias.firstOrNull { it.id == id }
 val carregando = categoriaState.carregando

    //se a cat nao existir
    if (categoria == null) {

        Scaffold(
            containerColor = CinzaFundo,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Categoria",
                            fontWeight = FontWeight.Bold
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Voltar"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = VerdeEscuro,
                        titleContentColor = Branco,
                        navigationIconContentColor = Branco
                    )
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = if (carregando) "Carregando categoria..." else "Categoria não encontrada",
                    color = VerdeEscuro,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = if (carregando) "Buscando os dados no banco local." else "Esta categoria não existe mais.",
                    color = Cinza,
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                androidx.compose.material3.Button(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text("Voltar")
                }
            }
        }

        return
    }

    val categoriaAtual = categoria ?: return

    // Todas as despesas da categoria
    val despesasCategoria = despesaState.despesas.filter {
        it.categoriaId == categoriaAtual.id
    }

    // Soma despesas
    val totalGasto = despesasCategoria.sumOf {
        it.valor
    }

    // Quanto ainda pode gastar
    val restante = categoriaAtual.limite - totalGasto

    // Porcentagem utilizada
    val percentual = if (categoriaAtual.limite > 0) {
        (totalGasto / categoriaAtual.limite)
            .coerceIn(0.0, 1.0)
            .toFloat()
    } else {
        0f
    }

    val percentualTexto = (percentual * 100).toInt()

    // Verifica se ultrapassou o limite
    val limiteUltrapassado = totalGasto > categoriaAtual.limite

    val corStatus = if (limiteUltrapassado) {
        Color(0xFFE53935)
    } else {
        VerdePrincipal
    }

    val fundoStatus = if (limiteUltrapassado) {
        Color(0xFFFFEBEE)
    } else {
        CardVerde
    }

    Scaffold(
        containerColor = CinzaFundo,

        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "Detalhes da categoria",
                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VerdeEscuro,
                    titleContentColor = Branco,
                    navigationIconContentColor = Branco
                )
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(CinzaFundo)
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                )
        ) {


            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(22.dp),

                colors = CardDefaults.cardColors(
                    containerColor = VerdeEscuro
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(22.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(58.dp)
                                .clip(CircleShape)
                                .background(VerdePrincipal),

                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Filled.Category,
                                contentDescription = null,
                                tint = Branco,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(15.dp)
                        )

                        Column {

                            Text(
                                text = categoriaAtual.nome,
                                color = Branco,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Resumo da categoria",
                                color = VerdeClaro,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )

                    Text(
                        text = "Total gasto",
                        color = VerdeClaro,
                        fontSize = 13.sp
                    )

                    Text(
                        text = "R$ %.2f".format(totalGasto),
                        color = Branco,
                        fontSize = 31.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Branco
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceBetween,

                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Uso do orçamento",
                            color = VerdeEscuro,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "$percentualTexto%",
                            color = corStatus,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(CardVerde)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(percentual)
                                .height(10.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(corStatus)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {

                            Text(
                                text = "Gasto",
                                color = Cinza,
                                fontSize = 12.sp
                            )

                            Text(
                                text = "R$ %.2f".format(totalGasto),
                                color = VerdeEscuro,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            Text(
                                text = "Limite",
                                color = Cinza,
                                fontSize = 12.sp
                            )

                            Text(
                                text = "R$ %.2f".format(categoriaAtual.limite),
                                color = VerdeEscuro,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(18.dp),

                colors = CardDefaults.cardColors(
                    containerColor = fundoStatus
                )
            ) {

                Row(
                    modifier = Modifier.padding(16.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = if (limiteUltrapassado) {
                            Icons.Filled.Warning
                        } else {
                            Icons.Filled.CheckCircle
                        },

                        contentDescription = null,

                        tint = corStatus,

                        modifier = Modifier.size(28.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Column {

                        Text(
                            text = if (limiteUltrapassado) {
                                "Limite ultrapassado"
                            } else {
                                "Dentro do orçamento"
                            },

                            color = corStatus,

                            fontSize = 15.sp,

                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(3.dp)
                        )

                        Text(
                            text = if (limiteUltrapassado) {
                                "Você ultrapassou o limite desta categoriaAtual."
                            } else {
                                "Ainda há R$ %.2f disponíveis.".format(restante)
                            },

                            color = Cinza,

                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )


            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                // Quantidade de despesas

                Card(
                    modifier = Modifier.weight(1f),

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Branco
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Despesas",
                            color = Cinza,
                            fontSize = 12.sp
                        )

                        Text(
                            text = despesasCategoria.size.toString(),
                            color = VerdeEscuro,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }

                // Valor disponível

                Card(
                    modifier = Modifier.weight(1f),

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Branco
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "Disponível",
                            color = Cinza,
                            fontSize = 12.sp
                        )

                        Text(
                            text = if (restante >= 0) {
                                "R$ %.2f".format(restante)
                            } else {
                                "- R$ %.2f".format(kotlin.math.abs(restante))
                            },

                            color = if (restante < 0) {
                                Color(0xFFE53935)
                            } else {
                                VerdeEscuro
                            },

                            fontSize = 18.sp,

                            fontWeight = FontWeight.Bold,

                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}