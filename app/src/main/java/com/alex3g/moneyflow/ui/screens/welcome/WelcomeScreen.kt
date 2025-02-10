package com.alex3g.moneyflow.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex3g.moneyflow.R
import com.alex3g.moneyflow.ui.components.button.MoneyFlowButton
import com.alex3g.moneyflow.ui.components.welcome.WelcomeContent
import com.alex3g.moneyflow.ui.components.welcome.WelcomeHeader

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToHome: () -> Unit) {
    Column(
        modifier = modifier
            .background(Color.White)
            .padding(horizontal = 32.dp, vertical = 32.dp)
            .fillMaxSize()
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WelcomeHeader()

        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painterResource(id = R.drawable.img_context),
                contentDescription = "Imagem ilustrativa",
                modifier = modifier
                    .size(160.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        WelcomeContent()

        MoneyFlowButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            text = "Avançar",
            onClick = onNavigateToHome
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToHome = {})
}