package io.github.takusan23.newradiosupporter.tool

import io.github.takusan23.newradiosupporter.tool.data.BandDictionaryData

/** EARFCN(4G/LTE)と、NR-ARFCN(5G/NR)からバンドを出す */
object BandDictionaryTool {

    /** 最小の Sub-6 5G 新周波数帯 ( MHz )。転用5Gは含まれない */
    private const val SUB6_MIN_FREQUENCY_WITHOUT_LTE_FREQUENCY_MHZ = 3600

    /**
     * バンドとEARFCN（最小値と最大値）の相対表。LTE（4G）版
     *
     * 3GPP TS 36.104 参照
     * https://portal.3gpp.org/desktopmodules/Specifications/SpecificationDetails.aspx?specificationId=2412
     */
    private val bandLteList = listOf(
        BandDictionaryData.Lte("1", 0, 599, 2110f),
        BandDictionaryData.Lte("2", 600, 1199, 1930f),
        BandDictionaryData.Lte("3", 1200, 1949, 1805f),
        BandDictionaryData.Lte("4", 1950, 2399, 2110f),
        BandDictionaryData.Lte("5", 2400, 2649, 869f),
        BandDictionaryData.Lte("6", 2650, 2749, 875f),
        BandDictionaryData.Lte("7", 2750, 3449, 2620f),
        BandDictionaryData.Lte("8", 3450, 3799, 925f),
        BandDictionaryData.Lte("9", 3800, 4149, 1844.9f),
        BandDictionaryData.Lte("10", 4150, 4749, 2110f),
        BandDictionaryData.Lte("11", 4750, 4949, 1475.9f),
        BandDictionaryData.Lte("12", 5010, 5179, 729f),
        BandDictionaryData.Lte("13", 5180, 5279, 746f),
        BandDictionaryData.Lte("14", 5280, 5379, 758f),
        BandDictionaryData.Lte("17", 5730, 5849, 734f),
        BandDictionaryData.Lte("18", 5850, 5999, 860f),
        BandDictionaryData.Lte("19", 6000, 6149, 875f),
        BandDictionaryData.Lte("20", 6150, 6449, 791f),
        BandDictionaryData.Lte("21", 6450, 6599, 1495.9f),
        BandDictionaryData.Lte("22", 6600, 7399, 3510f),
        BandDictionaryData.Lte("23", 7500, 7699, 2180f),
        BandDictionaryData.Lte("24", 7700, 8039, 1525f),
        BandDictionaryData.Lte("25", 8040, 8689, 1930f),
        BandDictionaryData.Lte("26", 8690, 9039, 859f),
        BandDictionaryData.Lte("27", 9040, 9209, 852f),
        BandDictionaryData.Lte("28", 9210, 9659, 758f),
        BandDictionaryData.Lte("29", 9660, 9769, 717f),
        BandDictionaryData.Lte("30", 9770, 9869, 2350f),
        BandDictionaryData.Lte("31", 9870, 9919, 462.5f),
        BandDictionaryData.Lte("32", 9920, 10359, 1452f),
        BandDictionaryData.Lte("33", 36000, 36199, 1900f),
        BandDictionaryData.Lte("34", 36200, 36349, 2010f),
        BandDictionaryData.Lte("35", 36350, 36949, 1850f),
        BandDictionaryData.Lte("36", 36950, 37549, 1930f),
        BandDictionaryData.Lte("37", 37550, 37749, 1910f),
        BandDictionaryData.Lte("38", 37750, 38249, 2570f),
        BandDictionaryData.Lte("39", 38250, 38649, 1880f),
        BandDictionaryData.Lte("40", 38650, 39649, 2300f),
        BandDictionaryData.Lte("41", 39650, 41589, 2496f),
        BandDictionaryData.Lte("42", 41590, 43589, 3400f),
        BandDictionaryData.Lte("43", 43590, 45589, 3600f),
        BandDictionaryData.Lte("44", 45590, 46589, 703f),
        BandDictionaryData.Lte("45", 46590, 46789, 1447f),
        BandDictionaryData.Lte("46", 46790, 54539, 5150f),
        BandDictionaryData.Lte("47", 54540, 55239, 5855f),
        BandDictionaryData.Lte("48", 55240, 56739, 3550f),
        BandDictionaryData.Lte("49", 56740, 58239, 3550f),
        BandDictionaryData.Lte("50", 58240, 59089, 1432f),
        BandDictionaryData.Lte("51", 59090, 59139, 1427f),
        BandDictionaryData.Lte("52", 59140, 60139, 3300f),
        BandDictionaryData.Lte("53", 60140, 60254, 2483.5f),
        BandDictionaryData.Lte("54", 60255, 60304, 1670f),
        BandDictionaryData.Lte("65", 65536, 66435, 2110f),
        BandDictionaryData.Lte("66", 66436, 67335, 2110f),
        BandDictionaryData.Lte("67", 67336, 67535, 738f),
        BandDictionaryData.Lte("68", 67536, 67835, 753f),
        BandDictionaryData.Lte("69", 67836, 68335, 2570f),
        BandDictionaryData.Lte("70", 68336, 68585, 1995f),
        BandDictionaryData.Lte("71", 68586, 68935, 617f),
        BandDictionaryData.Lte("72", 68936, 68985, 461f),
        BandDictionaryData.Lte("73", 68986, 69035, 460f),
        BandDictionaryData.Lte("74", 69036, 69465, 1475f),
        BandDictionaryData.Lte("75", 69466, 70315, 1432f),
        BandDictionaryData.Lte("76", 70316, 70365, 1427f),
        BandDictionaryData.Lte("85", 70366, 70545, 728f),
        BandDictionaryData.Lte("87", 70546, 70595, 420f),
        BandDictionaryData.Lte("88", 70596, 70645, 422f),
        BandDictionaryData.Lte("103", 70646, 70655, 757f),
    )

