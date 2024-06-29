package jjfactory.reservation.shop.presentation

import jjfactory.reservation.shop.domain.ShopAddress

class ShopDto {
    data class Detail(
        val id: Long,
        val name: String,
        val phone: String,
        val address: ShopAddress,
        val bizNum: String
    ){

    }
}