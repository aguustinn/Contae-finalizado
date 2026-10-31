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
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Warning
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
fun TelaOrcamento(navController: NavController, categoriaViewModel: CategoriaViewModel, despesaViewModel: DespesaViewModel) {
 val categoriaState by categoriaViewModel.uiState.collectAsState()
 val despesaState by despesaViewModel.uiState.collectAsState()
val limiteTotal = categoriaState.categorias.sumOf {
        it.limite
    }

    val gastoTotal = despesaState.despesas.sumOf {
        it.valor
    }

    val restanteTotal =
        limiteTotal - gastoTotal

    val percentualTotal =
        if (limiteTotal > 0) {
            (gastoTotal / limiteTotal)
                .coerceIn(0.0, 1.0)
                .toFloat()
        } else {
            0f
        }

    Scaffold(
        containerColor = CinzaFundo,

        bottomBar = {
            BarraNavegacao(
                navController = navController,
                rotaAtual = Rotas.ORCAMENTO
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
                                    Icons.Filled.AccountBalanceWallet,

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
                                text = "Orçamento",
                                color = Branco,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text =
                                    "Acompanhe seus limites mensais",

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
                            text = "Orçamento total",
                            color = VerdeClaro,
                            fontSize = 13.sp
                        )

                        Text(
                            text =
                                "R$ %.2f"
                                    .format(limiteTotal),

                            color = Branco,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Column {

                                Text(
                                    text = "Gasto",
                                    color = VerdeClaro,
                                    fontSize = 12.sp
                                )

                                Text(
                                    text =
                                        "R$ %.2f"
                                            .format(gastoTotal),

                                    color = Branco,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Column(
                                horizontalAlignment =
                                    Alignment.End
                            ) {

                                Text(
                                    text = "Restante",
                                    color = VerdeClaro,
                                    fontSize = 12.sp
                                )

                                Text(
                                    text =
                                        "R$ %.2f"
                                            .format(restanteTotal),

                                    color =
                                        if (restanteTotal < 0) {
                                            Color(0xFFFF8A80)
                                        } else {
                                            Branco
                                        },

                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            item {

                Text(
                    text = "Orçamento por categoria",

                    color = VerdeEscuro,

                    fontSize = 20.sp,

                    fontWeight = FontWeight.Bold,

                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 5.dp
                    )
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

                val percentual =
                    if (categoria.limite > 0) {
                        (gasto / categoria.limite)
                            .coerceIn(0.0, 1.0)
                            .toFloat()
                    } else {
                        0f
                    }

                val ultrapassou =
                    gasto > categoria.limite

                val cor =
                    if (ultrapassou) {
                        Color(0xFFE53935)
                    } else {
                        VerdePrincipal
                    }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 6.dp
                        ),

                    shape = RoundedCornerShape(20.dp),

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
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text =
                                    "${(percentual * 100).toInt()}%",

                                color = cor,

                                fontSize = 15.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(9.dp)
                                .clip(
                                    RoundedCornerShape(50.dp)
                                )
                                .background(CardVerde)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(percentual)
                                    .height(9.dp)
                                    .clip(
                                        RoundedCornerShape(50.dp)
                                    )
                                    .background(cor)
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceBetween
                        ) {

                            Text(
                                text =
                                    "R$ %.2f gasto"
                                        .format(gasto),

                                color = Cinza,
                                fontSize = 12.sp
                            )

                            Text(
                                text =
                                    "Limite: R$ %.2f"
                                        .format(categoria.limite),

                                color = Cinza,
                                fontSize = 12.sp
                            )
                        }

                        if (ultrapassou) {

                            Spacer(
                                modifier = Modifier.height(10.dp)
                            )

                            Row(
                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector =
                                        Icons.Filled.Warning,

                                    contentDescription = null,

                                    tint =
                                        Color(0xFFE53935),

                                    modifier =
                                        Modifier.size(18.dp)
                                )

                                Text(
                                    text =
                                        " Limite ultrapassado",

                                    color =
                                        Color(0xFFE53935),

                                    fontSize = 12.sp,

                                    fontWeight =
                                        FontWeight.Bold
                                )
                            }
                        }
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