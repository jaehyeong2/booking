package jjfactory.reservation.support

import org.springframework.stereotype.Component

@Component
class MailSenderImpl : MailSender {

    override fun sendShopManagerActivateMail(phone: String) {
        logger().info("sendShopManagerActivateMail called")
    }

    override fun sendUserActivateMail(phone: String) {
        logger().info("sendUserActivateMail called")
    }
}