package com.xeniac.chillclub.core.presentation.common.utils

import com.xeniac.chillclub.R
import com.xeniac.chillclub.core.domain.errors.StoreRequestNotificationPermissionDateError

fun StoreRequestNotificationPermissionDateError.asUiText(): UiText = when (this) {
    StoreRequestNotificationPermissionDateError.SomethingWentWrong -> UiText.StringResource(R.string.error_something_went_wrong)
}