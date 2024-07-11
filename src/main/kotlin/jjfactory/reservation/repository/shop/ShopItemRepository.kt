package jjfactory.reservation.repository.shop

import jjfactory.reservation.domain.shop.item.ShopItem
import org.springframework.data.jpa.repository.JpaRepository

interface ShopItemRepository: JpaRepository<ShopItem, Long> {
}