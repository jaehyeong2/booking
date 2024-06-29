package jjfactory.reservation.shop.domain.coupon

class ExpiredCouponException : RuntimeException()
class AlreadyUsedCouponException : RuntimeException()
class UnAvailableCouponException: RuntimeException()
class AlReadyIssuedUserException: RuntimeException()