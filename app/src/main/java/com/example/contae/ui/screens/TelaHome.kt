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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.contae.ui.theme.CardAzul
import com.example.contae.ui.theme.CardVerde
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@Composable
fun TelaHome(navController: NavController, categoriaViewModel: CategoriaViewModel, despesaViewModel: DespesaViewModel) {
 val categoriaState by categoriaViewModel.uiState.collectAsState()
 val despesaState by despesaViewModel.uiState.collectAsState()
val totalDespesas = despesaState.despesas.sumOf { it.valor }

    val totalCategorias = categoriaState.categorias.size

    Scaffold(
        containerColor = CinzaFundo,

        bottomBar = {
            BarraNavegacao(
                navController = navController,
                rotaAtual = Rotas.HOME
            )
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(CinzaFundo)
        ) {


            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(VerdeEscuro)
                        .padding(
                            start = 22.dp,
                            end = 22.dp,
                            top = 26.dp,
                            bottom = 34.dp
                        )
                ) {

                    Column {

                        Text(
                            text = "Olá! 👋",
                            color = Branco,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Bem-vindo ao Contaê",
                            color = VerdeClaro,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(top = 5.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(24.dp)
                        )


                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Branco
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {

                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Column {

                                        Text(
                                            text = "Total de despesas",
                                            color = Cinza,
                                            fontSize = 14.sp
                                        )

                                        Text(
                                            text = "R$ %.2f".format(totalDespesas),
                                            color = VerdeEscuro,
                                            fontSize = 29.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(top = 5.dp)
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .size(52.dp)
                                            .clip(CircleShape)
                                            .background(CardVerde),
                                        contentAlignment = Alignment.Center
                                    ) {

                                        Icon(
                                            imageVector = Icons.Filled.AccountBalanceWallet,
                                            contentDescription = "Despesas",
                                            tint = VerdePrincipal,
                                            modifier = Modifier.size(27.dp)
                                        )
                                    }
                                }

                                Spacer(
                                    modifier = Modifier.height(18.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(
                                            VerdePrincipal.copy(alpha = 0.15f)
                                        )
                                )

                                Spacer(
                                    modifier = Modifier.height(14.dp)
                                )

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Icon(
                                        imageVector = Icons.Filled.TrendingUp,
                                        contentDescription = null,
                                        tint = VerdePrincipal,
                                        modifier = Modifier.size(18.dp)
                                    )

                                    Spacer(
                                        modifier = Modifier.width(7.dp)
                                    )

                                    Text(
                                        text = "$totalCategorias categorias cadastradas",
                                        color = Cinza,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }


            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 22.dp
                        )
                ) {

                    Text(
                        text = "Resumo financeiro",
                        color = VerdeEscuro,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        MiniCard(
                            modifier = Modifier.weight(1f),
                            titulo = "Categorias",
                            valor = totalCategorias.toString(),
                            icon = Icons.Filled.Category,
                            background = CardVerde
                        )

                        MiniCard(
                            modifier = Modifier.weight(1f),
                            titulo = "Despesas",
                            valor = despesaState.despesas.size.toString(),
                            icon = Icons.Filled.ReceiptLong,
                            background = CardAzul
                        )
                    }
                }
            }


            item {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp
                        )
                ) {

                    Text(
                        text = "Acesso rápido",
                        color = VerdeEscuro,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    BotaoHome(
                        titulo = "Minhas categorias",
                        descricao = "Organize seus limites de gastos",
                        icon = Icons.Filled.Category,
                        onClick = {
                            navController.navigate(
                                Rotas.CATEGORIAS
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    BotaoHome(
                        titulo = "Minhas despesas",
                        descricao = "Veja e controle seus gastos",
                        icon = Icons.Filled.ReceiptLong,
                        onClick = {
                            navController.navigate(
                                Rotas.DESPESAS
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    BotaoHome(
                        titulo = "Meu orçamento",
                        descricao = "Acompanhe seus limites",
                        icon = Icons.Filled.AccountBalanceWallet,
                        onClick = {
                            navController.navigate(
                                Rotas.ORCAMENTO
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )
                }
            }
        }
    }
}



@Composable
private fun MiniCard(
    modifier: Modifier,
    titulo: String,
    valor: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    background: Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Branco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(background),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = VerdePrincipal,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = titulo,
                color = Cinza,
                fontSize = 13.sp
            )

            Text(
                text = valor,
                color = VerdeEscuro,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}



@Composable
private fun BotaoHome(
    titulo: String,
    descricao: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Branco
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(CardVerde),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = VerdePrincipal,
                    modifier = Modifier.size(25.dp)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 14.dp)
            ) {

                Text(
                    text = titulo,
                    color = VerdeEscuro,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = descricao,
                    color = Cinza,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 3.dp)
                )
            }

            IconButton(
                onClick = onClick
            ) {

                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Abrir",
                    tint = VerdePrincipal
                )
            }
        }
    }
}