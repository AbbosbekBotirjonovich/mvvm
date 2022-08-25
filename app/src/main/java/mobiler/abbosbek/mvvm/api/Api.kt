package mobiler.abbosbek.mvvm.api

import mobiler.abbosbek.mvvm.model.BaseResponse
import mobiler.abbosbek.mvvm.model.CategoryModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("get_categories")
    fun getCategory() : Call<BaseResponse<List<CategoryModel>>>
}