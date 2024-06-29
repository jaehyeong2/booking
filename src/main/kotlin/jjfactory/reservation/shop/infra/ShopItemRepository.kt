package jjfactory.reservation.shop.infra

import jjfactory.reservation.shop.domain.item.ShopItem
import org.springframework.data.jpa.repository.JpaRepository

interface ShopItemRepository: JpaRepository<ShopItem, Long> {
}