package jjfactory.reservation.book

import jjfactory.reservation.book.domain.BookCancel
import org.springframework.data.jpa.repository.JpaRepository

interface BookCancelRepository: JpaRepository<BookCancel, Long> {
}