package jjfactory.reservation.book

import org.springframework.data.jpa.repository.JpaRepository

interface BookCancelRepository: JpaRepository<BookCancel, Long> {
}