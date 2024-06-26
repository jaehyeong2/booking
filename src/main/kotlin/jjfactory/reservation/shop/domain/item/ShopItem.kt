package jjfactory.reservation.shop.domain.item

import jakarta.persistence.*
import jjfactory.reservation.shop.domain.Shop

@Entity
class ShopItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val shop: Shop,
    val name: String,
    @Column(columnDefinition = "TEXT")
    var description: String
) {
}