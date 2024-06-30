package jjfactory.reservation.shop.domain.coupon

import jjfactory.reservation.shop.infra.ShopCouponRepository
import jjfactory.reservation.shop.infra.ShopRepository
import jjfactory.reservation.shop.infra.UserShopCouponRepository
import jjfactory.reservation.user.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Transactional
@Service
class ShopCouponService(
    private val shopRepository: ShopRepository,
    private val userRepository: UserRepository,
    private val shopCouponRepository: ShopCouponRepository,
    private val userShopCouponRepository: UserShopCouponRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {

    val REDIS_COUPON_KEY = "shop_coupon_user"

    fun registerCoupon(command: ShopCouponCommand.Create, shopId: Long): Long {
        val initCoupon = command.toEntity(shopId)

        val coupon = shopCouponRepository.save(initCoupon)
        return coupon.id!!
    }

    fun deleteCoupon(id: Long){
        val coupon = shopCouponRepository.findByIdOrNull(id) ?: throw NotFoundException()
        shopCouponRepository.delete(coupon)
    }

    fun issueCouponToUser(userId: Long, couponId: Long): Long {
        val coupon = shopCouponRepository.findByIdOrNull(couponId) ?: throw NotFoundException()

        if (redisTemplate.opsForSet().isMember(REDIS_COUPON_KEY, userId.toString()) == true){
            throw AlReadyIssuedUserException()
        }

        if (!coupon.issueAvailable()) throw UnAvailableCouponException()

        coupon.decreaseQty()

        val initUserCoupon = UserShopCoupon(
            coupon = coupon,
            expiredAt = LocalDateTime.now().plusSeconds(coupon.validSecond),
            userId = userId
        )

        redisTemplate.opsForSet().add(REDIS_COUPON_KEY, userId.toString())

        return userShopCouponRepository.save(initUserCoupon).id!!
    }
}