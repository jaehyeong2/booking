package jjfactory.reservation.domain.shop

interface ShopReader {
    fun findById(id: Long): Shop?
    fun findByIdOrThrow(id: Long): Shop
}