    /**
     * バンドと NR-ARFCN の相対表。NR（5G）版
     *
     * FR1（Sub-6 まで）
     * 3GPP TS 38.101-1 参照
     * https://portal.3gpp.org/desktopmodules/Specifications/SpecificationDetails.aspx?specificationId=3283
     *
     * FR2（ミリ波）
     * 3GPP TS 38.101-2 参照
     * https://portal.3gpp.org/desktopmodules/Specifications/SpecificationDetails.aspx?specificationId=3284
     *
     * AOSP の実装
     * https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/telephony/java/android/telephony/AccessNetworkUtils.java
     */
    private val bandNrList = listOf(
        BandDictionaryData.Nr("n1", 422000, 434000, false),
        BandDictionaryData.Nr("n2", 386000, 398000, false),
        BandDictionaryData.Nr("n3", 361000, 376000, false),
        BandDictionaryData.Nr("n5", 173800, 178800, false),
        BandDictionaryData.Nr("n7", 524000, 538000, false),
        BandDictionaryData.Nr("n8", 185000, 192000, false),
        BandDictionaryData.Nr("n12", 145800, 149200, false),
        BandDictionaryData.Nr("n13", 149200, 151200, false),
        BandDictionaryData.Nr("n14", 151600, 153600, false),
        BandDictionaryData.Nr("n18", 172000, 175000, false),
        BandDictionaryData.Nr("n20", 158200, 164200, false),
        BandDictionaryData.Nr("n24", 305000, 311800, false),
        BandDictionaryData.Nr("n25", 386000, 399000, false),
        BandDictionaryData.Nr("n26", 171800, 178800, false),
        BandDictionaryData.Nr("n28", 151600, 160600, false),
        BandDictionaryData.Nr("n29", 143400, 145600, false),
        BandDictionaryData.Nr("n30", 470000, 472000, false),
        BandDictionaryData.Nr("n34", 402000, 405000, false),
        BandDictionaryData.Nr("n38", 514000, 524000, false),
        BandDictionaryData.Nr("n39", 376000, 384000, false),
        BandDictionaryData.Nr("n40", 460000, 480000, false),
        BandDictionaryData.Nr("n41", 499200, 537999, false),
        BandDictionaryData.Nr("n46", 743334, 795000, false),
        BandDictionaryData.Nr("n47", 790334, 795000, false),
        BandDictionaryData.Nr("n48", 636667, 646666, false),
        BandDictionaryData.Nr("n50", 286400, 303400, false),
        BandDictionaryData.Nr("n51", 285400, 286400, false),
        BandDictionaryData.Nr("n53", 496700, 499000, false),
        BandDictionaryData.Nr("n54", 334000, 335000, false),
        BandDictionaryData.Nr("n65", 422000, 440000, false),
        BandDictionaryData.Nr("n66", 422000, 440000, false),
        BandDictionaryData.Nr("n67", 147600, 151600, false),
        BandDictionaryData.Nr("n70", 399000, 404000, false),
        BandDictionaryData.Nr("n71", 123400, 130400, false),
        BandDictionaryData.Nr("n74", 295000, 303600, false),
        BandDictionaryData.Nr("n75", 286400, 303400, false),
        BandDictionaryData.Nr("n76", 285400, 286400, false),
        BandDictionaryData.Nr("n77", 620000, 680000, false),
        BandDictionaryData.Nr("n78", 620000, 653333, false),
        BandDictionaryData.Nr("n79", 693334, 733333, false),
        BandDictionaryData.Nr("n85", 145600, 149200, false),
        BandDictionaryData.Nr("n90", 499200, 538000, false),
        BandDictionaryData.Nr("n91", 285400, 286400, false),
        BandDictionaryData.Nr("n92", 286400, 303400, false),
        BandDictionaryData.Nr("n93", 285400, 286400, false),
        BandDictionaryData.Nr("n94", 286400, 303400, false),
        BandDictionaryData.Nr("n96", 795000, 875000, false),
        BandDictionaryData.Nr("n100", 183880, 185000, false),
        BandDictionaryData.Nr("n101", 380000, 382000, false),
        BandDictionaryData.Nr("n102", 795000, 828333, false),
        BandDictionaryData.Nr("n104", 828334, 875000, false),
        BandDictionaryData.Nr("n105", 122400, 130400, false),
        // 5G ミリ波
        BandDictionaryData.Nr("n257", 2054166, 2104165, true),
        BandDictionaryData.Nr("n258", 2016667, 2070832, true),
        BandDictionaryData.Nr("n259", 2270833, 2337499, true),
        BandDictionaryData.Nr("n260", 2229166, 2279165, true),
        BandDictionaryData.Nr("n261", 2070833, 2084999, true),
        BandDictionaryData.Nr("n262", 2399166, 2415832, true),
        // n263 もあるがなんか難しそうなのと、多分どこも使ってないので
    )

