package jjfactory.reservation.repository.shop;

import jjfactory.reservation.domain.shop.coupon.UserShopCoupon
import org.springframework.data.jpa.repository.JpaRepository

interface UserShopCouponRepository : JpaRepository<UserShopCoupon, Long> {
}