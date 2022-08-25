package mobiler.abbosbek.mvvm.repository

import androidx.lifecycle.MutableLiveData
import mobiler.abbosbek.mvvm.api.NetworkManager
import mobiler.abbosbek.mvvm.model.BaseResponse
import mobiler.abbosbek.mvvm.model.CategoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository {
    var api = NetworkManager.getApiInstance()

    fun getCategories(progress : MutableLiveData<Boolean>,error : MutableLiveData<String>,succes : MutableLiveData<List<CategoryModel>>){
        progress.value = true
        api.getCategory().enqueue(object : Callback<BaseResponse<List<CategoryModel>>>{
            override fun onResponse(
                call: Call<BaseResponse<List<CategoryModel>>>,
                response: Response<BaseResponse<List<CategoryModel>>>,
            ) {
                progress.value = false
                if (response.isSuccessful){
                    if (response.body()!!.success){
                        succes.value = response.body()!!.data
                    }else{
                        error.value = response.body()!!.message
                    }
                }else{
                    error.value = response.message()
                }
            }

            override fun onFailure(call: Call<BaseResponse<List<CategoryModel>>>, t: Throwable) {
                progress.value = false
                error.value = t.localizedMessage
            }
        })
    }
}