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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contae.data.model.Categoria
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
fun TelaCategorias(navController: NavController, categoriaViewModel: CategoriaViewModel, despesaViewModel: DespesaViewModel) {
 val categoriaState by categoriaViewModel.uiState.collectAsState()
 val despesaState by despesaViewModel.uiState.collectAsState()
var nome by remember {
        mutableStateOf("")
    }

    var limite by remember {
        mutableStateOf("")
    }

    var categoriaEditandoId by remember {
        mutableStateOf<Int?>(null)
    }

    Scaffold(
        containerColor = CinzaFundo,

        bottomBar = {
            BarraNavegacao(
                navController = navController,
                rotaAtual = Rotas.CATEGORIAS
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
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(VerdePrincipal),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Filled.Category,
                                contentDescription = null,
                                tint = Branco,
                                modifier = Modifier.size(26.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column {

                            Text(
                                text = "Categorias",
                                color = Branco,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Organize seus limites de gastos",
                                color = VerdeClaro,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 3.dp)
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
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Branco
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 3.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(18.dp)
                    ) {

                        Text(
                            text = if (categoriaEditandoId == null)
                                "Nova categoria"
                            else
                                "Editar categoria",
                            color = VerdeEscuro,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = if (categoriaEditandoId == null)
                                "Defina um limite para controlar seus gastos"
                            else
                                "Atualize as informações da categoria",
                            color = Cinza,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(
                                top = 4.dp,
                                bottom = 15.dp
                            )
                        )

                        OutlinedTextField(
                            value = nome,
                            onValueChange = {
                                nome = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Nome da categoria")
                            },
                            placeholder = {
                                Text("Ex.: Alimentação")
                            },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Category,
                                    contentDescription = null,
                                    tint = VerdePrincipal
                                )
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = limite,
                            onValueChange = {
                                limite = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text("Limite mensal")
                            },
                            placeholder = {
                                Text("Ex.: 500")
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            )
                        )

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        Button(
                            onClick = {

                                val limiteNumero = limite
                                    .replace(",", ".")
                                    .toDoubleOrNull()

                                if (
                                    nome.isNotBlank() &&
                                    limiteNumero != null &&
                                    limiteNumero > 0
                                ) {

                                    val id = categoriaEditandoId
                                    if (id == null) {
                                        categoriaViewModel.salvarCategoria(
                                            Categoria(
                                                nome = nome.trim(),
                                                limite = limiteNumero
                                            )
                                        )
                                    } else {
                                        categoriaViewModel.salvarCategoria(
                                            Categoria(
                                                id = id,
                                                nome = nome.trim(),
                                                limite = limiteNumero
                                            )
                                        )
                                    }

                                    nome = ""
                                    limite = ""
                                    categoriaEditandoId = null
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = VerdePrincipal
                            )
                        ) {

                            Icon(
                                imageVector =
                                    if (categoriaEditandoId == null)
                                        Icons.Filled.Add
                                    else
                                        Icons.Filled.Save,
                                contentDescription = null
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text =
                                    if (categoriaEditandoId == null)
                                        "Adicionar categoria"
                                    else
                                        "Salvar alterações",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (categoriaEditandoId != null) {

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Button(
                                onClick = {
                                    nome = ""
                                    limite = ""
                                    categoriaEditandoId = null
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = VerdeEscuro
                                )
                            ) {

                                Text("Cancelar edição")
                            }
                        }
                    }
                }
            }


            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 2.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Minhas categorias",
                        color = VerdeEscuro,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${categoriaState.categorias.size} cadastradas",
                        color = VerdePrincipal,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }


            items(
                items = categoriaState.categorias,
                key = { it.id }
            ) { categoria ->

                val totalGasto = despesaState.despesas
                    .filter {
                        it.categoriaId == categoria.id
                    }
                    .sumOf {
                        it.valor
                    }

                val porcentagem =
                    if (categoria.limite > 0) {
                        (totalGasto / categoria.limite)
                            .coerceIn(0.0, 1.0)
                            .toFloat()
                    } else {
                        0f
                    }

                Card(
                    onClick = {
                        navController.navigate(
                            Rotas.detalhesCategoria(
                                categoria.id
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 6.dp
                        ),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Branco
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(17.dp)
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(15.dp))
                                    .background(CardVerde),
                                contentAlignment = Alignment.Center
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.Category,
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
                                    text = categoria.nome,
                                    color = VerdeEscuro,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Limite: R$ %.2f"
                                        .format(categoria.limite),
                                    color = Cinza,
                                    fontSize = 13.sp,
                                    modifier = Modifier.padding(top = 3.dp)
                                )
                            }

                            IconButton(
                                onClick = {

                                    nome = categoria.nome
                                    limite = categoria.limite
                                        .toString()
                                    categoriaEditandoId =
                                        categoria.id
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.Edit,
                                    contentDescription = "Editar",
                                    tint = VerdePrincipal
                                )
                            }

                            IconButton(
                                onClick = {

                                    categoriaViewModel.deletarCategoria(categoria)
                                }
                            ) {

                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Excluir",
                                    tint = Color(0xFFE53935)
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                text = "Gasto atual",
                                color = Cinza,
                                fontSize = 12.sp
                            )

                            Text(
                                text = "R$ %.2f"
                                    .format(totalGasto),
                                color = VerdeEscuro,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(7.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(CardVerde)
                        ) {

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(
                                        porcentagem
                                    )
                                    .height(8.dp)
                                    .clip(
                                        RoundedCornerShape(
                                            50.dp
                                        )
                                    )
                                    .background(
                                        if (porcentagem >= 0.9f)
                                            Color(0xFFE53935)
                                        else
                                            VerdePrincipal
                                    )
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(7.dp)
                        )

                        Text(
                            text = "${(porcentagem * 100).toInt()}% do limite utilizado",
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