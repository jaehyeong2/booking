package jjfactory.reservation.shop.domain.coupon

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UserShopCouponTest {

    @Test
    fun `쿠폰 사용 성공`() {
        val shopCoupon = ShopCoupon(
            name = "name",
            shopId = 2L,
            useStartAt = LocalDateTime.now().minusDays(10),
            useEndAt = LocalDateTime.now().plusDays(10),
            qty = 5,
            validSecond = 2000
        )

        val coupon = UserShopCoupon(
            userId = 2L,
            coupon = shopCoupon,
            expiredAt = LocalDateTime.now().plusDays(10)
        )

        coupon.use()

        assertThat(coupon.usedAt).isNotNull
        assertThat(coupon.isUsed).isTrue
    }

    @Test
    fun `쿠폰 사용 기간 지나면 못씀`() {
        val shopCoupon = ShopCoupon(
            name = "name",
            shopId = 2L,
            useStartAt = LocalDateTime.now().minusDays(10),
            useEndAt = LocalDateTime.now().plusDays(10),
            qty = 5,
            validSecond = 2000
        )

        val coupon = UserShopCoupon(
            userId = 2L,
            coupon = shopCoupon,
            expiredAt = LocalDateTime.now().plusDays(10)
        )

        assertThatThrownBy {
            coupon.use()
        }.isInstanceOf(ExpiredCouponException::class.java)
    }

    @Test
    fun `쿠폰 재사용 불가`() {
        val shopCoupon = ShopCoupon(
            name = "name",
            shopId = 2L,
            useStartAt = LocalDateTime.now().minusDays(10),
            useEndAt = LocalDateTime.now().plusDays(10),
            qty = 5,
            validSecond = 2000
        )

        val coupon = UserShopCoupon(
            userId = 2L,
            coupon = shopCoupon,
            expiredAt = LocalDateTime.now().plusDays(10)
        )

        coupon.use()

        assertThatThrownBy {
            coupon.use()
        }.isInstanceOf(AlreadyUsedCouponException::class.java)
    }
}