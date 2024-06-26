package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.data.remote.AuthService
import com.example.trbofficerandroid.domain.model.UserShort
import com.example.trbofficerandroid.domain.repository.UserRepository

class GetClientListUseCase(
    private val authService: AuthService,
    private val repository: UserRepository
) {
    suspend operator fun invoke(): List<UserShort> {
        val token = authService.getSignedInUser()?.token
            ?: throw Exception("Не авторизован")
        return repository.getClientList(token = token).reversed()
    }
}