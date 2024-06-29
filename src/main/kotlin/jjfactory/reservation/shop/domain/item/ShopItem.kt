package jjfactory.reservation.shop.domain.item

import jakarta.persistence.*

@Entity
class ShopItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val shopId: Long,
    val name: String,
    @Column(columnDefinition = "TEXT")
    var description: String
) {
}