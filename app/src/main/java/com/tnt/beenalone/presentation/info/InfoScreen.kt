package com.tnt.beenalone.presentation.info

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tnt.beenalone.R
import com.tnt.beenalone.ui.components.CustomBackAppBar
import com.tnt.beenalone.ui.theme.BeenAloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CustomBackAppBar(title = "Thông tin ứng dụng", navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding() + 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.4f),
                painter = painterResource(id = R.drawable.heart_icon),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Phiên bản 1.0", fontWeight = FontWeight(600))
            Spacer(modifier = Modifier.height(20.dp))
            ItemInfo(icon = R.drawable.internet_icon, title = "ngoctientnt.id.vn") {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://ngoctientnt.vercel.app/")
                )
                context.startActivity(urlIntent)
            }
            ItemInfo(icon = R.drawable.facebook_icon, title = "ngoctien.TNT") {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/ngoctien.TNT/")
                )
                context.startActivity(urlIntent)
            }
            ItemInfo(icon = R.drawable.messenger_icon, title = "ngoctien.TNT") {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.m.me/ngoctien.TNT/")
                )
                context.startActivity(urlIntent)
            }
            ItemInfo(icon = R.drawable.telegram_icon, title = "ngoctienTNT") {
                val urlIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://t.me/ngoctienTNT")
                )
                context.startActivity(urlIntent)
            }
            ItemInfo(icon = R.drawable.gmail_icon, title = "ngoctienTNT.vn") {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, "ngoctienTNT.vn@gmail.com")
                intent.putExtra(Intent.EXTRA_SUBJECT, "Been Alone")
                context.startActivity(intent)
            }
        }
    }
}

@Composable
fun ItemInfo(icon: Int, title: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = icon),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = title,
                fontWeight = FontWeight(600),
                fontSize = 13.sp,
                color = Color(0xFF64748B)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    BeenAloneTheme {
        InfoScreen(rememberNavController())
    }
}