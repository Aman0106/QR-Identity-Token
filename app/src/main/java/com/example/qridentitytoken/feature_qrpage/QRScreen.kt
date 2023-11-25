package com.example.qridentitytoken.feature_qrpage

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qridentitytoken.feature_home.QRUtils


@Composable
fun QRScreen(
    url: String,
    userItemName: String,
) {
    val context = LocalContext.current
    var selectedSize by remember { mutableStateOf(QrSizes().medium) }
    val bitmap = remember { QRUtils.generateQRCode(url) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        QRCode(size = selectedSize, bitmap = bitmap)
        SizeButtons(onSizeSelected = { newSize ->
            selectedSize = newSize
        })

        Button(
            onClick = {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    try{
                        QRUtils.saveQRCodeAsPDF(
                            qrCode = bitmap!!,
                            fileName = "$userItemName QRCode",
                            context = context)

                        Toast.makeText(
                            context,
                            "Image Saved in Downloads",
                            Toast.LENGTH_SHORT).show()
                    }catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            context,
                            "Unable to save Image",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
            Text(
                text = "Save",
                fontSize = 32.sp
                )
        }
    }
}

@Composable
fun QRCode(size: Dp, bitmap: Bitmap?) {

    if (bitmap != null) {
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "QR Code",
            modifier = Modifier
                .size(size)
                .fillMaxWidth()
                .aspectRatio(1f)
        )
    }
}


@Composable
fun SizeButtons(onSizeSelected: (Dp) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        SizeButton(
            onClick = { onSizeSelected(QrSizes().small) },
            txt = "Small",
            modifier = Modifier.weight(1f)
        )
        SizeButton(
            onClick = { onSizeSelected(QrSizes().medium) },
            txt = "Medium",
            modifier = Modifier.weight(1f)
        )
        SizeButton(
            onClick = { onSizeSelected(QrSizes().large) },
            txt = "Large",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun SizeButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    txt: String,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(8.dp),

        ) {
        Text(
            text = txt,
        )
    }
}

@Composable
fun showSnackbar(context: Context, message: String) {
    // You can implement your own Snackbar or use a library like Accompanist Snackbar
    // For simplicity, here's a basic Toast
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Preview(showSystemUi = true)
@Composable
fun QRScreenPreview() {
    QRScreen(url = "Aman", userItemName = "Pen")
}

data class QrSizes(
    val small: Dp = 150.dp,
    val medium: Dp = 200.dp,
    val large: Dp = 350.dp
)