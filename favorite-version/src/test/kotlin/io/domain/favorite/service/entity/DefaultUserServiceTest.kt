package io.domain.favorite.service.entity

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.domain.favorite.data.domain.DomainOrganizationData
import io.domain.favorite.data.domain.DomainRoleData
import io.domain.favorite.data.domain.DomainUserData
import io.domain.favorite.domain.Organization
import io.domain.favorite.domain.Role
import io.domain.favorite.domain.User
import io.domain.favorite.domain.UserStatus
import io.domain.favorite.domain.request.AddUserRequest
import io.domain.favorite.domain.request.UpdateUserRequest
import io.domain.favorite.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

/**
 * @author esuyorkulov
 */
@RunWith(MockitoJUnitRunner::class)
class DefaultUserServiceTest {

    private lateinit var userRepository: UserRepository

    private val uuidGenerator: () -> UUID = { UUID.fromString("cd656fad-ff45-4d82-ab1d-98487f9f7a05") }

    private lateinit var userService: UserService

    @Before
    fun setup() {
        userRepository = mock()
        userService = DefaultUserService(
                userRepository = userRepository,
                uuidGenerator = uuidGenerator
        )
    }

    @Test
    fun `saveOrUpdate_save???_orUpdate??_wtf`() {
        //arrange
        val user: User = DomainUserData.getUserData()

        `when`(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.saveOrUpdate(user)

        //assert
        assertEquals(user, actual)
    }

    @Test
    fun add_request_returnsCreatedUserWithPendingStatus() {
        //arrange
        val request: AddUserRequest = DomainUserData.getAddUserRequest()
        val user: User = DomainUserData.getUserData(
                firstName = request.firstName,
                lastName = request.lastName,
                email = request.email,
                phoneNumber = request.phoneNumber,
                role = request.role,
                organization = request.organization,
                status = UserStatus.PENDING,
                password = uuidGenerator.invoke().toString()
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.add(request)

        //assert
        assertEquals(user, actual)
    }

    @Test
    fun update_requestWithFirstName_returnsUserWithUpdatedFirstName() {
        //arrange
        val expected = "LYALYALYA"
        val user: User = DomainUserData.getUserData(
                id = 1,
                firstName = "First"
        )
        val request: UpdateUserRequest = DomainUserData.getUpdateUserRequest(
                firstName = expected
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.update(user, request)

        //assert
        assertEquals(expected, actual.firstName)
    }

    @Test
    fun update_requestWithLastName_returnsUserWithUpdatedLastName() {
        //arrange
        val expected = "LYALYALYA"
        val user: User = DomainUserData.getUserData(
                id = 1,
                lastName = "Old"
        )
        val request: UpdateUserRequest = DomainUserData.getUpdateUserRequest(
                lastName = expected
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.update(user, request)

        //assert
        assertEquals(expected, actual.lastName)
    }

    @Test
    fun update_requestWithNewEmail_returnsUserWithUpdatedEmail() {
        //arrange
        val expected = "new@email.com"
        val user: User = DomainUserData.getUserData(
                id = 1,
                email = "old@email.com"
        )
        val request: UpdateUserRequest = DomainUserData.getUpdateUserRequest(
                email = expected
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.update(user, request)

        //assert
        assertEquals(expected, actual.email)
    }

    @Test
    fun update_requestWithNewPhoneNumber_returnsUserWithUpdatedPhoneNumber() {
        //arrange
        val expected = "+996555555"
        val user: User = DomainUserData.getUserData(
                id = 1,
                phoneNumber = null
        )
        val request: UpdateUserRequest = DomainUserData.getUpdateUserRequest(
                phoneNumber = expected
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.update(user, request)

        //assert
        assertEquals(expected, actual.phoneNumber)
    }

    @Test
    fun update_requestWithNewRole_returnsUserWithUpdatedRole() {
        //arrange
        val expected: Role = DomainRoleData.getRoleData(name = "SUPERHERO")
        val user: User = DomainUserData.getUserData(
                id = 1,
                role = DomainRoleData.getRoleData(name = "OLD ROLE")
        )
        val request: UpdateUserRequest = DomainUserData.getUpdateUserRequest(
                role = expected
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.update(user, request)

        //assert
        assertEquals(expected, actual.role)
    }

    @Test
    fun update_requestWithNewOrganization_returnsUserWithUpdatedOrganization() {
        //arrange
        val expected: Organization = DomainOrganizationData.getOrganizationData(name = "Zensoft")
        val user: User = DomainUserData.getUserData(
                id = 1,
                role = DomainRoleData.getRoleData(name = "Google")
        )
        val request: UpdateUserRequest = DomainUserData.getUpdateUserRequest(
                organization = expected
        )

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.update(user, request)

        //assert
        assertEquals(expected, actual.organization)
    }

    @Test
    fun activate_userIsDeactivated_returnsActivatedUser() {
        //arrange
        val user: User = DomainUserData.getUserData(
                id = 1,
                status = UserStatus.DEACTIVATED
        )

        val expected = UserStatus.ACTIVE

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.activate(user)

        //assert
        assertEquals(expected, actual.status)
    }

    @Test
    fun deactivate_userIsActive_returnsDeactivatedUser() {
        //arrange
        val user: User = DomainUserData.getUserData(
                id = 1,
                status = UserStatus.ACTIVE
        )

        val expected = UserStatus.DEACTIVATED

        whenever(userRepository.save(user)).thenReturn(user)
        //act
        val actual: User = userService.deactivate(user)

        //assert
        assertEquals(expected, actual.status)
    }

}