package jjfactory.reservation.domain.user

import jjfactory.reservation.repository.UserRepository
import jjfactory.reservation.support.MailSender
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalStateException
import java.time.Duration

@Transactional
@Service
class UserService(
    private val userRepository: UserRepository,
    private val mailSender: MailSender,
    private val redisTemplate: RedisTemplate<String, String>
) {

    val REDIS_USER_ACTIVATE_KEY = "active-mail"

    fun join(): Long {
        val initUser = User(
            lastName = "lee",
            firstName = "jae",
            phone = "01012341234",
        )

        val user = userRepository.save(initUser)

        //todo 쿠폰

        mailSender.sendUserActivateMail(user.phone)

        return user.id!!
    }

    fun sendActivateMail(userId: Long){
        val user = userRepository.findByIdOrNull(userId) ?: throw NotFoundException()

        if (user.isActive) throw IllegalArgumentException("이미 활성화된 유저입니다.")

        val opsForSet = redisTemplate.opsForSet()

        if (opsForSet.isMember(REDIS_USER_ACTIVATE_KEY, user.id.toString()) == true){
            throw IllegalStateException("")
        }

        mailSender.sendUserActivateMail(user.phone)

        // 레디스 저장
        opsForSet.add(REDIS_USER_ACTIVATE_KEY, user.id.toString())
        redisTemplate.expire(REDIS_USER_ACTIVATE_KEY, Duration.ofMinutes(1))
    }

}