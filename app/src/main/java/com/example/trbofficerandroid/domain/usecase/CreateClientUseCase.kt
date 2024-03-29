package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.model.CreateClient
import com.example.trbofficerandroid.domain.repository.UserRepository

class CreateClientUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(client: CreateClient) {
        TODO("Убрал id пользователя")
        val userId = throw Exception("Пользователь не авторизован")
        repository.createClient(client.copy(whoCreatedId = userId))
    }
}