package com.xeniac.chillclub.feature_settings.presentation.components

import android.Manifest
import android.os.Build
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.xeniac.chillclub.core.presentation.common.states.PostNotificationPermissionState
import com.xeniac.chillclub.core.presentation.common.ui.components.PostNotificationPermissionDialog
import com.xeniac.chillclub.core.presentation.common.utils.findActivity
import com.xeniac.chillclub.feature_settings.presentation.SettingsAction

@Composable
fun PostNotificationPermission(
    state: PostNotificationPermissionState,
    onAction: (action: SettingsAction) -> Unit
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val context = LocalContext.current
        val activity = LocalActivity.current ?: context.findActivity()

        val postNotificationPermissionResultLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            onAction(
                SettingsAction.OnNotificationPermissionResult(
                    isGranted = isGranted,
                    isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                        /* activity = */ activity,
                        /* permission = */ Manifest.permission.POST_NOTIFICATIONS
                    )
                )
            )
        }

        PostNotificationPermissionDialog(
            isVisible = state.isNotificationPermissionDialogVisible,
            isPermanentlyDeclined = state.isNotificationPermissionPermanentlyDeclined,
            onConfirmClick = {
                postNotificationPermissionResultLauncher.launch(
                    input = Manifest.permission.POST_NOTIFICATIONS
                )
            },
            onDismiss = { onAction(SettingsAction.DismissNotificationPermissionDialog) }
        )
    }
}