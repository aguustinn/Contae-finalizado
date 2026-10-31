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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contae.data.model.Despesa
import com.example.contae.ui.components.BarraNavegacao
import com.example.contae.ui.navigation.Rotas
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.CardVerde
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaDespesas(navController: NavController, despesaViewModel: DespesaViewModel) {
 val uiState by despesaViewModel.uiState.collectAsState()
var descricao by remember {
        mutableStateOf("")
    }

    var valor by remember {
        mutableStateOf("")
    }

    var data by remember {
        mutableStateOf("")
    }

    var categoriaIdTexto by remember {
        mutableStateOf("")
    }

    var categoriasExpandidas by remember { mutableStateOf(false) }

    var despesaEditandoId by remember {
        mutableStateOf<Int?>(null)
    }

    var mensagem by remember {
        mutableStateOf("")
    }

    Scaffold(
        containerColor = CinzaFundo,

        bottomBar = {
            BarraNavegacao(
                navController = navController,
                rotaAtual = Rotas.DESPESAS
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
                                imageVector = Icons.Filled.AttachMoney,
                                contentDescription = null,
                                tint = Branco,
                                modifier = Modifier.size(27.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column {

                            Text(
                                text = "Despesas",
                                color = Branco,
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Controle seus gastos do dia a dia",
                                color = VerdeClaro,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(top = 3.dp)
                            )
                        }
                    }
                }
            }

            // =========================================================
            // FORMULÁRIO
            // =========================================================

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
                            text = if (despesaEditandoId == null) {
                                "Nova despesa"
                            } else {
                                "Editar despesa"
                            },

                            color = VerdeEscuro,
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = if (despesaEditandoId == null) {
                                "Registre um novo gasto"
                            } else {
                                "Atualize os dados da despesa"
                            },

                            color = Cinza,
                            fontSize = 13.sp,

                            modifier = Modifier.padding(
                                top = 4.dp,
                                bottom = 15.dp
                            )
                        )

                        OutlinedTextField(
                            value = descricao,
                            onValueChange = {
                                descricao = it
                            },

                            modifier = Modifier.fillMaxWidth(),

                            label = {
                                Text("Descrição")
                            },

                            placeholder = {
                                Text("Ex.: Mercado")
                            },

                            singleLine = true
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = valor,
                            onValueChange = {
                                valor = it
                            },

                            modifier = Modifier.fillMaxWidth(),

                            label = {
                                Text("Valor")
                            },

                            placeholder = {
                                Text("Ex.: 150")
                            },

                            singleLine = true,

                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal
                            )
                        )

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        ExposedDropdownMenuBox(
                            expanded = categoriasExpandidas,
                            onExpandedChange = { categoriasExpandidas = it }
                        ) {
                            val categoriaSelecionada = uiState.categorias.find {
                                it.id.toString() == categoriaIdTexto
                            }
                            OutlinedTextField(
                                value = categoriaSelecionada?.nome ?: "",
                                onValueChange = {},
                                readOnly = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                label = { Text("Categoria") },
                                placeholder = { Text("Selecione uma categoria") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = categoriasExpandidas
                                    )
                                },
                                singleLine = true
                            )
                            ExposedDropdownMenu(
                                expanded = categoriasExpandidas,
                                onDismissRequest = { categoriasExpandidas = false }
                            ) {
                                uiState.categorias.forEach { categoria ->
                                    DropdownMenuItem(
                                        text = { Text(categoria.nome) },
                                        onClick = {
                                            categoriaIdTexto = categoria.id.toString()
                                            categoriasExpandidas = false
                                            mensagem = ""
                                        }
                                    )
                                }
                            }
                        }
                        if (uiState.categorias.isEmpty()) {
                            Text(
                                text = "Nenhuma categoria cadastrada. Cadastre uma na tela Categorias.",
                                color = Color(0xFFD32F2F),
                                fontSize = 12.sp
                            )
                        }

                        Spacer(
                            modifier = Modifier.height(12.dp)
                        )

                        OutlinedTextField(
                            value = data,
                            onValueChange = {
                                data = it
                            },

                            modifier = Modifier.fillMaxWidth(),

                            label = {
                                Text("Data")
                            },

                            placeholder = {
                                Text("Ex.: 05/10/2026")
                            },

                            singleLine = true
                        )

                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )

                        Button(
                            onClick = {
                                val valorNumero = valor.trim()
                                    .replace(",", ".")
                                    .toDoubleOrNull()

                                val categoriaId = categoriaIdTexto.trim()
                                    .toIntOrNull()

                                when {
                                    descricao.isBlank() -> {
                                        mensagem = "Informe a descrição da despesa."
                                    }

                                    valorNumero == null || valorNumero <= 0 -> {
                                        mensagem = "Informe um valor válido maior que zero."
                                    }

                                    categoriaId == null -> {
                                        mensagem = "Selecione uma categoria."
                                    }

                                    uiState.categorias.none { it.id == categoriaId } -> {
                                        mensagem = "Essa categoria não existe mais. Selecione outra."
                                    }

                                    data.isBlank() -> {
                                        mensagem = "Informe a data da despesa."
                                    }

                                    else -> {
                                        mensagem = "Salvando despesa..."

                                        val despesa = Despesa(
                                            id = despesaEditandoId ?: 0,
                                            descricao = descricao.trim(),
                                            valor = valorNumero,
                                            categoriaId = categoriaId,
                                            data = data.trim()
                                        )

                                        despesaViewModel.salvarDespesa(despesa) { sucesso, resultado ->
                                            mensagem = resultado

                                            if (sucesso) {
                                                descricao = ""
                                                valor = ""
                                                data = ""
                                                categoriaIdTexto = ""
                                                despesaEditandoId = null
                                            }
                                        }
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = VerdePrincipal
                            )
                        ) {
                            Icon(
                                imageVector = if (despesaEditandoId == null) {
                                    Icons.Filled.AttachMoney
                                } else {
                                    Icons.Filled.Save
                                },
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = if (despesaEditandoId == null) {
                                    "Adicionar despesa"
                                } else {
                                    "Salvar alterações"
                                },
                                fontWeight = FontWeight.Bold
                            )
                        }

                        if (mensagem.isNotBlank()) {
                            Text(
                                text = mensagem,
                                color = if (
                                    mensagem.startsWith("Erro") ||
                                    mensagem.startsWith("Informe") ||
                                    mensagem.startsWith("Essa")
                                ) {
                                    Color(0xFFD32F2F)
                                } else {
                                    VerdePrincipal
                                },
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }

                        if (despesaEditandoId != null) {

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Button(
                                onClick = {

                                    descricao = ""
                                    valor = ""
                                    data = ""
                                    categoriaIdTexto = ""
                                    despesaEditandoId = null
                                    mensagem = ""
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

            // =========================================================
            // TÍTULO DA LISTA
            // =========================================================

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 2.dp
                        ),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "Minhas despesas",
                        color = VerdeEscuro,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${uiState.despesas.size} registradas",
                        color = VerdePrincipal,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
            }

            // =========================================================
            // LISTA
            // =========================================================

            items(
                items = uiState.despesas,
                key = {
                    it.id
                }
            ) { despesa ->

                val categoria =
                    uiState.categorias.find {
                        it.id == despesa.categoriaId
                    }

                Card(
                    onClick = {

                        navController.navigate(
                            Rotas.detalhesDespesa(
                                despesa.id
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
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(
                                        RoundedCornerShape(15.dp)
                                    )
                                    .background(CardVerde),

                                contentAlignment =
                                    Alignment.Center
                            ) {

                                Icon(
                                    imageVector =
                                        Icons.Filled.AttachMoney,

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
                                    text = despesa.descricao,
                                    color = VerdeEscuro,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text =
                                        categoria?.nome
                                            ?: "Sem categoria",

                                    color = Cinza,
                                    fontSize = 13.sp,

                                    modifier =
                                        Modifier.padding(top = 3.dp)
                                )

                                Text(
                                    text = despesa.data,
                                    color = Cinza,
                                    fontSize = 12.sp,

                                    modifier =
                                        Modifier.padding(top = 2.dp)
                                )
                            }

                            Column(
                                horizontalAlignment =
                                    Alignment.End
                            ) {

                                Text(
                                    text =
                                        "R$ %.2f"
                                            .format(despesa.valor),

                                    color = VerdeEscuro,

                                    fontSize = 15.sp,

                                    fontWeight =
                                        FontWeight.Bold
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(4.dp)
                                )

                                Row {

                                    IconButton(
                                        onClick = {

                                            descricao =
                                                despesa.descricao

                                            valor =
                                                despesa.valor
                                                    .toString()

                                            data =
                                                despesa.data

                                            categoriaIdTexto =
                                                despesa.categoriaId
                                                    .toString()

                                            despesaEditandoId =
                                                despesa.id
                                        }
                                    ) {

                                        Icon(
                                            imageVector =
                                                Icons.Filled.Edit,

                                            contentDescription =
                                                "Editar",

                                            tint =
                                                VerdePrincipal
                                        )
                                    }

                                    IconButton(
                                        onClick = {

                                            despesaViewModel.deletarDespesa(despesa)
                                        }
                                    ) {

                                        Icon(
                                            imageVector =
                                                Icons.Filled.Delete,

                                            contentDescription =
                                                "Excluir",

                                            tint =
                                                Color(0xFFE53935)
                                        )
                                    }
                                }
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
