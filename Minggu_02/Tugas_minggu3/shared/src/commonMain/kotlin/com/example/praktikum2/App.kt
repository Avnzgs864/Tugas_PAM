package com.example.praktikum2

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import praktikum2.shared.generated.resources.Res
import praktikum2.shared.generated.resources.profile

data class ContactInfo(
    val label: String,
    val value: String
)

private val PurpleColors = lightColorScheme(
    primary = Color(0xFF7B1FA2),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFF4A148C),
    secondary = Color(0xFFE040FB),
    background = Color(0xFFF3E5F5),
    onBackground = Color(0xFF2A1033),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF2A1033)
)

private val SoftPurpleText = Color(0xFF7E5A8C)

@Composable
@Preview
fun App() {
    MaterialTheme(colorScheme = PurpleColors) {
        ProfileScreen()
    }
}

@Composable
fun ProfileScreen() {
    var showContact by remember { mutableStateOf(true) }

    val contacts = listOf(
        ContactInfo("EMAIL", "refin.124140126@student.itera.ac.id"),
        ContactInfo("TELEPON", "0881-0128-16428"),
        ContactInfo("LOKASI", "Bandar Lampung, Lampung, Indonesia")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileHeader(
            name = "Refin Agus Saputra",
            nim = "124140126",
            major = "Teknik Informatika - ITERA"
        )

        ProfileCard(title = "Tentang Saya") {
            Text(
                text = "Halo! Saya Refin, mahasiswa Teknik Informatika Institut Teknologi " +
                        "Sumatera yang sedang belajar membuat halaman profil dengan " +
                        "Compose Multiplatform.",
                color = SoftPurpleText,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )
        }

        ProfileActions(
            isContactVisible = showContact,
            onToggleContact = { showContact = !showContact }
        )

        AnimatedVisibility(
            visible = showContact,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            ProfileCard(title = "Informasi Kontak") {
                contacts.forEachIndexed { index, contact ->
                    InfoItem(label = contact.label, value = contact.value)
                    if (index != contacts.lastIndex) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.15f))
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun ProfileHeader(
    name: String,
    nim: String,
    major: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        // Banner ungu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(
                    Brush.linearGradient(
                        listOf(
                            MaterialTheme.colorScheme.primaryContainer,
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 120.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(6.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                        .padding(horizontal = 14.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "NIM $nim",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = major, fontSize = 13.sp, color = SoftPurpleText)
            }
        }

        Image(
            painter = painterResource(Res.drawable.profile),
            contentDescription = "Foto profil $name",
            modifier = Modifier
                .padding(top = 70.dp)
                .size(100.dp)
                .clip(CircleShape)
                .border(4.dp, MaterialTheme.colorScheme.surface, CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun ProfileActions(
    isContactVisible: Boolean,
    onToggleContact: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        OutlinedButton(
            onClick = onToggleContact,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = if (isContactVisible) "Hide Contact" else "Show Contact",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun ProfileCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(3.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
fun InfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
