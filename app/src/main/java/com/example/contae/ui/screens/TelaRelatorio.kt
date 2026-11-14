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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contae.ui.components.BarraNavegacao
import com.example.contae.ui.navigation.Rotas
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.CardVerde
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@Composable
fun TelaRelatorio(navController: NavController, categoriaViewModel: CategoriaViewModel, despesaViewModel: DespesaViewModel) {
 val categoriaState by categoriaViewModel.uiState.collectAsState()
 val despesaState by despesaViewModel.uiState.collectAsState()
val totalGasto =
        despesaState.despesas.sumOf {
            it.valor
        }

    val quantidadeDespesas =
        despesaState.despesas.size

    val media =
        if (quantidadeDespesas > 0) {
            totalGasto / quantidadeDespesas
        } else {
            0.0
        }

    val categoriaMaiorGasto =
        categoriaState.categorias.maxByOrNull { categoria ->

            despesaState.despesas
                .filter {
                    it.categoriaId == categoria.id
                }
                .sumOf {
                    it.valor
                }
        }

    Scaffold(
        containerColor = CinzaFundo,

        bottomBar = {
            BarraNavegacao(
                navController = navController,
                rotaAtual = Rotas.RELATORIO
            )
        }

    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(CinzaFundo)
        ) {


            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(VerdeEscuro)
                        .padding(
                            horizontal = 22.dp,
                            vertical = 25.dp
                        )
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(VerdePrincipal),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Filled.Assessment,

                                contentDescription = null,

                                tint = Branco,

                                modifier =
                                    Modifier.size(27.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.size(14.dp)
                        )

                        Column {

                            Text(
                                text = "Relatório",
                                color = Branco,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text =
                                    "Veja um resumo dos seus gastos",

                                color = VerdeClaro,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }



            item {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 20.dp
                        ),

                    shape = RoundedCornerShape(22.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = VerdeEscuro
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(22.dp)
                    ) {

                        Text(
                            text = "Total gasto",
                            color = VerdeClaro,
                            fontSize = 13.sp
                        )

                        Text(
                            text =
                                "R$ %.2f"
                                    .format(totalGasto),

                            color = Branco,
                            fontSize = 31.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "$quantidadeDespesas despesas registradas",

                            color = VerdeClaro,
                            fontSize = 13.sp
                        )
                    }
                }
            }


            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),

                    horizontalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    Card(
                        modifier = Modifier.weight(1f),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            CardDefaults.cardColors(
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
                                text =
                                    quantidadeDespesas.toString(),

                                color = VerdeEscuro,

                                fontSize = 23.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }

                    Card(
                        modifier = Modifier.weight(1f),

                        shape =
                            RoundedCornerShape(18.dp),

                        colors =
                            CardDefaults.cardColors(
                                containerColor = Branco
                            )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Média",
                                color = Cinza,
                                fontSize = 12.sp
                            )

                            Text(
                                text =
                                    "R$ %.2f"
                                        .format(media),

                                color = VerdeEscuro,

                                fontSize = 18.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }
            }


            item {

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),

                    shape = RoundedCornerShape(20.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = CardVerde
                    )
                ) {

                    Row(
                        modifier = Modifier.padding(18.dp),

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Filled.TrendingUp,

                            contentDescription = null,

                            tint = VerdePrincipal,

                            modifier =
                                Modifier.size(30.dp)
                        )

                        Spacer(
                            modifier = Modifier.size(14.dp)
                        )

                        Column {

                            Text(
                                text = "Maior categoria de gasto",
                                color = VerdeEscuro,
                                fontSize = 13.sp
                            )

                            Text(
                                text =
                                    categoriaMaiorGasto
                                        ?.nome
                                        ?: "Nenhuma",

                                color = VerdeEscuro,

                                fontSize = 18.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Resumo por categoria",

                    color = VerdeEscuro,

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.padding(
                        horizontal = 20.dp
                    )
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }


            items(
                items = categoriaState.categorias,
                key = {
                    it.id
                }
            ) { categoria ->

                val gasto =
                    despesaState.despesas
                        .filter {
                            it.categoriaId == categoria.id
                        }
                        .sumOf {
                            it.valor
                        }

                val porcentagemTotal =
                    if (totalGasto > 0) {
                        (gasto / totalGasto)
                            .coerceIn(0.0, 1.0)
                            .toFloat()
                    } else {
                        0f
                    }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 6.dp
                        ),

                    shape = RoundedCornerShape(18.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Branco
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(17.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = categoria.nome,
                                color = VerdeEscuro,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text =
                                    "R$ %.2f"
                                        .format(gasto),

                                color = VerdePrincipal,

                                fontSize = 14.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(
                                    RoundedCornerShape(50.dp)
                                )
                                .background(CardVerde)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(
                                        porcentagemTotal
                                    )
                                    .height(8.dp)
                                    .clip(
                                        RoundedCornerShape(50.dp)
                                    )
                                    .background(
                                        VerdePrincipal
                                    )
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                "${(porcentagemTotal * 100).toInt()}% do total",

                            color = Cinza,
                            fontSize = 11.sp
                        )
                    }
                }
            }

            item {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    }
}