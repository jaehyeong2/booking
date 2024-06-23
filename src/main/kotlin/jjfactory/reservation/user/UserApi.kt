package jjfactory.reservation.user

import jjfactory.reservation.dto.CommonResponse
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserApi(
    private val userService: UserService
) {

    @PostMapping
    fun register(): CommonResponse<Long> {
        return CommonResponse(userService.join())
    }

    @PostMapping("/{id}/active-mail")
    fun sendActivateMail(@PathVariable id: Long){
        userService.sendActivateMail(id)
    }
}