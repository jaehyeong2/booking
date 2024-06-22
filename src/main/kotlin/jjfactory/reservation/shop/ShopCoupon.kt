package jjfactory.reservation.shop

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime


@Table(indexes = [Index(name = "shopId", columnList = "shopId")])
@Entity
class ShopCoupon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val shopId: Long,
    val name: String,

    @Enumerated(EnumType.STRING)
    var type: Type,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,

    ) {

    enum class Type(name: String) {
        AUTO("자동 발행"),
        NORMAL("일반")
    }
}