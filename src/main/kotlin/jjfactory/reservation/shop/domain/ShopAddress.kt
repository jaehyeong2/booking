package jjfactory.reservation.shop.domain

import jakarta.persistence.Embeddable

@Embeddable
class ShopAddress(
    val city: String,
    val street: String
) {

}