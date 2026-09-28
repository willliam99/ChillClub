package com.xeniac.chillclub.core.domain.errors

sealed class StoreRequestNotificationPermissionDateError : Error() {
    data object SomethingWentWrong : StoreRequestNotificationPermissionDateError()
}