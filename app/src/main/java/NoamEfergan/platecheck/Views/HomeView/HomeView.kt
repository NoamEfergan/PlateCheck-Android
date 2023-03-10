package NoamEfergan.platecheck.Views.HomeView

import NoamEfergan.platecheck.Utils.Network.NetworkService
import NoamEfergan.platecheck.ui.theme.PrimaryGrey
import NoamEfergan.platecheck.ui.theme.SecondaryGrey
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun HomeView() {
    val regNumber = remember { mutableStateOf(TextFieldValue()) }
    val vm = HomeViewVM()

    val textFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = SecondaryGrey,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.width(250.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                TextField(
                    value = regNumber.value,
                    onValueChange = { regNumber.value = it },
                    shape = RoundedCornerShape(8.dp),
                    colors = textFieldColors,
                    placeholder = { PlaceholderText() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp)
                )
                SubmitButton(vm)
            }
        }
    }
}

@Composable
fun SubmitButton(vm: HomeViewVM) {
    Button(
        onClick = { performOnSubmit(vm) },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PrimaryGrey
        )
    ) {
        Text(
            text = "Submit", color = Color.White
        )
    }
}

@Composable
fun PlaceholderText() {
    Text(
        text = "Enter a reg number", color = Color.White, fontWeight = FontWeight.ExtraLight
    )
}

fun performOnSubmit(vm: HomeViewVM) {
    vm.performApiCall()
}