package jjfactory.reservation.domain.shop.holiday

import org.springframework.data.jpa.repository.JpaRepository

interface HolidayRepository: JpaRepository<Holiday, Long> {
}