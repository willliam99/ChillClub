package com.xeniac.chillclub.core.presentation.common.states

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostNotificationPermissionState(
    val isRequestNotificationPermissionShownToday: Boolean = false,
    val isNotificationPermissionPermanentlyDeclined: Boolean = false,
    val isNotificationPermissionDialogVisible: Boolean = false
) : Parcelable