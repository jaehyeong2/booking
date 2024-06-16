package jjfactory.reservation.shop.manager

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ShopManager(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val lastName: String,
    val firstName: String,
    val phone: String,
    val email: String,
    val shopId: Long,
) {
}