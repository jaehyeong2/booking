package jjfactory.reservation.presentation

import jjfactory.reservation.domain.shop.ShopAddress

class ShopDto {
    data class Detail(
        val id: Long,
        val name: String,
        val phone: String,
        val address: ShopAddress,
        val bizNum: String
    )
}