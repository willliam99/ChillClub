package com.xeniac.chillclub.feature_settings.presentation.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale
import com.xeniac.chillclub.R
import com.xeniac.chillclub.core.domain.models.AppTheme
import com.xeniac.chillclub.core.presentation.common.utils.findActivity
import com.xeniac.chillclub.feature_settings.presentation.SettingsAction
import com.xeniac.chillclub.feature_settings.presentation.states.SettingsState
import com.xeniac.chillclub.feature_settings.presentation.utils.TestTags

@SuppressLint("InlinedApi")
@Composable
fun GeneralSettings(
    state: SettingsState,
    modifier: Modifier = Modifier,
    background: Color = MaterialTheme.colorScheme.surface,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = 20.dp,
        vertical = 16.dp
    ),
    title: String = stringResource(id = R.string.settings_general_title).uppercase(),
    titleFontSize: TextUnit = 20.sp,
    titleLineHeight: TextUnit = 20.sp,
    titleFontWeight: FontWeight = FontWeight.Normal,
    onAction: (action: SettingsAction) -> Unit
) {
    val context = LocalContext.current
    val activity = LocalActivity.current ?: context.findActivity()

    var isPostNotificationsPermissionGranted by remember {
        mutableStateOf(
            when (
                ActivityCompat.checkSelfPermission(
                    /* context = */ context,
                    /* permission = */ Manifest.permission.POST_NOTIFICATIONS
                )
            ) {
                PackageManager.PERMISSION_GRANTED -> true
                PackageManager.PERMISSION_DENIED -> false
                else -> false
            }
        )
    }

    val postNotificationPermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        isPostNotificationsPermissionGranted = isGranted

        onAction(
            SettingsAction.OnNotificationPermissionResult(
                isGranted = isGranted,
                isPermanentlyDeclined = !shouldShowRequestPermissionRationale(
                    /* activity = */ activity,
                    /* permission = */ Manifest.permission.POST_NOTIFICATIONS
                )
            )
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(background)
            .padding(contentPadding)
    ) {
        Text(
            text = title,
            fontSize = titleFontSize,
            lineHeight = titleLineHeight,
            fontWeight = titleFontWeight,
            modifier = Modifier.fillMaxWidth()
        )

        SettingsSwitchRow(
            icon = painterResource(id = R.drawable.ic_settings_theme),
            title = stringResource(id = R.string.settings_general_theme_title),
            isChecked = when (state.currentAppTheme) {
                AppTheme.Dark -> true
                AppTheme.Light -> false
                null -> null
            },
            testTag = TestTags.SWITCH_THEME,
            onCheckedChange = { isChecked ->
                when (isChecked) {
                    true -> onAction(SettingsAction.StoreCurrentAppTheme(AppTheme.Dark))
                    false -> onAction(SettingsAction.StoreCurrentAppTheme(AppTheme.Light))
                }
            }
        )

        SettingsSwitchRow(
            isEnabled = isPostNotificationsPermissionGranted,
            icon = painterResource(id = R.drawable.ic_settings_background_player),
            title = stringResource(id = R.string.settings_general_background_player_title),
            description = stringResource(id = R.string.settings_general_background_play_description),
            isChecked = isPostNotificationsPermissionGranted && state.isPlayInBackgroundEnabled == true,
            testTag = TestTags.SWITCH_BACKGROUND_PLAYER,
            onCheckedChange = { isChecked ->
                onAction(SettingsAction.StorePlayInBackgroundSwitch(isChecked))
            },
            onRowClick = when {
                isPostNotificationsPermissionGranted -> null
                else -> {
                    {
                        postNotificationPermissionResultLauncher.launch(
                            input = Manifest.permission.POST_NOTIFICATIONS
                        )
                    }
                }
            }
        )
    }
}