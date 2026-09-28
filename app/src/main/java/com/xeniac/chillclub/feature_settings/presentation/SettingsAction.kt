package com.xeniac.chillclub.feature_settings.presentation

import com.xeniac.chillclub.core.domain.models.AppTheme

sealed interface SettingsAction {
    data class StoreCurrentAppTheme(val newAppTheme: AppTheme) : SettingsAction
    data class StorePlayInBackgroundSwitch(val isEnabled: Boolean) : SettingsAction

    data class OnNotificationPermissionResult(
        val isGranted: Boolean,
        val isPermanentlyDeclined: Boolean
    ) : SettingsAction

    data object DismissNotificationPermissionDialog : SettingsAction
}