package jjfactory.reservation.shop

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ShopService(
    private val shopRepository: ShopRepository
) {

    @Transactional(readOnly = true)
    fun getById(id: Long): Shop {
        val shop = shopRepository.findByIdOrNull(id) ?: throw  NotFoundException()
        return shop
    }

    fun registerShop(command: ShopCommand.Create): Long {
        if (shopRepository.existsByBizNum(command.bizNum)) throw IllegalArgumentException("duplicate bizNum")
        val initShop = command.toEntity()

        return shopRepository.save(initShop).id!!
    }
}