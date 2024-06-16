package jjfactory.reservation.shop

import jjfactory.reservation.shop.manager.ShopManager

class ShopCommand {
    data class Create(
        val name: String,
        val phone: String,
        val address: ShopAddress,
        val bizNum: String,
        val manager: ShopManagerCommand.Create
    ){
        fun toEntity(): Shop {
            return Shop(
                name = name,
                phone = phone,
                address = address,
                bizNum = bizNum
            )
        }
    }

    data class Update(
        val name: String,
        val phone: String,
        val address: ShopAddress
    )
}

class ShopManagerCommand{
    data class Create(
        val lastName: String,
        val firstName: String,
        val phone: String,
        val email: String,
    ){
        fun toEntity(shopId: Long): ShopManager {
            return ShopManager(
                lastName = lastName,
                firstName = firstName,
                phone = phone,
                email = email,
                shopId = shopId
            )
        }
    }
}