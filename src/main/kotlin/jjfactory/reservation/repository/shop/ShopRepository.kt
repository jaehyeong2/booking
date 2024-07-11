package jjfactory.reservation.repository.shop

import jjfactory.reservation.domain.shop.Shop
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository: JpaRepository<Shop, Long> {
    fun existsByBizNum(bizNum: String): Boolean
}
