package jjfactory.reservation.domain.shop

import jjfactory.reservation.presentation.ShopDto
import jjfactory.reservation.domain.shop.holiday.Holiday
import jjfactory.reservation.domain.shop.holiday.HolidayRepository
import jjfactory.reservation.repository.shop.ShopRepository
import jjfactory.reservation.domain.shop.manager.ShopManagerRepository
import jjfactory.reservation.support.MailSender
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Transactional
@Service
class ShopService(
    private val shopReader: ShopReader,
    private val shopRepository: ShopRepository,
    private val redisTemplate: RedisTemplate<String, String>,
    private val shopManagerRepository: ShopManagerRepository,
    private val holidayRepository: HolidayRepository,
    private val mailSender: MailSender
) {

    val REDIS_RANK_KEY = "shop-ranking"

    @Transactional(readOnly = true)
    fun getDetail(id: Long): ShopDto.Detail {
        val shop = shopReader.findByIdOrThrow(id)

        redisTemplate.opsForZSet().incrementScore(REDIS_RANK_KEY, shop.id.toString(), 1.0)

        return ShopDto.Detail(
            id = shop.id!!,
            name = shop.name,
            phone = shop.phone,
            address = shop.address,
            bizNum = shop.bizNum
        )
    }

    fun registerShop(command: ShopCommand.Create): Long {
        if (shopRepository.existsByBizNum(command.bizNum)) throw IllegalArgumentException("duplicate bizNum")
        val initShop = command.toEntity()

        val shop = shopRepository.save(initShop)

        val initManager = command.manager.toEntity(shop.id!!)
        shopManagerRepository.save(initManager)

        mailSender.sendShopManagerActivateMail(initManager.phone)
        return shop.id
    }

    fun getViewRanking() {
        redisTemplate.opsForZSet().reverseRange(REDIS_RANK_KEY, 1, 10)?.forEach {
            println(it)
        }
    }

    fun addHolidayToShop(shopId: Long, dates: List<LocalDate>){
        val shop = shopReader.findByIdOrThrow(shopId)

        dates.forEach {
            val initEntity = Holiday(
                shop = shop,
                date = it
            )

            holidayRepository.save(initEntity)
        }
    }
}