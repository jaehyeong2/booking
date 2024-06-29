package jjfactory.reservation.shop.domain.coupon

import jjfactory.reservation.shop.infra.ShopCouponRepository
import jjfactory.reservation.shop.infra.ShopRepository
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ShopCouponService(
    private val shopRepository: ShopRepository,
    private val shopCouponRepository: ShopCouponRepository
) {
    fun registerCoupon(command: ShopCouponCommand.Create, shopId: Long): Long {
        val initCoupon = command.toEntity(shopId)

        val coupon = shopCouponRepository.save(initCoupon)
        return coupon.id!!
    }

    fun deleteCoupon(id: Long){
        val coupon = shopCouponRepository.findByIdOrNull(id) ?: throw NotFoundException()
        shopCouponRepository.delete(coupon)
    }
}