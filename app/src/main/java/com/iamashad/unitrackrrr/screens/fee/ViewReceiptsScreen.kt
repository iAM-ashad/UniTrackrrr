package com.iamashad.unitrackrrr.screens.fee

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.iamashad.unitrackrrr.R
import com.iamashad.unitrackrrr.components.AppTopBar
import com.iamashad.unitrackrrr.model.FeeReceipt

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ViewReceiptsScreen(
    navController: NavController,
    semester: Int,
    viewModel: ReceiptViewModel = hiltViewModel()
) {
    val receipts = viewModel.receipts.collectAsState().value.filter { feeReceipt->
        feeReceipt.semester == semester
    }
    val context = LocalContext.current

    val storagePermissionState = rememberPermissionState(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    if (storagePermissionState.permission.isEmpty()) {
        LaunchedEffect(Unit) {
            storagePermissionState.launchPermissionRequest()
        }
    }
    if (receipts.isEmpty()) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AppTopBar(
                isHome = false,
                navController = navController,
                title = ""
            )
            receipts.forEach { receipt ->
                ReceiptItem(receipt = receipt, context = context)
            }
        }
    }

}
@Composable
fun ReceiptItem(receipt: FeeReceipt, context: Context) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = "You have the following receipts: ",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp)
        )
        Card (
            modifier = Modifier
                .fillMaxWidth(.65f)
                .height(100.dp)
                .padding(10.dp),
            shape = RoundedCornerShape(corner = CornerSize(40.dp)),
            colors = CardDefaults.cardColors(
                containerColor = Color(32, 207, 231, 255)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            )
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Text(
                    text = "Semester ${receipt.semester} Receipt",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
                IconButton(onClick = {
                    downloadPdf(
                        context = context,
                        url = receipt.receiptUrl!!,
                        fileName = "Receipt"
                    )
                },
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth(0.8f)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.downloadpdf),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

fun downloadPdf(context: Context, url: String, fileName: String) {
    if (url.isEmpty()) {
        Toast.makeText(context, "Invalid download URL", Toast.LENGTH_SHORT).show()
        return
    }

    val request = DownloadManager.Request(Uri.parse(url)).apply {
        setTitle("Downloading PDF")
        setDescription("Downloading $fileName")

        setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "$fileName.pdf")

        setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)

        setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
    }

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)

    Toast.makeText(context, "Download started", Toast.LENGTH_SHORT).show()
}