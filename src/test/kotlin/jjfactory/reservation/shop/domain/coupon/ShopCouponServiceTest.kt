package jjfactory.reservation.shop.domain.coupon

import jjfactory.reservation.shop.domain.Shop
import jjfactory.reservation.shop.domain.ShopAddress
import jjfactory.reservation.shop.infra.ShopCouponRepository
import jjfactory.reservation.shop.infra.ShopRepository
import jjfactory.reservation.user.User
import jjfactory.reservation.user.UserRepository
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Transactional
@SpringBootTest
class ShopCouponServiceTest{
    @Autowired lateinit var shopCouponService: ShopCouponService
    @Autowired lateinit var userRepository: UserRepository
    @Autowired lateinit var shopCouponRepository: ShopCouponRepository
    @Autowired lateinit var shopRepository: ShopRepository

    @Test
    fun `쿠폰 발급 시작 안되었으면 exception`(){
        val initUser = User(
            lastName = "lee",
            firstName = "jae",
            phone = "01012341234"
        )

        val user = userRepository.save(initUser)

        val initShop = Shop(
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444",
        )

        val shop = shopRepository.save(initShop)

        val initCoupon = ShopCoupon(
            name = "name",
            shopId = shop.id!!,
            useStartAt = LocalDateTime.now().minusDays(10),
            useEndAt = LocalDateTime.now().plusDays(10),
            qty = 5,
            validSecond = 2000
        )

        val coupon = shopCouponRepository.save(initCoupon)

        assertThatThrownBy {
            shopCouponService.issueCouponToUser(user.id!!, couponId = coupon.id!!)
        }.isInstanceOf(UnAvailableCouponException::class.java)
    }

    @Test
    fun `유저의 쿠폰 발급 성공`(){
        val initUser = User(
            lastName = "lee",
            firstName = "jae",
            phone = "01012341234"
        )

        val user = userRepository.save(initUser)

        val initShop = Shop(
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444",
        )

        val shop = shopRepository.save(initShop)

        val initCoupon = ShopCoupon(
            name = "name",
            shopId = shop.id!!,
            useStartAt = LocalDateTime.now().minusDays(10),
            useEndAt = LocalDateTime.now().plusDays(10),
            qty = 5,
            validSecond = 2000
        )

        val coupon = shopCouponRepository.save(initCoupon)

        coupon.startIssue()

        val userCouponId = shopCouponService.issueCouponToUser(user.id!!, couponId = coupon.id!!)

        assertThat(userCouponId).isNotNull
    }

    @Test
    fun `동일 쿠폰 재발급 시도 시 익셉션`(){
        val initUser = User(
            lastName = "lee",
            firstName = "jae",
            phone = "01012341234"
        )

        val user = userRepository.save(initUser)

        val initShop = Shop(
            name = "shopA",
            phone = "0101234134",
            address = ShopAddress(
                city = "busan",
                street = "test"
            ),
            bizNum = "0123334444",
        )

        val shop = shopRepository.save(initShop)

        val initCoupon = ShopCoupon(
            name = "name",
            shopId = shop.id!!,
            useStartAt = LocalDateTime.now().minusDays(10),
            useEndAt = LocalDateTime.now().plusDays(10),
            qty = 5,
            validSecond = 2000
        )

        val coupon = shopCouponRepository.save(initCoupon)

        coupon.startIssue()

        shopCouponService.issueCouponToUser(user.id!!, couponId = coupon.id!!)

        assertThatThrownBy {
            shopCouponService.issueCouponToUser(user.id!!, couponId = coupon.id!!)
        }.isInstanceOf(AlReadyIssuedUserException::class.java)

    }
}