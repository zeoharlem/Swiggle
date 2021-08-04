package ng.com.zeoharlem.swopit.models


import com.google.gson.annotations.SerializedName

data class PopularResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val populars: List<Popular>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)