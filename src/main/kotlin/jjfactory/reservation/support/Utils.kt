package jjfactory.reservation.support

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Utils {
}

inline fun <reified T> T.logger() : Logger {
    return LoggerFactory.getLogger(T::class.java)
}