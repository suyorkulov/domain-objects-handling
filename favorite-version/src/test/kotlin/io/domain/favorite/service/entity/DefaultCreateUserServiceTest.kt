package io.domain.favorite.service.entity

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.domain.favorite.data.domain.DomainOrganizationData
import io.domain.favorite.data.domain.DomainRoleData
import io.domain.favorite.data.domain.DomainUserData
import io.domain.favorite.data.dto.DtoUserData
import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.Role
import io.domain.favorite.domain.User
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.dto.AddUserRequestDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * @author esuyorkulov
 */
class DefaultCreateUserServiceTest {

    private lateinit var userService: UserService
    private lateinit var roleService: RoleService
    private lateinit var organizationService: OrganizationService

    private lateinit var createUserService: CreateUserService


    @Before
    fun setup() {
        userService = mock()
        roleService = mock()
        organizationService = mock()

        createUserService = DefaultCreateUserService(
                userService = userService,
                roleService = roleService,
                organizationService = organizationService
        )
    }

    @Test
    fun create_requestWithRoleAndOrganization_returnsCreatedUser() {
        //arrange
        val request: AddUserRequestDto = DtoUserData.getAddUserRequestDto()

        val role: Role = DomainRoleData.getRoleData()
        val organization: Organization = DomainOrganizationData.getOrganizationData()

        val addRequest: AddUserRequest = DomainUserData.getAddUserRequest(
                role = role,
                organization = organization,
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                phoneNumber = request.phoneNumber
        )
        val expected: User = DomainUserData.getUserData()

        whenever(roleService.getRole(request.roleId)).thenReturn(role)
        whenever(organizationService.get(request.organizationId)).thenReturn(organization)
        whenever(userService.add(addRequest)).thenReturn(expected)
        //act
        val actual: User = createUserService.create(request)

        //assert
        assertEquals(expected, actual)
    }

}