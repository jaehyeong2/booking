package jjfactory.reservation.repository

import jjfactory.reservation.domain.coupon.Coupon
import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository: JpaRepository<Coupon, Long> {
}