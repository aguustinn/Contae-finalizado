package com.example.contae.ui.screens
import androidx.compose.runtime.collectAsState
import com.example.contae.viewmodel.CategoriaViewModel
import com.example.contae.viewmodel.DespesaViewModel


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.contae.data.model.Despesa
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.CardVerde
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDetalhesDespesa(id: Int, navController: NavController, despesaViewModel: DespesaViewModel) {
 val uiState by despesaViewModel.uiState.collectAsState()

 val despesa = uiState.despesas.firstOrNull { it.id == id }
 val carregando = uiState.carregando

    if (despesa == null) {

        Scaffold(
            containerColor = CinzaFundo,

            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Despesa",
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

                horizontalAlignment =
                    Alignment.CenterHorizontally,

                verticalArrangement =
                    Arrangement.Center
            ) {

                Text(
                    text = if (carregando) "Carregando despesa..." else "Despesa não encontrada",
                    color = VerdeEscuro,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Button(
                    onClick = {
                        navController.popBackStack()
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdePrincipal
                    )
                ) {
                    Text("Voltar")
                }
            }
        }

        return
    }

    val despesaAtual = despesa ?: return

    val categoria = uiState.categorias.find {
        it.id == despesaAtual.categoriaId
    }

    Scaffold(
        containerColor = CinzaFundo,

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalhes da despesa",
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
                .padding(20.dp)
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
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        androidx.compose.foundation.layout.Box(
                            modifier = Modifier
                                .size(58.dp)
                                .clip(CircleShape)
                                .background(VerdePrincipal),

                            contentAlignment =
                                Alignment.Center
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Filled.AttachMoney,

                                contentDescription = null,

                                tint = Branco,

                                modifier =
                                    Modifier.size(30.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(15.dp)
                        )

                        Column {

                            Text(
                                text = despesaAtual.descricao,
                                color = Branco,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Detalhes do gasto",
                                color = VerdeClaro,
                                fontSize = 13.sp
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(25.dp)
                    )

                    Text(
                        text = "Valor da despesa",
                        color = VerdeClaro,
                        fontSize = 13.sp
                    )

                    Text(
                        text =
                            "R$ %.2f"
                                .format(despesaAtual.valor),

                        color = Branco,

                        fontSize = 31.sp,

                        fontWeight = FontWeight.Bold
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
                )
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = "Informações",
                        color = VerdeEscuro,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Categoria",
                            color = Cinza
                        )

                        Text(
                            text =
                                categoria?.nome
                                    ?: "Não encontrada",

                            color = VerdeEscuro,

                            fontWeight =
                                FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Data",
                            color = Cinza
                        )

                        Text(
                            text = despesaAtual.data,
                            color = VerdeEscuro,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),

                        horizontalArrangement =
                            Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "ID da despesa",
                            color = Cinza
                        )

                        Text(
                            text = despesaAtual.id.toString(),
                            color = VerdeEscuro,
                            fontWeight = FontWeight.Bold
                        )
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
                    containerColor = CardVerde
                )
            ) {

                Row(
                    modifier = Modifier.padding(16.dp),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.Category,
                        contentDescription = null,
                        tint = VerdePrincipal
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(
                        text =
                            "Esta despesa está vinculada à categoria " +
                                    "\"${categoria?.nome ?: "não encontrada"}\".",

                        color = VerdeEscuro,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}