    /**
     * EARFCNからバンドを出す。LTE版
     *
     * @return 1850 なら band 3
     */
    fun toLteBand(earfcn: Int): String {
        return bandLteList.firstOrNull { bandDictionaryData ->
            // 範囲内にあれば
            earfcn in (bandDictionaryData.dlMin..bandDictionaryData.dlMax)
        }?.bandNumber.toString()
    }

    /**
     * NR-ARFCN からバンドを出す。5G版。NRは「New Radio」らしい
     *
     * @return n79 とか返ってくると思う
     */
    @Deprecated("toNrBandList を使う")
    fun toNrBand(nrarfcn: Int): String {
        return bandNrList.firstOrNull { bandDictionaryData ->
            // 範囲内にあれば
            nrarfcn in (bandDictionaryData.dlMin..bandDictionaryData.dlMax)
        }?.bandNumber.toString()
    }

    /**
     * NR-ARFCN からバンドを出す。5G版。NRは「New Radio」らしい
     * NR-ARFCN が複数のバンドに一致する場合はこちらを使い、
     * 日本の通信キャリアが使っているバンドを優先的に返すと良さそう
     *
     * @see [tryFixNrBand]
     * @param nrarfcn NR-ARFCN
     * @return ["n79"] みたいに返ってくるはず
     */
    fun toNrBandList(nrarfcn: Int): List<String> {
        return bandNrList.filter { bandDictionaryData ->
            // 範囲内にあれば
            nrarfcn in (bandDictionaryData.dlMin..bandDictionaryData.dlMax)
        }.map { it.bandNumber }
    }

    /**
     * NRARFCNがミリ波かどうか
     *
     * @return ミリ波ならtrue
     * */
    fun isMmWave(nrarfcn: Int): Boolean {
        return bandNrList.firstOrNull { bandDictionaryData ->
            // 範囲内にあれば
            nrarfcn in (bandDictionaryData.dlMin..bandDictionaryData.dlMax)
        }?.isMmWave == true
    }

