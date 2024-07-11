package jjfactory.reservation.domain.shop.coupon

class ExpiredCouponException : RuntimeException()
class AlreadyUsedCouponException : RuntimeException()
class UnAvailableCouponException: RuntimeException()
class AlReadyIssuedUserException: RuntimeException()
class OutOfStockException: RuntimeException()