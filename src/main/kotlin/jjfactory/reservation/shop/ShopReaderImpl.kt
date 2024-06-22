package jjfactory.reservation.shop

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ShopReaderImpl(
    private val shopRepository: ShopRepository
) : ShopReader {
    override fun findById(id: Long): Shop? {
        return shopRepository.findByIdOrNull(id)
    }

    override fun findByIdOrThrow(id: Long): Shop {
        return shopRepository.findByIdOrNull(id) ?: throw NotFoundException()
    }
}