package jjfactory.reservation.shop.domain.item

import org.springframework.data.jpa.repository.JpaRepository

interface ShopItemRepository: JpaRepository<ShopItem, Long> {
}