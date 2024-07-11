package jjfactory.reservation.domain.shop.coupon

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
    var type: Type = Type.NORMAL,

    @CreationTimestamp
    val createdAt: LocalDateTime? = null,
    @UpdateTimestamp
    val updatedAt: LocalDateTime? = null,

    var qty: Int,
    var issueStarted: Boolean = false,

    var validSecond: Long,

    var useStartAt: LocalDateTime,
    var useEndAt: LocalDateTime,
    ) {

    enum class Type(name: String) {
        AUTO("자동 발행"),
        NORMAL("일반")
    }

    fun decreaseQty(){
        if (qty <= 0) throw OutOfStockException()
        qty--
    }

    fun issueAvailable(): Boolean {
        return issueStarted
    }

    fun startIssue(){
        issueStarted = true
    }
}