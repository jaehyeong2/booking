package jjfactory.reservation.shop.domain.coupon

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ShopCouponTest{

    @Test
    fun `쿠폰 발급 시작 성공`(){
        val initCoupon = ShopCoupon(
            shopId = 2L,
            qty = 2,
            name = "컷팅 할인 쿠폰",
            useStartAt = LocalDateTime.now().minusDays(1),
            useEndAt = LocalDateTime.now().plusDays(1),
            validSecond = 20000
        )

        initCoupon.startIssue()

        Assertions.assertThat(initCoupon.issueStarted).isTrue
    }
}