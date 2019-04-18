package io.domain.endpoint.service.endpoint

import io.domain.endpoint.data.domain.DomainOrganizationData
import io.domain.endpoint.data.domain.DomainRoleData
import io.domain.endpoint.data.domain.DomainUserData
import io.domain.endpoint.data.dto.DtoUserData
import io.domain.endpoint.domain.Organization
import io.domain.endpoint.domain.Role
import io.domain.endpoint.domain.User
import io.domain.endpoint.domain.UserStatus
import io.domain.endpoint.dto.AddUserRequestDto
import io.domain.endpoint.dto.UpdateUserRequestDto
import io.domain.endpoint.dto.UserDto
import io.domain.endpoint.service.entity.OrganizationService
import io.domain.endpoint.service.entity.RoleService
import io.domain.endpoint.service.entity.UserService
import io.domain.endpoint.service.mapper.UserMapper
import io.domain.endpoint.service.notification.UserNotificationService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
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
    private lateinit var roleService: RoleService
    @Mock
    private lateinit var organizationService: OrganizationService
    @Mock
    private lateinit var userNotificationService: UserNotificationService
    @Mock
    private lateinit var userMapper: UserMapper

    private lateinit var userEndpoint: UserEndpoint

    @Before
    fun setup() {
        userEndpoint = DefaultUserEndpoint(
                userService = userService,
                roleService = roleService,
                organizationService = organizationService,
                userNotificationService = userNotificationService,
                userMapper = userMapper
        )
    }

    @Test
    fun add_validRequest_returnsCreatedUser() { //PLOHOI TEST(((
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val role: Role = DomainRoleData.getRoleData()
        val organization: Organization = DomainOrganizationData.getOrganizationData()
        val createdUser: User = DomainUserData.getUserData(
                role = role,
                organization = organization
        )

        val expected: UserDto = DtoUserData.getUserDto()

        `when`(roleService.getRole(request.roleId)).thenReturn(role)
        `when`(organizationService.get(request.organizationId)).thenReturn(organization)
        `when`(userService.saveOrUpdate(createdUser)).thenReturn(createdUser)
        `when`(userMapper.toUserDto(createdUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.add(request)

        //assert
        assertEquals(expected, actual)
    }

    @Test
    fun add_validRequest_willSendInviteToCreatedUser() { //PLOHOI TEST(((
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val role: Role = DomainRoleData.getRoleData()
        val organization: Organization = DomainOrganizationData.getOrganizationData()
        val createdUser: User = DomainUserData.getUserData(
                role = role,
                organization = organization
        )

        `when`(roleService.getRole(request.roleId)).thenReturn(role)
        `when`(organizationService.get(request.organizationId)).thenReturn(organization)
        `when`(userService.saveOrUpdate(createdUser)).thenReturn(createdUser) //bad idea
        //act
        userEndpoint.add(request)

        //assert
        verify(userNotificationService).sendInvite(createdUser)
    }

//    @Test
//    fun addAlternative_validRequest_returnsCreatedUser() { // TEST POLEGCHE I KOROCHE
//        //arrange
//        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()
//
//        val createdUser: User = DomainUserData.getUserData()
//
//        val expected: UserDto = DtoUserData.getUserDto()
//
//        `when`(userMapper.toUser(request)).thenReturn(createdUser)
//        `when`(userService.saveOrUpdate(createdUser)).thenReturn(createdUser)
//        `when`(userMapper.toUserDto(createdUser)).thenReturn(expected)
//        //act
//        val actual: UserDto = userEndpoint.add(request)
//
//        //assert
//        assertEquals(expected, actual)
//    }

    //MAPPER NE DOLJEN ZANIMATSYA MODIFICATION'om
    // LEGKO ZAPUTATSYA
    //RABOTAET DA???
    @Test
    fun update_validRequest_returnsUpdatedUser() {
        //arrange
        val userId = 1L
        val request: UpdateUserRequestDto = DtoUserData.getUpdateUserRequestDto()

        val role: Role = DomainRoleData.getRoleData()
        val organization: Organization = DomainOrganizationData.getOrganizationData()
        val existsUser: User = DomainUserData.getUserData(
                role = role,
                organization = organization
        )

        val expected: UserDto = DtoUserData.getUserDto()

        `when`(userService.get(userId)).thenReturn(existsUser)
        `when`(userService.saveOrUpdate(existsUser)).thenReturn(existsUser) //WO ZA BRED MALCHIKI

        `when`(roleService.getRole(request.roleId)).thenReturn(role)
        `when`(organizationService.get(request.organizationId)).thenReturn(organization)
        `when`(userMapper.toUserDto(existsUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.update(userId, request)

        //assert
        assertEquals(expected, actual)
    }

    //MAPPER NE DOLJEN ZANIMATSYA MODIFICATION'om
    // LEGKO ZAPUTATSYA
    //HMMM POCHEMU PRI IZMENII DTO VSE PRODOLJAET RABOTAT'???? NE OCHEN NADEJNYI TEST
    @Test
    fun update_validRequestWithFirstNameLYALYALYA_returnsUpdatedUser() {
        //arrange
        val userId = 1L
        val request: UpdateUserRequestDto = DtoUserData.getUpdateUserRequestDto(
                firstName = "LYALYALYA",
                lastName = "DRUGOE CHTO-TO"
        )

        val role: Role = DomainRoleData.getRoleData()
        val organization: Organization = DomainOrganizationData.getOrganizationData()
        val existsUser: User = DomainUserData.getUserData(
                role = role,
                organization = organization
        )

        val expected: UserDto = DtoUserData.getUserDto()

        `when`(userService.get(userId)).thenReturn(existsUser)
        `when`(userService.saveOrUpdate(existsUser)).thenReturn(existsUser) //WO ZA BRED MALCHIKI

        `when`(roleService.getRole(request.roleId)).thenReturn(role)
        `when`(organizationService.get(request.organizationId)).thenReturn(organization)
        `when`(userMapper.toUserDto(existsUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.update(userId, request)

        //assert
        assertEquals(expected, actual)
    }

    //NENADEJNYI TEST((
    //LEGKO SLOMAT MOJNO
    //TYAJELO NAPISAT CHTO-TO HOROWEE
    @Test
    fun activate_targetUserIsNotActive_shouldActivateUser() {
        //arrange
        val userId = 1L
        val targetUser: User = DomainUserData.getUserData(
                status = UserStatus.DEACTIVATED
        )

        val activatedUser: User = targetUser.apply { this.status = UserStatus.ACTIVE }

        val expected: UserDto = DtoUserData.getUserDto(
                status = UserStatus.ACTIVE
        )

        `when`(userService.get(userId)).thenReturn(targetUser)
        `when`(userService.saveOrUpdate(activatedUser)).thenReturn(activatedUser)
        `when`(userMapper.toUserDto(targetUser)).thenReturn(expected)
        //act
        val actual: UserDto = userEndpoint.activate(userId)

        //assert
        assertEquals(expected, actual)
    }

}