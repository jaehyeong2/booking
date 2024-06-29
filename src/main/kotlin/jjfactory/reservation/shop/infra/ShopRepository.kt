package jjfactory.reservation.shop.infra

import jjfactory.reservation.shop.domain.Shop
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository: JpaRepository<Shop, Long> {
    fun existsByBizNum(bizNum: String): Boolean
}
