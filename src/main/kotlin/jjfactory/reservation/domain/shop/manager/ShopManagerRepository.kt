package jjfactory.reservation.domain.shop.manager

import org.springframework.data.jpa.repository.JpaRepository

interface ShopManagerRepository : JpaRepository<ShopManager, Long> {
}