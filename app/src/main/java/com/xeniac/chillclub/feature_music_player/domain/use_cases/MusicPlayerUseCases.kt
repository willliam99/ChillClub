package com.xeniac.chillclub.feature_music_player.domain.use_cases

import com.xeniac.chillclub.core.domain.use_cases.GetIsRequestNotificationPermissionShownTodayUseCase
import com.xeniac.chillclub.core.domain.use_cases.StoreRequestNotificationPermissionDateUseCase
import dagger.Lazy
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
data class MusicPlayerUseCases @Inject constructor(
    val getRadioStationsUseCase: Lazy<GetRadioStationsUseCase>,
    val getCurrentlyPlayingRadioStationUseCase: Lazy<GetCurrentlyPlayingRadioStationUseCase>,
    val observeMusicVolumeChangesUseCase: Lazy<ObserveMusicVolumeChangesUseCase>,
    val adjustMusicVolumeUseCase: Lazy<AdjustMusicVolumeUseCase>,
    val getIsPlayInBackgroundEnabledUseCase: Lazy<GetIsPlayInBackgroundEnabledUseCase>,
    val getCurrentlyPlayingRadioStationIdUseCase: Lazy<GetCurrentlyPlayingRadioStationIdUseCase>,
    val storeCurrentlyPlayingRadioStationIdUseCase: Lazy<StoreCurrentlyPlayingRadioStationIdUseCase>,
    val getIsRequestNotificationPermissionShownTodayUseCase: Lazy<GetIsRequestNotificationPermissionShownTodayUseCase>,
    val storeRequestNotificationPermissionDateUseCase: Lazy<StoreRequestNotificationPermissionDateUseCase>
)