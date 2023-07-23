package com.tnt.beenalone.core

import com.tnt.beenalone.R
import com.tnt.beenalone.ui.theme.Bronze
import com.tnt.beenalone.ui.theme.Diamond
import com.tnt.beenalone.ui.theme.Gold
import com.tnt.beenalone.ui.theme.Master
import com.tnt.beenalone.ui.theme.Platinum
import com.tnt.beenalone.ui.theme.Silver
import com.tnt.beenalone.ui.theme.Veteran

object Constants {
    const val BEEN_ALONE_DATASTORE = "been_alone_tnt_datastore"
    private const val HOST_URL = "been-alone-server.vercel.app"
    const val BASE_URL_V1 = "https://$HOST_URL/api/v1/"
}

val rankList =
    listOf(
        mapOf("name" to "Đồng 3", "image" to R.drawable.bronze_rank, "color" to Bronze),
        mapOf("name" to "Đồng 2", "image" to R.drawable.bronze_rank, "color" to Bronze),
        mapOf("name" to "Đồng 1", "image" to R.drawable.bronze_rank, "color" to Bronze),
        mapOf("name" to "Bạc 3", "image" to R.drawable.silver_rank, "color" to Silver),
        mapOf("name" to "Bạc 2", "image" to R.drawable.silver_rank, "color" to Silver),
        mapOf("name" to "Bạc 1", "image" to R.drawable.silver_rank, "color" to Silver),
        mapOf("name" to "Vàng 4", "image" to R.drawable.gold_rank, "color" to Gold),
        mapOf("name" to "Vàng 3", "image" to R.drawable.gold_rank, "color" to Gold),
        mapOf("name" to "Vàng 2", "image" to R.drawable.gold_rank, "color" to Gold),
        mapOf("name" to "Vàng 1", "image" to R.drawable.gold_rank, "color" to Gold),
        mapOf("name" to "Bạch kim 5", "image" to R.drawable.platinum_rank, "color" to Platinum),
        mapOf("name" to "Bạch kim 4", "image" to R.drawable.platinum_rank, "color" to Platinum),
        mapOf("name" to "Bạch kim 3", "image" to R.drawable.platinum_rank, "color" to Platinum),
        mapOf("name" to "Bạch kim 2", "image" to R.drawable.platinum_rank, "color" to Platinum),
        mapOf("name" to "Bạch kim 1", "image" to R.drawable.platinum_rank, "color" to Platinum),
        mapOf("name" to "Kim Cương 5", "image" to R.drawable.diamond_rank, "color" to Diamond),
        mapOf("name" to "Kim Cương 4", "image" to R.drawable.diamond_rank, "color" to Diamond),
        mapOf("name" to "Kim Cương 3", "image" to R.drawable.diamond_rank, "color" to Diamond),
        mapOf("name" to "Kim Cương 2", "image" to R.drawable.diamond_rank, "color" to Diamond),
        mapOf("name" to "Kim Cương 1", "image" to R.drawable.diamond_rank, "color" to Diamond),
        mapOf("name" to "Tinh Anh 5", "image" to R.drawable.veteran_rank, "color" to Veteran),
        mapOf("name" to "Tinh Anh 4", "image" to R.drawable.veteran_rank, "color" to Veteran),
        mapOf("name" to "Tinh Anh 3", "image" to R.drawable.veteran_rank, "color" to Veteran),
        mapOf("name" to "Tinh Anh 2", "image" to R.drawable.veteran_rank, "color" to Veteran),
        mapOf("name" to "Tinh Anh 1", "image" to R.drawable.veteran_rank, "color" to Veteran),
        mapOf("name" to "Cao thủ", "image" to R.drawable.master_rank, "color" to Master),
        mapOf("name" to "Chiến tướng", "image" to R.drawable.legendary, "color" to Master),
        mapOf("name" to "Thách đấu", "image" to R.drawable.conquerer_rank, "color" to Master),
    )

val listDayOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

val listFeeling = listOf(
    R.drawable.icon_tired,
    R.drawable.icon_pleading_face,
    R.drawable.icon_smiling_face_hearts,
    R.drawable.icon_star_struck,
    R.drawable.icon_smiling_face_heart_eyes
)