package mobiler.abbosbek.mvvm.api

import io.reactivex.Observable
import mobiler.abbosbek.mvvm.model.BaseResponse
import mobiler.abbosbek.mvvm.model.CategoryModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("get_categories")
    fun getCategory() : Observable<BaseResponse<List<CategoryModel>>>
}