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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contae.ui.theme.Branco
import com.example.contae.ui.theme.Cinza
import com.example.contae.ui.theme.CinzaFundo
import com.example.contae.ui.theme.VerdeClaro
import com.example.contae.ui.theme.VerdeEscuro
import com.example.contae.ui.theme.VerdePrincipal

@Composable
fun TelaInicial(
    onComecar: () -> Unit
) {

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
                    .size(100.dp)
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
                        Modifier.size(52.dp)
                )
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Text(
                text = "Contaê",
                color = VerdeEscuro,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Vamos começar?",
                color = VerdePrincipal,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            Text(
                text =
                    "Organize suas despesas, acompanhe seu orçamento e tenha mais controle sobre seu dinheiro.",

                color = Cinza,

                fontSize = 15.sp,

                lineHeight = 22.sp,

                modifier = Modifier.fillMaxWidth(),

                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(35.dp)
            )

            Button(
                onClick = {
                    onComecar()
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
                    text = "Vamos começar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                Icon(
                    imageVector =
                        Icons.Filled.ArrowForward,

                    contentDescription = null
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "Controle simples. Decisões melhores.",
                color = VerdeClaro,
                fontSize = 12.sp
            )
        }
    }
}