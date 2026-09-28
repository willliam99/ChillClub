package com.xeniac.chillclub.core.data.repositories

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.xeniac.chillclub.core.domain.models.RequestNotificationPermissionDate
import com.xeniac.chillclub.core.domain.repositories.PermissionsDataStoreRepository
import com.xeniac.chillclub.core.domain.utils.PermissionHelper.isRequestShownToday
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import javax.inject.Inject
import kotlin.time.Clock

class FakePermissionsDataStoreRepositoryImpl @Inject constructor(
) : PermissionsDataStoreRepository {

    var requestNotificationPermissionDate = SnapshotStateList<RequestNotificationPermissionDate?>(
    ).apply {
        add(null)
    }

    override suspend fun isRequestNotificationPermissionShownToday(): Boolean {
        val isShown = requestNotificationPermissionDate.first()?.isRequestShownToday() ?: false
        return isShown
    }

    override suspend fun storeRequestNotificationPermissionDate(
        dateTimeFormat: DateTimeFormat<DateTimeComponents>
    ) {
        val shownDate = Clock.System.now()
        requestNotificationPermissionDate.apply {
            clear()
            add(shownDate.format(dateTimeFormat))
        }
    }
}