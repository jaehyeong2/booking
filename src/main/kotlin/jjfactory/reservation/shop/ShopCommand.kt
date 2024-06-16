package jjfactory.reservation.shop

class ShopCommand {
    data class Create(
        val name: String,
        val phone: String,
        val address: ShopAddress,
        val bizNum: String
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