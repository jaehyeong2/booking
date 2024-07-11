package jjfactory.reservation.domain.shop

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jjfactory.reservation.domain.shop.holiday.Holiday
import java.time.LocalDate

@Entity
class Shop(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val phone: String,
    val address: ShopAddress,
    val bizNum: String
) {

    @OneToMany(mappedBy = "shop")
    val holidays: MutableSet<Holiday> = mutableSetOf()

    @Transient
    val holidayDates: Set<LocalDate> = holidays.map { it.date }.toSet()

    fun isHoliday(date: LocalDate): Boolean {
        if (holidayDates.contains(date)) return true
        return false
    }
}