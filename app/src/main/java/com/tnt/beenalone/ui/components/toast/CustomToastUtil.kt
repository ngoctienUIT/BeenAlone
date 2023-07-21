package com.tnt.beenalone.ui.components.toast

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tnt.beenalone.ui.components.toast.type.Info
import com.tnt.beenalone.ui.components.toast.type.Success
import com.tnt.beenalone.ui.components.toast.type.Warning
import com.tnt.beenalone.ui.components.toast.type.Error

object CustomToastUtil {
    @Composable
    fun ToastSuccess(
        message: String,
        duration: Int = Toast.LENGTH_LONG,
        padding: PaddingValues = PaddingValues(top = 20.dp),
        contentAlignment: Alignment = Alignment.TopCenter
    ) {
        val successToast = CustomToast(LocalContext.current)
        successToast.MakeTest(
            message = message,
            duration = duration,
            type = Success(),
            padding = padding,
            contentAlignment = contentAlignment
        )
        successToast.show()
    }

    @Composable
    fun ToastError(
        message: String,
        duration: Int = Toast.LENGTH_LONG,
        padding: PaddingValues = PaddingValues(top = 20.dp),
        contentAlignment: Alignment = Alignment.TopCenter
    ) {
        val errorToast = CustomToast(LocalContext.current)
        errorToast.MakeTest(
            message = message,
            duration = duration,
            type = Error(),
            padding = padding,
            contentAlignment = contentAlignment
        )
        errorToast.show()
    }

    @Composable
    fun ToastInfo(
        message: String,
        duration: Int = Toast.LENGTH_LONG,
        padding: PaddingValues = PaddingValues(top = 20.dp),
        contentAlignment: Alignment = Alignment.TopCenter
    ) {
        val infoToast = CustomToast(LocalContext.current)
        infoToast.MakeTest(
            message = message,
            duration = duration,
            type = Info(),
            padding = padding,
            contentAlignment = contentAlignment
        )
        infoToast.show()
    }

    @Composable
    fun ToastWarning(
        message: String,
        duration: Int = Toast.LENGTH_LONG,
        padding: PaddingValues = PaddingValues(top = 20.dp),
        contentAlignment: Alignment = Alignment.TopCenter
    ) {
        val warningToast = CustomToast(LocalContext.current)
        warningToast.MakeTest(
            message = message,
            duration = duration,
            type = Warning(),
            padding = padding,
            contentAlignment = contentAlignment
        )
        warningToast.show()
    }

    @Composable
    fun SetView(
        messageTxt: String,
        resourceIcon: Int,
        backgroundColor: Color,
        padding: PaddingValues,
        contentAlignment: Alignment
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = contentAlignment
        ) {
            Surface(
                modifier = Modifier.wrapContentSize(),
                color = Color.Transparent
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = backgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.padding(8.dp, 0.dp, 8.dp, 0.dp),
                        painter = painterResource(id = resourceIcon),
                        contentDescription = "",
                    )
                    Text(
                        text = messageTxt,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        style = TextStyle(color = Color.White),
                        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp)
                    )
                }
            }
        }
    }
}