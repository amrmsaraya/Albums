package com.github.amrmsaraya.albums.data.model.user


import com.github.amrmsaraya.albums.domain.entity.user.Company
import com.squareup.moshi.Json

data class CompanyDTO(
    @Json(name = "name")
    val name: String?,
    @Json(name = "catchPhrase")
    val catchPhrase: String?,
    @Json(name = "bs")
    val bs: String?
) {
    fun toDomain(): Company {
        return Company(
            name = name ?: "",
            catchPhrase = catchPhrase ?: "",
            bs = bs ?: ""
        )
    }
}