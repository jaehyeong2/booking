package jjfactory.reservation.shop.domain.manager

import org.springframework.data.jpa.repository.JpaRepository

interface ShopManagerRepository : JpaRepository<ShopManager, Long> {
}