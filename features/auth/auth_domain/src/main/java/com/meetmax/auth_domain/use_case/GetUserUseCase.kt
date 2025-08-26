package com.meetmax.auth_domain.use_case

import com.meetmax.auth_domain.repository.AuthRepository
import com.meetmax.auth_domain.repository.User

class GetUserUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Result<List<User>> {
        return authRepository.getUser()
    }
}