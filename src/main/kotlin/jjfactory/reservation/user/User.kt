package jjfactory.reservation.user

import jakarta.persistence.*

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val lastName: String,
    val firstName: String,
    val phone: String,

    @Enumerated(EnumType.STRING)
    var grade: Grade = Grade.E
) {

    fun getDiscountRate() = grade.discountRate

    fun upgrade(){
        if (grade.ordinal >= 1) {
            grade = Grade.values()[grade.ordinal-1]
        }
    }
}