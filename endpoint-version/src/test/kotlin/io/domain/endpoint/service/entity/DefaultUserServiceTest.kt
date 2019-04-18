package io.domain.endpoint.service.entity

import io.domain.endpoint.data.domain.DomainUserData
import io.domain.endpoint.domain.User
import io.domain.endpoint.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author esuyorkulov
 */
@RunWith(MockitoJUnitRunner::class)
class DefaultUserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    private lateinit var userService: UserService

    @Before
    fun setup() {
        userService = DefaultUserService(userRepository)
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

}