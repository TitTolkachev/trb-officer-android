package com.example.trbofficerandroid.domain.model

data class CreateUser(
    val firstName: String,
    val lastName: String,
    val patronymicName: String? = null,
    val birthDate: Long,
    val phoneNumber: String,
    val address: String,
    val passportNumber: String,
    val passportSeries: String? = null,
    val whoCreatedId: String,
    val email: String,
    val password: String,
    val sex: Sex,
    val isClient: Boolean,
    val isOfficer: Boolean,
)
