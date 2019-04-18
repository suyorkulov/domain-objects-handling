package io.domain.classic.controller

import io.domain.classic.domain.User
import io.domain.classic.domain.UserStatus
import io.domain.classic.dto.AddUserRequestDto
import io.domain.classic.dto.UpdateUserRequestDto
import io.domain.classic.dto.UserDto
import io.domain.classic.service.UserService
import io.domain.classic.service.mapper.UserMapper
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author esuyorkulov
 */
@RestController
@RequestMapping("/api/users")
class UserApiController(
        private val userService: UserService,
        private val userMapper: UserMapper
) {

    @PostMapping
    fun add(@Valid @RequestBody request: AddUserRequestDto): UserDto {
        val user: User = userMapper.toUser(request)

        val createdUser: User = userService.add(user)

        return userMapper.toUserDto(createdUser)
    }

    @PutMapping("/{userId}")
    fun update(@PathVariable userId: Long, request: UpdateUserRequestDto): UserDto {
        val updatedUser: User = userService.update(userId, request)

        return userMapper.toUserDto(updatedUser)
    }

    @GetMapping("/userId")
    fun get(@PathVariable userId: Long): UserDto {
        val user: User = userService.get(userId)

        return userMapper.toUserDto(user)
    }

    @PostMapping("/{userId}/activate")
    fun activate(@PathVariable userId: Long): UserDto {
        val user: User = userService.get(userId)
        user.status = UserStatus.ACTIVE

        val activatedUser: User = userService.saveOrUpdate(user)

        return userMapper.toUserDto(activatedUser)
    }

    @PostMapping("/{userId}/deactivate")
    fun deactivate(@PathVariable userId: Long): UserDto {
        val user: User = userService.get(userId)
        user.status = UserStatus.DEACTIVATED

        val deactivated: User = userService.saveOrUpdate(user)

        return userMapper.toUserDto(deactivated)
    }


}