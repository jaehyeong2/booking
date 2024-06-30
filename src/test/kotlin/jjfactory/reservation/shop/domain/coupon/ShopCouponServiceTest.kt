package jjfactory.reservation.shop.domain.coupon

import jjfactory.reservation.shop.domain.Shop
import jjfactory.reservation.shop.domain.ShopAddress
import jjfactory.reservation.shop.infra.ShopCouponRepository
import jjfactory.reservation.shop.infra.ShopRepository
import jjfactory.reservation.user.User
import jjfactory.reservation.user.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


@Transactional
@SpringBootTest
class ShopCouponServiceTest {
    @Autowired
    lateinit var shopCouponService: ShopCouponService
    @Autowired
    lateinit var userRepository: UserRepository
    @Autowired
    lateinit var shopCouponRepository: ShopCouponRepository
    @Autowired
    lateinit var shopRepository: ShopRepository

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    val REDIS_COUPON_KEY = "shop_coupon_user"

    @BeforeEach
    fun tearDown(){
        redisTemplate.delete(REDIS_COUPON_KEY)
    }

    @Test
    fun `쿠폰 발급 시작 안되었으면 exception`() {
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
            shopCouponService.issueCouponToUser(1L, couponId = coupon.id!!)
        }.isInstanceOf(UnAvailableCouponException::class.java)
    }

    @Test
    fun `유저의 쿠폰 발급 성공`() {
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

        val userCouponId = shopCouponService.issueCouponToUser(1L, couponId = coupon.id!!)

        assertThat(userCouponId).isNotNull
        assertThat(coupon.qty).isEqualTo(4)
    }

    @Test
    fun `동일 쿠폰 재발급 시도 시 익셉션`() {
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

        shopCouponService.issueCouponToUser(1L, couponId = coupon.id!!)

        assertThatThrownBy {
            shopCouponService.issueCouponToUser(1L, couponId = coupon.id!!)
        }.isInstanceOf(AlReadyIssuedUserException::class.java)
    }

    @Test
    fun `동시 과다 요청 시 테스트 실패`() {
        //given
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

        val executorService = Executors.newFixedThreadPool(100)
        val countDownLatch = CountDownLatch(100)

        //when
        for (i in 1 .. 100){
            executorService.submit{
                shopCouponService.issueCouponToUser(i.toLong(), couponId = coupon.id!!)
            }

            countDownLatch.countDown()
        }

        countDownLatch.await()
        assertThat(coupon.qty).isNotEqualTo(0)
    }
}