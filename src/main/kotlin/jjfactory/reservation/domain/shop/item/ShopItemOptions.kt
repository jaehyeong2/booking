package jjfactory.reservation.domain.shop.item

import jakarta.persistence.*

@Entity
class ShopItemOptions(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val shopItem: ShopItem,
    val name: String,
    @Column(columnDefinition = "TEXT")
    var description: String
) {
}