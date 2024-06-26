package com.example.trbofficerandroid.presentation.ui.screen.more

import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.trbofficerandroid.domain.model.AppTheme
import com.example.trbofficerandroid.presentation.theme.AppTheme
import com.example.trbofficerandroid.presentation.ui.screen.signin.openCustomTab
import org.koin.androidx.compose.koinViewModel

@Composable
fun MoreScreen() {
    val viewModel: MoreViewModel = koinViewModel()
    val theme by viewModel.theme.collectAsState(null)

    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.link.collect {
            val customIntent = CustomTabsIntent.Builder()
                .setDefaultColorSchemeParams(
                    CustomTabColorSchemeParams.Builder()
                        .setToolbarColor(0x006B5D)
                        .build()
                )
                .setColorSchemeParams(
                    CustomTabsIntent.COLOR_SCHEME_DARK,
                    CustomTabColorSchemeParams.Builder()
                        .setToolbarColor(0x006B5D)
                        .build()
                )
            openCustomTab(
                context,
                customIntent.build(),
                Uri.parse(it)
            )
        }
    }

    MoreScreenContent(
        // Данные
        photoUrl = viewModel.userPhoto.collectAsState().value,
        theme = theme,

        // События
        onUpdateThemeClick = remember { { viewModel.updateAppTheme(it) } },
        onLogoutClick = remember { { viewModel.logout() } }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoreScreenContent(
    // Данные
    photoUrl: String? = null,
    theme: AppTheme? = null,

    // События
    onUpdateThemeClick: (AppTheme) -> Unit = {},
    onLogoutClick: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Spacer(Modifier.weight(1f))
        AsyncImage(
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            model = photoUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )
        Spacer(Modifier.weight(2f))

        // AppTheme
        Spacer(Modifier.height(16.dp))
        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            SegmentedButton(
                selected = theme != AppTheme.DARK && theme != null,
                onClick = { onUpdateThemeClick(AppTheme.LIGHT) },
                shape = SegmentedButtonDefaults.itemShape(index = 0, count = 2)
            ) {
                Text(text = "Светлая")
            }
            SegmentedButton(
                selected = theme == AppTheme.DARK,
                onClick = { onUpdateThemeClick(AppTheme.DARK) },
                shape = SegmentedButtonDefaults.itemShape(index = 1, count = 2)
            ) {
                Text(text = "Темная")
            }
        }

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onLogoutClick,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(text = "Выйти")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        Surface {
            MoreScreenContent()
        }
    }
}