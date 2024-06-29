package jjfactory.reservation.shop.domain

import jjfactory.reservation.shop.domain.Shop

interface ShopReader {
    fun findById(id: Long): Shop?
    fun findByIdOrThrow(id: Long): Shop
}