package io.domain.favorite.service.endpoint

import com.nhaarman.mockito_kotlin.whenever
import io.domain.favorite.data.domain.DomainUserData
import io.domain.favorite.data.dto.DtoUserData
import io.domain.favorite.domain.User
import io.domain.favorite.domain.UserStatus
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.domain.request.UpdateUserRequest
import io.domain.favorite.dto.AddUserRequestDto
import io.domain.favorite.dto.UpdateUserRequestDto
import io.domain.favorite.dto.UserDto
import io.domain.favorite.service.entity.UserService
import io.domain.favorite.service.mapper.UserMapper
import io.domain.favorite.service.notification.UserNotificationService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author esuyorkulov
 */
@RunWith(MockitoJUnitRunner::class)
class DefaultUserEndpointTest {

    @Mock
    private lateinit var userService: UserService
    @Mock
    private lateinit var userNotificationService: UserNotificationService
    @Mock
    private lateinit var userMapper: UserMapper

    private lateinit var userEndpoint: UserEndpoint

    @Before
    fun setup() {
        userEndpoint = DefaultUserEndpoint(
                userService = userService,
                userNotificationService = userNotificationService,
                userMapper = userMapper
        )
    }

    //POLUCHAETSYA MY TESTIRUEM PLOTNO ENTITY SERVICES ZA VYPOLNENIE BLL
    //MAPPERY OTDELNO
    //ENDPOINT ORKESTRATOR, CEPOCHKA VYZOVOV O COMMAND

    @Test
    fun add_validRequest_returnsCreatedUser() {
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val addUserRequest: AddUserRequest = DomainUserData.getAddUserRequest()
        val createdUser: User = DomainUserData.getUserData()

        val expected: UserDto = DtoUserData.getUserDto()

        whenever(userMapper.toAddUserRequest(request)).thenReturn(addUserRequest)
        whenever(userService.add(addUserRequest)).thenReturn(createdUser)
        whenever(userMapper.toUserDto(createdUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.add(request)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun add_validRequest_willSendInviteToCreatedUser() {
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val addUserRequest: AddUserRequest = DomainUserData.getAddUserRequest()
        val createdUser: User = DomainUserData.getUserData()

        whenever(userMapper.toAddUserRequest(request)).thenReturn(addUserRequest)
        whenever(userService.add(addUserRequest)).thenReturn(createdUser)
        //act
        userEndpoint.add(request)

        //assert
        verify(userNotificationService).sendInvite(createdUser)
    }

    @Test
    fun update_validRequest_returnsUpdatedUser() {
        //arrange
        val userId = 1L
        val request: UpdateUserRequestDto = DtoUserData.getUpdateUserRequestDto()

        val targetUser: User = DomainUserData.getUserData()
        val updateUserRequest: UpdateUserRequest = DomainUserData.getUpdateUserRequest()

        val expected: UserDto = DtoUserData.getUserDto()

        whenever(userService.get(userId)).thenReturn(targetUser)
        whenever(userMapper.toUpdateUserRequest(request)).thenReturn(updateUserRequest)
        whenever(userService.update(targetUser, updateUserRequest)).thenReturn(targetUser)
        whenever(userMapper.toUserDto(targetUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.update(userId, request)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun activate_targetUserIsDeactivated_returnsUpdatedUser() {
        //arrange
        val userId = 1L

        val targetUser: User = DomainUserData.getUserData(
                status = UserStatus.DEACTIVATED
        )
        val expected: UserDto = DtoUserData.getUserDto()

        whenever(userService.get(userId)).thenReturn(targetUser)
        whenever(userService.activate(targetUser)).thenReturn(targetUser)
        whenever(userMapper.toUserDto(targetUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.activate(userId)

        //assert
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun activate_targetUserAlreadyActive_throwsIllegalStateException() {
        //arrange
        val userId = 1L

        val targetUser: User = DomainUserData.getUserData(
                status = UserStatus.ACTIVE
        )

        whenever(userService.get(userId)).thenReturn(targetUser)
        //act
        userEndpoint.activate(userId)

        //assert
    }

    @Test
    fun deactivate_targetUserIsActivate_returnsUpdatedUser() {
        //arrange
        val userId = 1L

        val targetUser: User = DomainUserData.getUserData(
                status = UserStatus.ACTIVE
        )
        val expected: UserDto = DtoUserData.getUserDto()

        whenever(userService.get(userId)).thenReturn(targetUser)
        whenever(userService.deactivate(targetUser)).thenReturn(targetUser)
        whenever(userMapper.toUserDto(targetUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.deactivate(userId)

        //assert
        assertEquals(expected, actual)
    }

    @Test(expected = IllegalStateException::class)
    fun deactivate_targetUserAlreadyDeactivated_throwsIllegalStateException() {
        //arrange
        val userId = 1L

        val targetUser: User = DomainUserData.getUserData(
                status = UserStatus.DEACTIVATED
        )

        whenever(userService.get(userId)).thenReturn(targetUser)
        //act
        userEndpoint.deactivate(userId)

        //assert
    }

}