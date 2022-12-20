package com.example.providerB.ui.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

fun Context.checkNotificationPermission(

    permissionNotificationLauncher: ActivityResultLauncher<String>
) {
    if (ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            permissionNotificationLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
}
