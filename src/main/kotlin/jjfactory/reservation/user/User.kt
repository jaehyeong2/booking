package jjfactory.reservation.user

import jakarta.persistence.*

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val lastName: String,
    val firstName: String,
    val phone: String,

    var token: String,

    var isActive: Boolean = false,

    @Enumerated(EnumType.STRING)
    var grade: Grade = Grade.E
) {

    fun getFullName(): String {
        return lastName + firstName
    }

    fun getDiscountRate() = grade.discountRate

    fun upgrade() {
        if (grade.ordinal >= 1) {
            grade = Grade.values()[grade.ordinal - 1]
        }
    }

    fun activate() {
        isActive = true
    }
}