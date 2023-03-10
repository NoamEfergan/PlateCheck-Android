package NoamEfergan.platecheck.Views.HomeView

import NoamEfergan.platecheck.ui.theme.PrimaryGrey
import NoamEfergan.platecheck.ui.theme.SecondaryGrey
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.flow.asSharedFlow

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
        LoadingView(vm)
        ErrorView(vm)
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
fun LoadingView(vm: HomeViewVM) {
    if (vm.showDialog) {
        Dialog(
            onDismissRequest = { vm.showDialog = false },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun ErrorView(vm: HomeViewVM) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        vm.errorMessege.asSharedFlow().collect { message ->
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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
            text = "Submit", color = White
        )
    }
}

@Composable
fun PlaceholderText() {
    Text(
        text = "Enter a reg number", color = White, fontWeight = FontWeight.ExtraLight
    )
}

fun performOnSubmit(vm: HomeViewVM) {
    vm.performApiCall()
}