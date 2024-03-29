package com.example.trbofficerandroid.domain.usecase

import com.example.trbofficerandroid.domain.repository.UserRepository

class GetClientListUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getClientList().reversed()
}