import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex3g.moneyflow.R

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String? = null,
    onValueChange: (String) -> Unit,
    label: String = "Valor",
    @DrawableRes iconRes: Int,
    keywordType: KeyboardType,
    isError: Boolean = false,
    errorMessage: String = ""
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            placeholder = { Text(placeholder?.toString() ?: "") },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = iconRes),
                    contentDescription = "Ícone de moeda",
                    tint = Color(0xFFFFA500)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keywordType),
            isError = isError,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFFA500),
                unfocusedBorderColor = Color.Gray,
                errorBorderColor = Color.Red,
                focusedTextColor = Color.Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        )

        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                //style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

@Preview()
@Composable
fun TextFieldPreview() {
    var amount by remember { mutableStateOf("") }

//    CustomOutlinedTextField(
//        modifier = Modifier,
//        value = amount,
//        onValueChange = { amount = it },
//        isError = amount.isNotEmpty() && amount.toDoubleOrNull() == null,
//        errorMessage = "Insira um valor numérico válido"
//    )
}
