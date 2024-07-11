package jjfactory.reservation.user

import jjfactory.reservation.domain.user.Grade
import jjfactory.reservation.domain.user.User
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class UserTest{

    @Test
    fun getFullName(){
        val user = User(
            firstName = "jj",
            lastName = "lee",
            phone = "0101234134"
        )

        assertThat(user.getFullName()).isEqualTo("leejj")
    }

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