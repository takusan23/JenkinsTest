package io.github.takusan23.newradiosupporter.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.takusan23.newradiosupporter.R
import io.github.takusan23.newradiosupporter.tool.data.FinalNrType
import io.github.takusan23.newradiosupporter.tool.data.NrStandAloneType

/**
 * 回線状態を表示するやつ。展開時用
 *
 * @param nrStandAloneType 5G未接続時はnullでおｋ
 */
@Composable
fun SimNetWorkStatusExpanded(
    modifier: Modifier = Modifier,
    finalNRType: FinalNrType,
    nrStandAloneType: NrStandAloneType?,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less_24),
                contentDescription = null
            )
        }
        Icon(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = when (finalNRType) {
                FinalNrType.ANCHOR_BAND -> R.drawable.ic_android_anchor_lte_band
                FinalNrType.NR_LTE_FREQUENCY -> R.drawable.android_nr_lte_freq_nr
                FinalNrType.NR_SUB6 -> R.drawable.ic_android_nr_sub6
                FinalNrType.NR_MMW -> R.drawable.ic_android_nr_mmw
                FinalNrType.LTE -> R.drawable.ic_android_lte
                else -> R.drawable.ic_outline_info_24
            }),
            contentDescription = null
        )
        // メッセージ
        ConnectedStatusMessage(finalNRType = finalNRType)
        // 5Gの場合は NSA/SA どっちかを表示する
        if (nrStandAloneType != null && (finalNRType.isNr)) {
            ConnectedNrStandAloneMessage(nrStandAloneType)
        }
    }
}

/** もし5Gに接続している場合は、スタンドアローン、ノンスタンドアローンのどっちに接続しているかを表示する */
@Composable
private fun ConnectedNrStandAloneMessage(nrStandAloneType: NrStandAloneType) {
    val (icon, strId) = when (nrStandAloneType) {
        NrStandAloneType.STAND_ALONE -> R.drawable.android_5g_stand_alone to R.string.type_stand_alone_5g
        NrStandAloneType.NON_STAND_ALONE -> R.drawable.android_5g_non_stand_alone to R.string.type_non_stand_alone_5g
        NrStandAloneType.ERROR -> R.drawable.ic_outline_4g_mobiledata_24 to R.string.type_lte
    }
    MessageCard(
        cardColor = MaterialTheme.colorScheme.secondaryContainer,
        iconRes = icon,
        text = stringResource(id = strId)
    )
}

/** 接続中のネットワークを表示するやつ */
@Composable
private fun ConnectedStatusMessage(finalNRType: FinalNrType) {
    MessageCard(
        cardColor = animateColorAsState(targetValue = if (finalNRType.isNr) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.errorContainer).value,
        iconRes = if (finalNRType.isNr) R.drawable.ic_outline_5g_24 else R.drawable.ic_outline_error_outline_24,
        text = when (finalNRType) {
            FinalNrType.NR_MMW -> stringResource(id = R.string.type_nr_mmwave)
            FinalNrType.NR_SUB6 -> stringResource(id = R.string.type_nr_sub6)
            FinalNrType.NR_LTE_FREQUENCY -> stringResource(id = R.string.type_lte_freq_nr)
            FinalNrType.MAYBE_NR -> stringResource(id = R.string.type_maybe_nr)
            FinalNrType.ANCHOR_BAND -> stringResource(id = R.string.type_lte_anchor_band)
            FinalNrType.LTE -> stringResource(id = R.string.type_lte)
            else -> stringResource(id = R.string.loading)
        }
    )
}

/**
 * メッセージのカード
 *
 * @param cardColor カードの色
 * @param text メッセージ
 * @param iconRes アイコンのリソースID
 */
@Composable
private fun MessageCard(
    cardColor: Color,
    text: String,
    iconRes: Int,
) {
    Surface(
        modifier = Modifier.padding(10.dp),
        color = cardColor,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                painter = painterResource(id = iconRes),
                contentDescription = null
            )
            Text(text = text)
        }
    }
}