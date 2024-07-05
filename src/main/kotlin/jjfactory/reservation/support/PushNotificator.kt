package jjfactory.reservation.support

interface PushNotificator {
    fun sendBookCancelNotification(token: String)
    fun sendBookingNotification(token: String)
}