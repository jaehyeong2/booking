package jjfactory.reservation.shop.domain.holiday

import org.springframework.data.jpa.repository.JpaRepository

interface HolidayRepository: JpaRepository<Holiday, Long> {
}