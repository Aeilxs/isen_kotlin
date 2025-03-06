package fr.isen.ISENSmartCompanion.isensmartcompanion

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import fr.isen.ISENSmartCompanion.isensmartcompanion.model.EventDto

@SuppressLint("MissingPermission")
@Composable
fun EventDetailScreen(event: EventDto, onBack: () -> Unit) {
    val ctx = LocalContext.current

    val pinnedKey = "pinned_event_${event.id}"

    var isPinned by remember { mutableStateOf(false) }

    LaunchedEffect(event.id) {
        val prefs = ctx.getSharedPreferences("EventPrefs", android.content.Context.MODE_PRIVATE)
        isPinned = prefs.getBoolean(pinnedKey, false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = event.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                EventDetailSection(
                    title = ctx.getString(R.string.event_screen_description_label),
                    content = event.description
                )
                EventDetailSection(
                    title = ctx.getString(R.string.event_screen_date_label),
                    content = event.date
                )
                EventDetailSection(
                    title = ctx.getString(R.string.event_screen_location_label),
                    content = event.location
                )
                EventDetailSection(
                    title = ctx.getString(R.string.event_screen_category_label),
                    content = event.category
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(
                onClick = onBack,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.app_red)
                ),
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp),
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ctx.getString(R.string.event_screen_back_button_content_description),
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Button(
                onClick = {
                    val newPinnedValue = !isPinned
                    isPinned = newPinnedValue

                    val prefs = ctx.getSharedPreferences("EventPrefs", android.content.Context.MODE_PRIVATE)
                    prefs.edit().putBoolean(pinnedKey, newPinnedValue).apply()

                    if (newPinnedValue) {
                        // (API >= 26)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            val manager = ctx.getSystemService(NotificationManager::class.java)
                            if (manager?.getNotificationChannel(CHANNEL_ID) == null) {
                                val channel = NotificationChannel(
                                    CHANNEL_ID,
                                    "Event Reminders",
                                    NotificationManager.IMPORTANCE_DEFAULT
                                )
                                manager?.createNotificationChannel(channel)
                            }
                        }

                        Handler(Looper.getMainLooper()).postDelayed({
                            val builder = NotificationCompat.Builder(ctx, CHANNEL_ID)
                                .setSmallIcon(android.R.drawable.ic_dialog_info)
                                .setContentTitle("ISEN Event Reminder!")
                                .setContentText("Don't forget : ${event.title} starting soon !")
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

                            NotificationManagerCompat.from(ctx).notify(NOTIFICATION_ID, builder.build())
                        }, 10_000)
                    }
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
                    if (isPinned) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(R.string.event_screen_unsubscribe_btn_content_description),
                            tint = Color.Yellow,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = stringResource(R.string.event_screen_subscribe_btn_content_description),
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EventDetailSection(title: String, content: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            fontSize = 16.sp
        )
    }
}

private const val CHANNEL_ID = "event_reminders_channel"
private const val NOTIFICATION_ID = 1234
