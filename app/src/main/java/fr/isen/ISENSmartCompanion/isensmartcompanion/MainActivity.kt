package fr.isen.ISENSmartCompanion.isensmartcompanion

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.ISENSmartCompanion.isensmartcompanion.ui.theme.ISENSmartCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ISENSmartCompanionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var userInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.isen_logo),
            contentDescription = context.getString(R.string.isen_logo_content_description),
            modifier = Modifier.size(250.dp, 100.dp)
        )

        Text(
            text = context.getString(R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = userInput,
                onValueChange = { userInput = it },
                placeholder = { Text(context.getString(R.string.text_field_label)) },
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFF5F5F5), shape = MaterialTheme.shapes.small)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = {
                    Log.d("ISENApp", "Question submitted: $userInput")
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFB71C1C) // Rouge foncé
                ),
                modifier = Modifier.size(48.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = context.getString(R.string.send_button_content_description),
                    modifier = Modifier.size(24.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ISENSmartCompanionTheme {
        MainScreen()
    }
}
