package jjfactory.reservation.shop.infra;

import jjfactory.reservation.shop.domain.coupon.UserShopCoupon
import org.springframework.data.jpa.repository.JpaRepository

interface UserShopCouponRepository : JpaRepository<UserShopCoupon, Long> {
}