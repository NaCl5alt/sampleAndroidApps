package com.example.sampleandroidapps.feature.settings.section

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity

@Composable
fun LicensesItemSection(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier.clickable {
            context.startActivity(Intent(context, OssLicensesMenuActivity::class.java))
        }
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Text(
                modifier = Modifier.Companion.padding(start = 8.dp),
                text = "Licenses",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.Companion.weight(1f))

            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = "Navigation to licenses screen"
            )
        }

        HorizontalDivider()
    }
}

@Preview
@Composable
private fun PreviewLicensesColumn() {
    SampleAndroidAppsTheme {
        Surface {
            LicensesItemSection()
        }
    }
}
