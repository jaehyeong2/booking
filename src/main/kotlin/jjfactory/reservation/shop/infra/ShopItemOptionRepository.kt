package jjfactory.reservation.shop.infra

import jjfactory.reservation.shop.domain.item.ShopItemOptions
import org.springframework.data.jpa.repository.JpaRepository

interface ShopItemOptionRepository : JpaRepository<ShopItemOptions, Long> {
}
