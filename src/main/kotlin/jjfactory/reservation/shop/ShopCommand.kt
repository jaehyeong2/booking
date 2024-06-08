package jjfactory.reservation.shop

class ShopCommand {
    data class Create(
        val name: String,
        val phone: String,
        val address: ShopAddress
    ){
        fun toEntity(): Shop {
            return Shop(
                name = name,
                phone = phone,
                address = address
            )
        }
    }

    data class Update(
        val name: String,
        val phone: String,
        val address: ShopAddress
    )
}