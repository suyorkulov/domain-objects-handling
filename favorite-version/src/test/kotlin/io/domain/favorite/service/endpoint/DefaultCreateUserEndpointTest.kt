package io.domain.favorite.service.endpoint

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.domain.favorite.data.domain.DomainUserData
import io.domain.favorite.data.dto.DtoUserData
import io.domain.favorite.domain.User
import io.domain.favorite.dto.AddUserRequestDto
import io.domain.favorite.dto.UserDto
import io.domain.favorite.service.entity.CreateUserService
import io.domain.favorite.service.mapper.UserMapper
import io.domain.favorite.service.notification.UserNotificationService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author esuyorkulov
 */

class DefaultCreateUserEndpointTest {

    private lateinit var createUserService: CreateUserService
    private lateinit var userNotificationService: UserNotificationService
    private lateinit var userMapper: UserMapper

    private lateinit var createUserEndpoint: CreateUserEndpoint

    @Before
    fun setup() {
        createUserService = mock()
        userNotificationService = mock()
        userMapper = mock()

        createUserEndpoint = DefaultCreateUserEndpoint(
                createUserService = createUserService,
                userMapper = userMapper,
                userNotificationService = userNotificationService
        )
    }

    @Test
    fun create_validRequest_returnsCreatedUser() {
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val createdUser: User = DomainUserData.getUserData()
        val expected: UserDto = DtoUserData.getUserDto()

        whenever(createUserService.create(request)).thenReturn(createdUser)
        whenever(userMapper.toUserDto(createdUser)).thenReturn(expected)
        //act
        val actual: UserDto = createUserEndpoint.create(request)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun create_validRequest_shouldSendInviteToCreatedUser() {
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val createdUser: User = DomainUserData.getUserData()

        whenever(createUserService.create(request)).thenReturn(createdUser)
        //act
        createUserEndpoint.create(request)

        //assert
        verify(userNotificationService).sendInvite(createdUser)
    }

}