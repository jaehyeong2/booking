package jjfactory.reservation.domain.shop

import jakarta.persistence.Embeddable

@Embeddable
class ShopAddress(
    val city: String,
    val street: String
) {

}