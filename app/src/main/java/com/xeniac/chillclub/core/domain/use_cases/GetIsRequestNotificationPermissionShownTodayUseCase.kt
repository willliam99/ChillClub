package com.xeniac.chillclub.core.domain.use_cases

import com.xeniac.chillclub.core.domain.repositories.PermissionsDataStoreRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class GetIsRequestNotificationPermissionShownTodayUseCase @Inject constructor(
    private val repository: PermissionsDataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> = flow {
        return@flow emit(repository.isRequestNotificationPermissionShownToday())
    }
}