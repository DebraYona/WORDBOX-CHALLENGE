package com.simios.workboxchallenge.domain

import com.simios.workboxchallenge.data.repository.GetUserRepository
import com.simios.workboxchallenge.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetUsersUseCaseTest {
    @RelaxedMockK
    private lateinit var repo: GetUserRepository

    private lateinit var getUsersUseCase: GetUsersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getUsersUseCase = GetUsersUseCase(repo)
    }

    @Test
    fun `when the api returns empty, get from database`() = runBlocking {
        coEvery { repo.getAllUsersFromApi() } returns emptyList()

        getUsersUseCase()

        coVerify(exactly = 1) { repo.getAllUsersFromDatabase() }

    }

    @Test
    fun `when api return a list, store the result in the database`() = runBlocking {
        val users = listOf(User("female", 10, "ele", "pic", "mail", "loc", "999999"))
        coEvery { repo.getAllUsersFromApi() } returns users

        val response = getUsersUseCase()

        coVerify(exactly = 1) { repo.clearUser() }
        coVerify(exactly = 1) { repo.insertAllUser(any()) }
        coVerify(exactly = 0) { repo.getAllUsersFromDatabase() }
        assert(response == users)
    }
}