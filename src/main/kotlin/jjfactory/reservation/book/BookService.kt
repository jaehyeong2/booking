package jjfactory.reservation.book

import jjfactory.reservation.shop.infra.ShopItemRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class BookService(
    private val shopItemRepository: ShopItemRepository,
    private val bookRepository: BookRepository
) {

    fun booking(loginUserId: Long, itemId: Long, bookStartAt: LocalDateTime, bookEndAt: LocalDateTime): Long {
        val item = shopItemRepository.findByIdOrNull(itemId) ?: throw NotFoundException()

        item.validateBookable(bookStartAt.toLocalDate())

        val initBook = Book(
            userId = loginUserId,
            itemId = itemId,
            bookStartAt = bookStartAt,
            bookEndAt = bookEndAt
        )

        val book = bookRepository.save(initBook)

        return book.id!!
    }

}