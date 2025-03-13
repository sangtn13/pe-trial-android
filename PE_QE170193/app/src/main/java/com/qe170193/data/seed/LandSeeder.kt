package com.qe170193.data.seed

import android.content.Context
import com.qe170193.data.db.LandDao
import com.qe170193.data.model.Land

object LandSeeder {
    fun seed(context: Context) {
        val landDao = LandDao(context)

        if (landDao.getAllLands().isNotEmpty()) return

        val lands = listOf(
            Land(name = "Đất biển Quy Nhơn", price = 2000.0, address = "Quy Nhơn, Bình Định", latitude = 13.7820, longitude = 109.2194, details = "Mặt tiền biển, gần khu du lịch."),
            Land(name = "Nhà phố Quy Nhơn", price = 3000.0, address = "Quy Nhơn, Bình Định", latitude = 13.7791, longitude = 109.2238, details = "Nhà phố trung tâm, tiện kinh doanh."),
            Land(name = "Căn hộ cao cấp Quy Nhơn", price = 2500.0, address = "Quy Nhơn, Bình Định", latitude = 13.7833, longitude = 109.2197, details = "View biển đẹp, full nội thất."),
            Land(name = "Shophouse Quy Nhơn", price = 5000.0, address = "Quy Nhơn, Bình Định", latitude = 13.7810, longitude = 109.2211, details = "Nằm trong khu đô thị sầm uất."),
            Land(name = "Resort ven biển Quy Nhơn", price = 15000.0, address = "Quy Nhơn, Bình Định", latitude = 13.7754, longitude = 109.2178, details = "Khu nghỉ dưỡng cao cấp, sinh lời nhanh."),
            Land(name = "Đất vườn Đà Lạt", price = 5000.0, address = "Đà Lạt, Lâm Đồng", latitude = 11.9404, longitude = 108.4583, details = "Đất rộng 500m2, gần trung tâm."),
            Land(name = "Nhà phố quận 1", price = 3000.0, address = "Quận 1, TP. Hồ Chí Minh", latitude = 10.7769, longitude = 106.7009, details = "Nhà mặt tiền đường lớn."),
            Land(name = "Biệt thự biển Nha Trang", price = 15000.0, address = "Nha Trang, Khánh Hòa", latitude = 12.2388, longitude = 109.1967, details = "View biển đẹp, khu resort cao cấp."),
            Land(name = "Căn hộ chung cư Hà Nội", price = 2000.0, address = "Cầu Giấy, Hà Nội", latitude = 21.0285, longitude = 105.8542, details = "Căn hộ 2 phòng ngủ, gần công viên."),
            Land(name = "Đất nền Bình Dương", price = 7000.0, address = "Dĩ An, Bình Dương", latitude = 10.9188, longitude = 106.7548, details = "Gần KCN, tiềm năng đầu tư."),
            Land(name = "Shophouse Vũng Tàu", price = 5000.0, address = "Vũng Tàu, Bà Rịa - Vũng Tàu", latitude = 10.3457, longitude = 107.0843, details = "Kinh doanh thuận lợi, gần biển."),
            Land(name = "Nhà liền kề Đà Nẵng", price = 2500.0, address = "Hải Châu, Đà Nẵng", latitude = 16.0544, longitude = 108.2022, details = "Gần cầu Rồng, thuận tiện di chuyển."),
            Land(name = "Trang trại Lâm Đồng", price = 3000.0, address = "Bảo Lộc, Lâm Đồng", latitude = 11.5473, longitude = 107.8071, details = "Trang trại rộng, khí hậu mát mẻ."),
            Land(name = "Homestay Sapa", price = 1800.0, address = "Sapa, Lào Cai", latitude = 22.3364, longitude = 103.8438, details = "Homestay view núi, hút khách du lịch."),
            Land(name = "Nhà cổ Hội An", price = 6000.0, address = "Hội An, Quảng Nam", latitude = 15.8770, longitude = 108.3308, details = "Nhà cổ, phong cách truyền thống.")
        )

        lands.forEach { landDao.insertLand(it) }
    }
}
