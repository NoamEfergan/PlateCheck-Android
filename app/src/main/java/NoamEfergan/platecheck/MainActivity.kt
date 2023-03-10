package NoamEfergan.platecheck

import NoamEfergan.platecheck.ui.theme.BackgroundYellow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import NoamEfergan.platecheck.ui.theme.PlateCheckTheme
import NoamEfergan.platecheck.ui.theme.PrimaryGrey
import NoamEfergan.platecheck.ui.theme.SecondaryGrey
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlateCheckTheme {
                MainView()
            }
        }
    }
}

@Composable
fun MainView() {
    val regNumber = remember { mutableStateOf(TextFieldValue()) }

    val textFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = SecondaryGrey,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
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
                SubmitButton()
            }
        }
    }
}

@Composable
fun SubmitButton() {
    Button(
        onClick = { performOnSubmit() },
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PrimaryGrey
        )
    ) {
        Text(
            text = "Submit",
            color = Color.White
        )
    }
}

@Composable
fun PlaceholderText() {
    Text(
        text = "Enter a reg number",
        color = Color.White,
        fontWeight = FontWeight.ExtraLight
    )
}

fun performOnSubmit() {
    Log.i("Noam", "Submit button clicked")
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    PlateCheckTheme {
        MainView()
    }
}