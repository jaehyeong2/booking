package jjfactory.reservation.shop

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jjfactory.reservation.shop.holiday.Holiday

@Entity
class Shop(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val phone: String,
    val address: ShopAddress,
    val bizNum: String
) {

    @OneToMany(mappedBy = "shop")
    var holidays: List<Holiday> = listOf()
}