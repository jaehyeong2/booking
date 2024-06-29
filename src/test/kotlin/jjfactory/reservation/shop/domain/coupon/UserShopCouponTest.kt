package jjfactory.reservation.shop.domain.coupon

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class UserShopCouponTest {

    @Test
    fun `쿠폰 사용 성공`() {
        val coupon = UserShopCoupon(
            userId = 2L,
            couponId = 3L,
            expiredAt = LocalDateTime.now().plusDays(10)
        )

        coupon.use()

        assertThat(coupon.usedAt).isNotNull
        assertThat(coupon.isUsed).isTrue
    }

    @Test
    fun `쿠폰 사용 기간 지나면 못씀`() {
        val coupon = UserShopCoupon(
            userId = 2L,
            couponId = 3L,
            expiredAt = LocalDateTime.now().minusDays(10)
        )

        assertThatThrownBy {
            coupon.use()
        }.isInstanceOf(ExpiredCouponException::class.java)
    }

    @Test
    fun `쿠폰 재사용 불가`() {
        val coupon = UserShopCoupon(
            userId = 2L,
            couponId = 3L,
            expiredAt = LocalDateTime.now().plusDays(10)
        )
        coupon.use()

        assertThatThrownBy {
            coupon.use()
        }.isInstanceOf(AlreadyUsedCouponException::class.java)
    }
}