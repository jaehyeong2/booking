package jjfactory.reservation.support

import org.springframework.stereotype.Component

@Component
class PushNotificatorImpl : PushNotificator {
    override fun sendBookCancelNotification(token: String) {
        TODO("Not yet implemented")
    }

    override fun sendBookingNotification(token: String) {
        TODO("Not yet implemented")
    }
}