package jjfactory.reservation.shop

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockKExtension::class)
class ShopServiceTest{
    @InjectMockKs
    lateinit var shopService: ShopService

    @MockK
    lateinit var shopRepository: ShopRepository

    @Test
    fun `중복 사업자등록번호로 가입시 Exception`() {
        val command = ShopCommand.Create(
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444"
        )

        every {
            shopRepository.existsByBizNum(any())
        } returns true

        Assertions.assertThatThrownBy {
            shopService.registerShop(command)
        }.isInstanceOf(IllegalArgumentException::class.java)

    }
}