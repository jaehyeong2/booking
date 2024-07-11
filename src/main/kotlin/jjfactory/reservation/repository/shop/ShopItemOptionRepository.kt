package jjfactory.reservation.repository.shop

import jjfactory.reservation.domain.shop.item.ShopItemOptions
import org.springframework.data.jpa.repository.JpaRepository

interface ShopItemOptionRepository : JpaRepository<ShopItemOptions, Long> {
}
