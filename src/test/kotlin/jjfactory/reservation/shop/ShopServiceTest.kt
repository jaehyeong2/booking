package jjfactory.reservation.shop

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import jjfactory.reservation.shop.application.ShopService
import jjfactory.reservation.shop.domain.Shop
import jjfactory.reservation.shop.domain.ShopAddress
import jjfactory.reservation.shop.domain.ShopCommand
import jjfactory.reservation.shop.domain.ShopManagerCommand
import jjfactory.reservation.shop.domain.manager.ShopManager
import jjfactory.reservation.shop.domain.manager.ShopManagerRepository
import jjfactory.reservation.shop.infra.ShopRepository
import jjfactory.reservation.support.MailSender
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ShopServiceTest {
    @InjectMockKs
    lateinit var shopService: ShopService

    @MockK
    lateinit var shopRepository: ShopRepository

    @MockK
    lateinit var shopManagerRepository: ShopManagerRepository

    @MockK
    lateinit var mailSender: MailSender

    @Test
    fun `중복 사업자등록번호로 가입시 Exception`() {
        val command = ShopCommand.Create(
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444",
            manager = ShopManagerCommand.Create(
                lastName = "lee",
                firstName = "jae",
                phone = "01012341234",
                email = "test@test.com"
            )
        )

        every {
            shopRepository.existsByBizNum(any())
        } returns true

        Assertions.assertThatThrownBy {
            shopService.registerShop(command)
        }.isInstanceOf(IllegalArgumentException::class.java)

    }

    @Test
    fun `shop 등록 성공`() {
        val command = ShopCommand.Create(
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444",
            manager = ShopManagerCommand.Create(
                lastName = "lee",
                firstName = "jae",
                phone = "01012341234",
                email = "test@test.com"
            )
        )

        every {
            shopRepository.existsByBizNum(any())
        } returns false

        val shop = Shop(
            id = 1L,
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444",
        )
        every {
            shopRepository.save(any())
        } returns shop

        every {
            shopManagerRepository.save(any())
        } returns ShopManager(
            lastName = "lee",
            firstName = "jae",
            phone = "01012341234",
            email = "test@test.com",
            shopId = shop.id!!
        )

        //fixme

        shopService.registerShop(command)

        //횟수. verify Order는 순서
        verify { mailSender.sendShopManagerActivateMail(command.manager.phone) }
    }
}