package jjfactory.reservation.payment

enum class PayGroup(
    val title: String,
    val payList: List<String>
) {
    CASH("현금", listOf("TOSS")),
    CARD("카드", listOf("CARD", "PAYCO", "TOSS", "KAKAO_PAY")),
    ETC("기타", listOf("POINT", "COUPON")),
    EMPTY("없음", emptyList());

    companion object {
        fun findByCode(code: String): PayGroup {
            return values().find {
                it.hasPayCode(code)
            } ?: EMPTY
        }
    }

    private fun hasPayCode(code: String): Boolean {
        return payList.contains(code)
    }
}