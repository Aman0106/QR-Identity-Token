package com.example.qridentitytoken.feature_home

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.io.File
import java.io.OutputStream
import java.util.Objects

object QRUtils {
    fun generateQRCode(data: String, size: Int = 300): Bitmap? {
        var bmp: Bitmap? = null
        try {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(
                data,
                BarcodeFormat.QR_CODE,
                size,
                size
            )
            bmp = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        return bmp
    }
    @RequiresApi(Build.VERSION_CODES.Q)
    fun saveQRCodeAsPDF(qrCode: Bitmap, fileName: String, context: Context) {

        val fOStream: OutputStream

        try {
            val contentResolver = context.contentResolver
            val contentValues = ContentValues()
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, "$fileName.jpg")
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")

            val downloadUri = contentResolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            fOStream = contentResolver.openOutputStream(Objects.requireNonNull(downloadUri)!!)!!

            qrCode.compress(Bitmap.CompressFormat.JPEG, 100, fOStream)

            Objects.requireNonNull(fOStream)

        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

