package com.example.testrelog.data.local

import com.example.testrelog.data.remote.RelogApi
import com.example.testrelog.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: RelogApi
): AuthRepository {

}