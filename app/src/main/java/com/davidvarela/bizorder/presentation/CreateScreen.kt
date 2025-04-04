package com.davidvarela.bizorder.presentation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.davidvarela.bizorder.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateScreen(
    viewModel: PreOrderViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var productName by rememberSaveable { mutableStateOf("") }
    var customerName by rememberSaveable { mutableStateOf("") }
    var isButtonEnabled by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is CreateEvent.Success -> {
                    Toast.makeText(
                        context,
                        "PreOrder guardada y enviada correctamente al servidor",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is CreateEvent.Error -> {
                    Toast.makeText(
                        context,
                        "PreOrder guardada localmente. El envío al servidor falló.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            productName = ""
            customerName = ""
            isButtonEnabled = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.top_image),
            contentDescription = "Formulario",
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(8.dp))
                .padding(bottom = 24.dp),
            contentScale = ContentScale.Crop
        )

        TextField(
            value = productName,
            onValueChange = {
                productName = it
                isButtonEnabled = productName.isNotEmpty() && customerName.isNotEmpty()
            },
            label = { Text("Nombre del producto") },
            modifier =  Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = customerName,
            onValueChange = {
                customerName = it
                isButtonEnabled = productName.isNotEmpty() && customerName.isNotEmpty()
            },
            label = { Text("Nombre del cliente") },
            modifier =  Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.onSavePreOrder(customerName, productName)
            },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar PreOrder")
        }
    }

}