package jjfactory.reservation.shop

import jjfactory.reservation.shop.manager.ShopManagerRepository
import jjfactory.reservation.support.MailSender
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ShopService(
    private val shopReader: ShopReader,
    private val shopRepository: ShopRepository,
    private val shopManagerRepository: ShopManagerRepository,
    private val mailSender: MailSender
) {

    @Transactional(readOnly = true)
    fun getDetail(id: Long): ShopDto.Detail {
        val shop = shopReader.findByIdOrThrow(id)

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
}