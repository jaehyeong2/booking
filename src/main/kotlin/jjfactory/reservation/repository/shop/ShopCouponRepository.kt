package jjfactory.reservation.repository.shop

import jjfactory.reservation.domain.shop.coupon.ShopCoupon
import org.springframework.data.jpa.repository.JpaRepository

interface ShopCouponRepository: JpaRepository<ShopCoupon, Long> {

}
