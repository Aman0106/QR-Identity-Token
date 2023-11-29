package com.example.qridentitytoken.feature_qrpage

import android.content.res.Configuration
import android.graphics.Bitmap
import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.qridentitytoken.feature_qrpage.ui_components.QRScreenAppBar
import com.example.qridentitytoken.ui.theme.QRIdentityTokenTheme
import com.example.qridentitytoken.ui.theme.spacing


@Composable
fun QRScreen(
    userItemName: String,
    navHostController: NavHostController,
    userUID: String
) {
    val context = LocalContext.current
    val selectedSize by remember { mutableStateOf(QrSizes().large) }
    val url = "https://qr-identity-token.web.app/?uid=$userUID&itemName=$userItemName"
    val bitmap = remember {
        QRUtils.generateQRCode(
            url
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        QRScreenAppBar(navHostController = navHostController)
        Spacer(modifier = Modifier.height(100.dp))
        QRCode(size = selectedSize, bitmap = bitmap)

        Spacer(modifier = Modifier.height(20.dp))

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

                        navHostController.popBackStack()
                    }catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            context,
                            "Unable to save Image",
                            Toast.LENGTH_SHORT).show()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.inverseSurface,
                contentColor = MaterialTheme.colorScheme.inverseOnSurface
            )
        ) {
            Text(
                text = "Save",
                fontSize = 32.sp
            )
        }
    }

}

@Composable
fun QRCode(size: Dp, bitmap: Bitmap?) {
    val primaryColor = MaterialTheme.colorScheme.primary
    if (bitmap != null) {
        Box(
            modifier = Modifier
                .size(size)
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "QR Code",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.spacing.large)
                    .clip(RoundedCornerShape(50.dp))
            )

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                val cornerRadius = 100f // Adjust the corner radius as needed
                val strokeWidth = 6f // Adjust the stroke width as needed

                drawRoundRect(
                    color = primaryColor, // Change the color as needed
                    size = this.size,
                    topLeft = Offset(0f, 0f),
                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                    style = Stroke(width = strokeWidth)
                )
            }
        }
    }
}


@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun QRScreenPreview() {
    QRIdentityTokenTheme {
        QRScreen(userItemName = "Pen", rememberNavController(), userUID = "dummy")
    }
}

data class QrSizes(
    val small: Dp = 150.dp,
    val medium: Dp = 200.dp,
    val large: Dp = 350.dp
)