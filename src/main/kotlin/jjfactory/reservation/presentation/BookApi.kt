package jjfactory.reservation.presentation

import jjfactory.reservation.domain.book.BookService
import jjfactory.reservation.presentation.dto.CommonResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/book")
@RestController
class BookApi(
    private val bookService: BookService
) {

    @PostMapping
    fun booking(@RequestBody request: BookDto.CreateRequest): CommonResponse<Unit> {
        val loginUserId = 2L
        bookService.booking(loginUserId, request.itemId, request.startAt, request.endAt)
        return CommonResponse.OK
    }

    @PostMapping("/{id}/cancel")
    fun cancelBook(@PathVariable id : Long, @RequestBody request: BookDto.CancelRequest): CommonResponse<Unit> {
        val loginUserId = 2L
        bookService.cancelBook(loginUserId, id, request.reason)
        return CommonResponse.OK
    }
}