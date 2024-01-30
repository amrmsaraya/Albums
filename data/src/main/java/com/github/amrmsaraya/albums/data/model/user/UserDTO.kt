package com.github.amrmsaraya.albums.data.model.user


import com.github.amrmsaraya.albums.domain.entity.user.User
import com.squareup.moshi.Json

data class UserDTO(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "username")
    val username: String?,
    @Json(name = "email")
    val email: String?,
    @Json(name = "address")
    val address: AddressDTO?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "website")
    val website: String?,
    @Json(name = "company")
    val company: CompanyDTO?
) {
    fun toDomain(): User {
        return User(
            id = id ?: 0,
            name = name ?: "",
            username = username ?: "",
            email = email ?: "",
            address = address?.toDomain(),
            phone = phone ?: "",
            website = website ?: "",
            company = company?.toDomain()
        )
    }
}