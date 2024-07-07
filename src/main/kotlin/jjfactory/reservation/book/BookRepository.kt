package jjfactory.reservation.book

import jjfactory.reservation.book.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
}