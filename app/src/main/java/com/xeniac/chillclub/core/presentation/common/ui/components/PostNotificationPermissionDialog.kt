package com.xeniac.chillclub.core.presentation.common.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.xeniac.chillclub.R
import com.xeniac.chillclub.core.presentation.common.utils.permission.PostNotificationsPermissionHelper

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PostNotificationPermissionDialog(
    isVisible: Boolean,
    isPermanentlyDeclined: Boolean,
    modifier: Modifier = Modifier,
    onConfirmClick: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isVisible) {
        PermissionDialog(
            icon = painterResource(id = R.drawable.ic_core_dialog_post_notification),
            permissionHelper = PostNotificationsPermissionHelper(),
            isPermanentlyDeclined = isPermanentlyDeclined,
            onConfirmClick = onConfirmClick,
            onDismiss = onDismiss,
            modifier = modifier
        )
    }
}