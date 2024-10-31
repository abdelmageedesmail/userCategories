package com.abdelmageed.userCategories.domain.dto

data class BaseResponse<T>(
    val statusCode: Int,
    val data: T? = null,
    val message: String? = null
)

data class WrapperListResponse<T>(
    val statusCode: Int,
    val data: List<T>? = null,
    val message: String? = null
)