package jjfactory.reservation.book.presentation

import jjfactory.reservation.shop.presentation.ShopDto
import java.time.LocalDateTime

class BookDto {
    data class Detail(
        val id: Long,
        val shop: ShopDto.Detail,
        val bookStartAt: LocalDateTime,
        val bookEndAt: LocalDateTime,
        val createdAt: LocalDateTime
    )

    data class CreateRequest(
        val itemId: Long,
        val startAt: LocalDateTime,
        val endAt: LocalDateTime
    )

    data class CancelRequest(
        val reason: String
    )
}