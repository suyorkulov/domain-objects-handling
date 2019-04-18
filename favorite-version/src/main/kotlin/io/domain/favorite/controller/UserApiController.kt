package io.domain.favorite.controller

import io.domain.favorite.dto.AddUserRequestDto
import io.domain.favorite.dto.UpdateUserRequestDto
import io.domain.favorite.dto.UserDto
import io.domain.favorite.service.endpoint.UserEndpoint
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * @author esuyorkulov
 */
@RestController
@RequestMapping("/api/users")
class UserApiController(
        private val userEndpoint: UserEndpoint
) {

    @PostMapping
    fun add(@Valid @RequestBody request: AddUserRequestDto): UserDto {
        return userEndpoint.add(request)
    }

    @PutMapping("/{userId}")
    fun update(@PathVariable userId: Long, request: UpdateUserRequestDto): UserDto {
        return userEndpoint.update(userId, request)
    }

    @GetMapping("/userId")
    fun get(@PathVariable userId: Long): UserDto {
        return userEndpoint.get(userId)
    }

    @PostMapping("/{userId}/activate")
    fun activate(@PathVariable userId: Long): UserDto {
        return userEndpoint.activate(userId)
    }

    @PostMapping("/{userId}/deactivate")
    fun deactivate(@PathVariable userId: Long): UserDto {
        return userEndpoint.deactivate(userId)
    }

}