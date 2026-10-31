package com.example.contae.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@Composable
fun TelaLogin(
    onLogin: () -> Unit
) {

    var usuario by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CinzaFundo)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(78.dp)
                    .clip(CircleShape)
                    .background(VerdeEscuro),

                contentAlignment =
                    Alignment.Center
            ) {

                Icon(
                    imageVector =
                        Icons.Filled.AccountBalanceWallet,

                    contentDescription = null,

                    tint = Branco,

                    modifier =
                        Modifier.size(40.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Bem-vindo!",
                color = VerdeEscuro,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Entre na sua conta",
                color = Cinza,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )



            OutlinedTextField(
                value = usuario,

                onValueChange = {
                    usuario = it
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Usuário")
                },

                placeholder = {
                    Text("Digite seu usuário")
                },

                singleLine = true,

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = VerdePrincipal
                    )
                },

                shape = RoundedCornerShape(14.dp)
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )


            OutlinedTextField(
                value = senha,

                onValueChange = {
                    senha = it
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Senha")
                },

                placeholder = {
                    Text("Digite sua senha")
                },

                singleLine = true,

                visualTransformation =
                    PasswordVisualTransformation(),

                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = VerdePrincipal
                    )
                },

                shape = RoundedCornerShape(14.dp)
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            Button(
                onClick = {
                    onLogin()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdePrincipal
                )
            ) {

                Text(
                    text = "Entrar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = "Use qualquer usuário e senha para entrar.",
                color = VerdeClaro,
                fontSize = 12.sp
            )
        }
    }
}