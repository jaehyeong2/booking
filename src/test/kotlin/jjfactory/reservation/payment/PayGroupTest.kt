package jjfactory.reservation.payment

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PayGroupTest{

    @Test
    fun findByCodeTest(){
        val code1 = "test"
        val code2 = "CARD"
        val code3 = "TOSS"

        assertThat(PayGroup.findByCode(code1)).isEqualTo(PayGroup.EMPTY)
        assertThat(PayGroup.findByCode(code2)).isEqualTo(PayGroup.CARD)
        assertThat(PayGroup.findByCode(code3)).isEqualTo(PayGroup.CASH)

    }
}