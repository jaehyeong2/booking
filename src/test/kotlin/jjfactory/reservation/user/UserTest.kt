package jjfactory.reservation.user

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.E

class UserTest{

    @Test
    fun `초기 grade는 E`(){
        val user = User(
            firstName = "jj",
            lastName = "lee",
            phone = "0101234134"
        )

        assertThat(user.grade).isEqualTo(Grade.E)
    }

    @Test
    fun upgrade(){
        val user = User(
            firstName = "jj",
            lastName = "lee",
            phone = "0101234134"
        )

        user.upgrade()
        assertThat(user.grade).isEqualTo(Grade.D)

        user.upgrade()
        assertThat(user.grade).isEqualTo(Grade.C)

        user.upgrade()
        assertThat(user.grade).isEqualTo(Grade.B)

        user.upgrade()
        assertThat(user.grade).isEqualTo(Grade.A)

        user.upgrade()
        assertThat(user.grade).isEqualTo(Grade.A)
    }
}