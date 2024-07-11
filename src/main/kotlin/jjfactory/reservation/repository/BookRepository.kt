package jjfactory.reservation.repository

import jjfactory.reservation.domain.book.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
}