package jjfactory.reservation.repository

import jjfactory.reservation.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}
