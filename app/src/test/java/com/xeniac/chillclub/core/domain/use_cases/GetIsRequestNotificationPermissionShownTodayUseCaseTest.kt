package com.xeniac.chillclub.core.domain.use_cases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.xeniac.chillclub.MainCoroutineRule
import com.xeniac.chillclub.core.data.repositories.FakePermissionsDataStoreRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class GetIsRequestNotificationPermissionShownTodayUseCaseTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var fakePermissionsDataStoreRepositoryImpl: FakePermissionsDataStoreRepositoryImpl
    private lateinit var getIsRequestNotificationPermissionShownTodayUseCase: GetIsRequestNotificationPermissionShownTodayUseCase

    @Before
    fun setUp() {
        fakePermissionsDataStoreRepositoryImpl = FakePermissionsDataStoreRepositoryImpl()
        getIsRequestNotificationPermissionShownTodayUseCase =
            GetIsRequestNotificationPermissionShownTodayUseCase(
                repository = fakePermissionsDataStoreRepositoryImpl
            )
    }

    @Test
    fun getDefaultIsRequestNotificationPermissionShownToday_returnsFalse() = runTest {
        getIsRequestNotificationPermissionShownTodayUseCase().onEach { isShownToday ->
            assertThat(isShownToday).isFalse()
        }.launchIn(scope = this)
    }
}