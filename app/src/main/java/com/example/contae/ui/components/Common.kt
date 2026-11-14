package com.example.contae.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import com.example.contae.ui.navigation.Rotas
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdeFundo
import com.example.contae.ui.theme.VerdePrincipal


@Composable
fun CabecalhoConta(
    titulo: String,
    subtitulo: String? = null
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(VerdeEscuro)
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {

        Text(
            text = titulo,
            color = Branco,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        if (subtitulo != null) {

            Text(
                text = subtitulo,
                color = VerdeClaro,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun CardDestaque(
    titulo: String,
    valor: String,
    icone: @Composable () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(VerdeEscuro)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column {

            Text(
                text = titulo,
                color = VerdeClaro,
                fontSize = 14.sp
            )

            Text(
                text = valor,
                color = Branco,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 6.dp)
            )
        }

        icone()
    }
}


@Composable
fun CardInformacao(
    titulo: String,
    valor: String,
    cor: Color = VerdePrincipal,
    icone: @Composable () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Branco)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(cor.copy(alpha = 0.12f))
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            icone()
        }

        Column(
            modifier = Modifier
                .padding(start = 14.dp)
        ) {

            Text(
                text = titulo,
                color = Cinza,
                fontSize = 13.sp
            )

            Text(
                text = valor,
                color = VerdeEscuro,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 3.dp)
            )
        }
    }
}



@Composable
fun FundoConta(
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(CinzaFundo)
    ) {

        content()
    }
}


@Composable
fun TituloSecao(
    titulo: String,
    modifier: Modifier = Modifier
) {

    Text(
        text = titulo,
        color = VerdeEscuro,
        fontSize = 19.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(
            bottom = 8.dp
        )
    )
}


@Composable
fun BarraNavegacao(
    navController: NavController,
    rotaAtual: String? = null
) {

    NavigationBar(
        containerColor = Branco,
        tonalElevation = 8.dp
    ) {

        NavigationBarItem(

            selected = rotaAtual == Rotas.HOME,

            onClick = {
                navController.navigate(Rotas.HOME) {
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Início"
                )
            },

            label = {
                Text("Início")
            }
        )

        NavigationBarItem(

            selected = rotaAtual == Rotas.CATEGORIAS,

            onClick = {
                navController.navigate(Rotas.CATEGORIAS) {
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Filled.Category,
                    contentDescription = "Categorias"
                )
            },

            label = {
                Text("Categorias")
            }
        )

        NavigationBarItem(

            selected = rotaAtual == Rotas.DESPESAS,

            onClick = {
                navController.navigate(Rotas.DESPESAS) {
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Filled.ReceiptLong,
                    contentDescription = "Despesas"
                )
            },

            label = {
                Text("Despesas")
            }
        )

        NavigationBarItem(

            selected = rotaAtual == Rotas.ORCAMENTO,

            onClick = {
                navController.navigate(Rotas.ORCAMENTO) {
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountBalanceWallet,
                    contentDescription = "Orçamento"
                )
            },

            label = {
                Text("Orçamento")
            }
        )

        NavigationBarItem(

            selected = rotaAtual == Rotas.RELATORIO,

            onClick = {
                navController.navigate(Rotas.RELATORIO) {
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Filled.BarChart,
                    contentDescription = "Relatório"
                )
            },

            label = {
                Text("Relatório")
            }
        )
    }
}