    /**
     * EARFCN (4G)から周波数を求める。
     * 計算式は「3GPP TS 36.104 5.7.3」を参照してください
     *
     * @param earfcn EARFCN
     * @return 周波数 (ダウンリンク)。単位は MHz
     */
    fun toLteFrequencyMhz(earfcn: Int): Float {
        // 無いと思うけど無いなら return
        val bandData = bandLteList.firstOrNull { bandDictionaryData -> earfcn in (bandDictionaryData.dlMin..bandDictionaryData.dlMax) } ?: return -1f
        // NOffs-DL は EARFCN の範囲の最小値と同じなのでそれを使う
        val NOffsDL = bandData.dlMin
        // NR は周波数で値が違うが、LTE はバンドによって値が異なる
        val FDLLow = bandData.fDlLow
        // FDL = FDLLow + 0.1(NDL – NOffsDL) の計算をする
        val frequencyMHz = FDLLow + ((0.1f * earfcn) - (0.1 * NOffsDL))
        // 小数点第二位までにする
        return "%.2f".format(frequencyMHz).toFloat()
    }

    /**
     * NR-ARFCN (5G) から周波数を求める。
     * 計算式は「3GPP TS 38.104 5.4.2.1」を参照してください。
     *
     * @param nrarfcn NR-ARFCN
     * @return 周波数 (ダウンリンク)。単位は MHz 。3600とか。
     */
    fun toNrFrequencyMhz(nrarfcn: Int): Float {
        // 計算に必要な、 FREF-Offs / FGlobal / NREF-Offs を NR-ARFCN から出す
        // 資料では FGlobal は kHz だが、 MHz に合わせるため変換している
        val (FGlobal, FREFOffs, NREFOffs) = when (nrarfcn) {
            // 3 GHz 以下
            in 0..599999 -> Triple(0.005f, 0f, 0)
            // 3 GHz から 24.25 GHz
            in 600000..2016666 -> Triple(0.015f, 3000f, 600000)
            // 24.25 GHz 以上
            in 2016667..3279165 -> Triple(0.060f, 24250.08f, 2016667)
            // ありえないので適当にreturn
            else -> return -1f
        }
        // FREFOffs + FGlobal( NR-ARFCN - NREFOffs ) の計算をする
        val frequencyMHz = FREFOffs + ((FGlobal * nrarfcn) - (FGlobal * NREFOffs))
        // 小数点第二位までにする
        return "%.2f".format(frequencyMHz).toFloat()
    }

    /**
     * 転用5G / なんちゃって5G / NR化 かどうかを判定する。
     * 具体的には、NRARFCNから周波数を出して、周波数が 3.6GHz [SUB6_MIN_FREQUENCY_WITHOUT_LTE_FREQUENCY_MHZ] 未満の場合は転用5Gの判定を行う。
     *
     * @return 転用5Gかどうか
     */
    fun isLteFrequency(nrarfcn: Int): Boolean = toNrFrequencyMhz(nrarfcn) < SUB6_MIN_FREQUENCY_WITHOUT_LTE_FREQUENCY_MHZ

    /**
     * 5G バンドの修正を試みる
     * 詳しくは→ [CarrierNrBandDictionary]
     *
     * @param mcc MCC
     * @param mnc MNC
     * @param nrarfcn NR-ARFCN
     * @param bandNumber 修正前のバンド、[android.telephony.CellIdentityNr.getBands]や[toNrBand]の返り値
     * @return 修正できた場合はバンド、出来なかった場合は[bandNumber]をそのまま返す
     */
    fun tryFixNrBand(mcc: String, mnc: String, nrarfcn: Int, bandNumber: String): String {
        // あらかじめ用意した、通信キャリアの提供している 5G バンドを取得する
        // 用意していないキャリアの場合はそのまま返す
        val provideNrBandNumberList = CarrierNrBandDictionary
            .findProvideNrBandNumberList(mcc, mnc) ?: return bandNumber

        // 修正を試みる
        // 提供している 5G バンドに含まれている場合はそのまま返す
        if (provideNrBandNumberList.any { it == bandNumber }) {
            return bandNumber
        }

        // 提供しているバンドと引数のバンドが違った場合
        // NR-ARFCN を使い再度バンドを探し、提供している 5G バンドを返す
        val findBandFromProvideCarrierNrBand = toNrBandList(nrarfcn)
            .firstOrNull { resolveBandName -> provideNrBandNumberList.any { it == resolveBandName } }
        if (findBandFromProvideCarrierNrBand != null) {
            return findBandFromProvideCarrierNrBand
        }

        // それでもうまく行かなかった場合は、修正できないため元のバンドを返す
        return bandNumber
    }
}