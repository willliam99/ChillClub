package com.xeniac.chillclub.feature_settings.presentation.states

import android.os.Parcelable
import com.xeniac.chillclub.core.domain.models.AppTheme
import com.xeniac.chillclub.core.presentation.common.states.PostNotificationPermissionState
import kotlinx.parcelize.Parcelize

@Parcelize
data class SettingsState(
    val currentAppTheme: AppTheme? = null,
    val isPlayInBackgroundEnabled: Boolean? = null,
    val postNotificationPermissionState: PostNotificationPermissionState = PostNotificationPermissionState()
) : Parcelable