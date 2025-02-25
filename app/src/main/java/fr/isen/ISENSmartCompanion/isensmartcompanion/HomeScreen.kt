import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.ISENSmartCompanion.isensmartcompanion.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    var buf by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.isen_logo),
                contentDescription = ctx.getString(R.string.isen_logo_content_description),
                modifier = Modifier.size(250.dp, 100.dp)
            )
            Text(
                text = ctx.getString(R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.DarkGray
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = buf,
                onValueChange = { buf = it },
                placeholder = { Text(ctx.getString(R.string.text_field_label)) },
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = colorResource(R.color.app_red)
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
            )

            Spacer(modifier = Modifier.width(4.dp))

            Button(
                onClick = {
                    Toast.makeText(ctx, ctx.getString(R.string.toast_message), Toast.LENGTH_SHORT).show()
                    buf = ""
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.app_red)
                ),
                modifier = Modifier.size(48.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.Transparent,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = ctx.getString(R.string.send_button_content_description),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

        }
    }
}
