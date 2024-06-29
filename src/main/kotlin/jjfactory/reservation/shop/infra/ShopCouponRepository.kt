package jjfactory.reservation.shop.infra

import jjfactory.reservation.shop.domain.coupon.ShopCoupon
import org.springframework.data.jpa.repository.JpaRepository

interface ShopCouponRepository: JpaRepository<ShopCoupon, Long> {

}
