package jjfactory.reservation.user

enum class Grade(
    val discountRate: Int
) {
    A(25),
    B(20),
    C(15),
    D(10),
    E(5);
}