package jjfactory.reservation.shop.domain.coupon

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class ShopCouponTest {

    @Test
    fun `쿠폰 발급 시작 성공`() {
        val initCoupon = ShopCoupon(
            shopId = 2L,
            qty = 2,
            name = "컷팅 할인 쿠폰",
            useStartAt = LocalDateTime.now().minusDays(1),
            useEndAt = LocalDateTime.now().plusDays(1),
            validSecond = 20000
        )

        initCoupon.startIssue()

        assertThat(initCoupon.issueStarted).isTrue
    }

    @Test
    fun `qty 감소 성공`() {
        val initCoupon = ShopCoupon(
            shopId = 2L,
            qty = 2,
            name = "컷팅 할인 쿠폰",
            useStartAt = LocalDateTime.now().minusDays(1),
            useEndAt = LocalDateTime.now().plusDays(1),
            validSecond = 20000
        )

        initCoupon.decreaseQty()

        assertThat(initCoupon.qty).isEqualTo(1)
    }

    @Test
    fun `재고 소진 후 qty감소하려고 하면 익셉션`() {
        val initCoupon = ShopCoupon(
            shopId = 2L,
            qty = 1,
            name = "컷팅 할인 쿠폰",
            useStartAt = LocalDateTime.now().minusDays(1),
            useEndAt = LocalDateTime.now().plusDays(1),
            validSecond = 20000
        )

        initCoupon.decreaseQty()

        assertThatThrownBy {
            initCoupon.decreaseQty()
        }.isInstanceOf(OutOfStockException::class.java)
    }
}