package jjfactory.reservation.domain.book

import jjfactory.reservation.repository.BookCancelRepository
import jjfactory.reservation.repository.BookRepository
import jjfactory.reservation.repository.shop.ShopItemRepository
import jjfactory.reservation.support.PushNotificator
import jjfactory.reservation.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class BookService(
    private val shopItemRepository: ShopItemRepository,
    private val bookRepository: BookRepository,
    private val bookCancelRepository: BookCancelRepository,
    private val userRepository: UserRepository,
    private val pushNotificator: PushNotificator
) {


    fun getBookDetail(bookId: Long){
        val book = bookRepository.findByIdOrNull(bookId) ?: throw NotFoundException()


    }

    fun booking(loginUserId: Long, itemId: Long, bookStartAt: LocalDateTime, bookEndAt: LocalDateTime): Long {
        val item = shopItemRepository.findByIdOrNull(itemId) ?: throw NotFoundException()
        val user = userRepository.findByIdOrNull(loginUserId) ?: throw NotFoundException()

        item.validateBookable(bookStartAt.toLocalDate())

        val initBook = Book(
            userId = loginUserId,
            itemId = itemId,
            bookStartAt = bookStartAt,
            bookEndAt = bookEndAt
        )

        val book = bookRepository.save(initBook)

        user.token?.let {
            pushNotificator.sendBookingNotification(it)
        }

        return book.id!!
    }


    fun cancelBook(loginUserId: Long, bookId: Long, cancelReason: String){
        val user = userRepository.findByIdOrNull(loginUserId) ?: throw NotFoundException()
        val book = bookRepository.findByIdOrNull(bookId) ?: throw NotFoundException()

        if (book.userId != loginUserId) throw IllegalArgumentException("권한 없음. 커스텀 예외로 수정하기")

        book.cancel()

        val initCancel = BookCancel(
            book = book,
            reason = cancelReason
        )

        bookCancelRepository.save(initCancel)

        //todo 결제 되엇다면? (선 결제 / 후불결제)

        user.token?.let {
            pushNotificator.sendBookCancelNotification(it)
        }
    }

}