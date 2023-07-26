package com.tnt.beenalone.presentation.edit_profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.tnt.beenalone.R
import com.tnt.beenalone.ui.components.CustomAppBar
import com.tnt.beenalone.ui.components.toast.CustomToastUtil
import com.tnt.beenalone.ui.theme.BeenAloneTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel = hiltViewModel()
) {
    val dialogState = rememberMaterialDialogState()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            viewModel.imageUri = uri
            Log.d("uri", uri.toString())
            Log.d("path", uri?.path.toString())
        }

    if (viewModel.showToastSuccess) {
        CustomToastUtil.ToastSuccess("Lưu thành công")
        viewModel.showToastSuccess = false
    }

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok") {
                viewModel.datePicker = viewModel.date
            }
            negativeButton("Cancel") {
                viewModel.date = viewModel.datePicker
            }
        }
    ) {
        datepicker(
            title = "Ngày sinh",
            initialDate = viewModel.datePicker,
            allowedDateValidator = { it <= LocalDate.now() && it >= LocalDate.of(1990, 1, 1) }
        ) { viewModel.date = it }
    }

    Scaffold(
        topBar = {
            CustomAppBar(title = "Chỉnh sửa thông tin", navController = navController) {
                viewModel.saveProfile()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(top = it.calculateTopPadding() + 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.size(150.dp)) {
                if (viewModel.imageUri == null) {
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
                        model = viewModel.imageUri,
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
            ItemEditText("Tên", viewModel.name, { text -> viewModel.name = text })
            Spacer(modifier = Modifier.height(10.dp))
            ItemEditText("Ngày sinh", formatter.format(viewModel.datePicker), {}) {
                dialogState.show()
            }
            Spacer(modifier = Modifier.height(10.dp))
            ItemEditText("Giới tính", "Nam", {}) {

            }
            Spacer(modifier = Modifier.height(10.dp))
            ItemEditText("Facebook", "ngoctienTNT", {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditText(
    title: String,
    content: String,
    onValueChange: (String) -> Unit,
    onClick: (() -> Unit)? = null
) {
    val interactionSource = remember { MutableInteractionSource() }

    if (interactionSource.collectIsPressedAsState().value) onClick?.let { it() }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(text = title)
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = content,
            onValueChange = onValueChange,
            placeholder = { Text(text = title) },
            shape = RoundedCornerShape(8.dp),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
//            enabled = onClick == null,
            readOnly = onClick != null,
            interactionSource = interactionSource
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    BeenAloneTheme {
        EditProfileScreen(rememberNavController())
    }
}