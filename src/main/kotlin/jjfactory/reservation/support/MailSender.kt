package jjfactory.reservation.support

interface MailSender {
    fun sendShopManagerActivateMail(phone: String)
    fun sendUserActivateMail(phone: String)
}