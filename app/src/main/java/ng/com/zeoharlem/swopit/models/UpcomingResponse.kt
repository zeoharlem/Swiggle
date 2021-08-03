package ng.com.zeoharlem.swopit.models


import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("populars")
    val results: List<Upcoming>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)