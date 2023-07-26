package com.tnt.beenalone.presentation.welcome.page

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tnt.beenalone.R
import com.tnt.beenalone.models.User
import com.tnt.beenalone.presentation.edit_profile.ItemEditText
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun InputInfoPage(onNext: (user: User) -> Unit) {
    val dialogState = rememberMaterialDialogState()
    var name by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var datePicker: LocalDate by remember { mutableStateOf(LocalDate.now()) }
    var date: LocalDate by remember { mutableStateOf(LocalDate.now()) }
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            Log.d("uri", uri.toString())
            Log.d("path", uri?.path.toString())
        }

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok") {
                datePicker = date
            }
            negativeButton("Cancel") {
                date = datePicker
            }
        }
    ) {
        datepicker(
            title = "Ngày sinh",
            initialDate = datePicker,
            allowedDateValidator = { it <= LocalDate.now() && it >= LocalDate.of(1990, 1, 1) }
        ) { date = it }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.size(150.dp)) {
            if (imageUri == null) {
                Image(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(90.dp))
                        .clickable { launcher.launch("image/*") },
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(RoundedCornerShape(90.dp))
                        .clickable { launcher.launch("image/*") },
                    model = imageUri,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Icon(
                modifier = Modifier
                    .size(35.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.photographer),
                contentDescription = "",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ItemEditText("Tên", name, { text -> name = text })
        Spacer(modifier = Modifier.height(10.dp))
        ItemEditText("Ngày sinh", formatter.format(datePicker), {}) {
            dialogState.show()
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp),
            onClick = {
                onNext(
                    User(
                        name,
                        true,
                        formatter.format(datePicker),
                        imageUri.toString(),
                        LocalDate.now()
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(
                text = "Tiếp",
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}