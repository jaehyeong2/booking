package jjfactory.reservation.repository

import jjfactory.reservation.domain.book.BookCancel
import org.springframework.data.jpa.repository.JpaRepository

interface BookCancelRepository: JpaRepository<BookCancel, Long> {
}