package mobiler.abbosbek.mvvm.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import mobiler.abbosbek.mvvm.api.NetworkManager
import mobiler.abbosbek.mvvm.model.BaseResponse
import mobiler.abbosbek.mvvm.model.CategoryModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepository {
    var api = NetworkManager.getApiInstance()
    var compositeDisposable = CompositeDisposable()


    fun getCategories(
        progress : MutableLiveData<Boolean>,
        error : MutableLiveData<String>,
        success : MutableLiveData<List<CategoryModel>>
    ) {

        compositeDisposable.add(
            api.getCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    progress.value = true
                }
                .doFinally {
                    progress.value = false
                }
                .subscribeWith(object : DisposableObserver<BaseResponse<List<CategoryModel>>>(){
                    override fun onNext(t: BaseResponse<List<CategoryModel>>) {
                        if (t.success){
                            success.value = t.data
                        }else{
                            error.value = t.message
                        }
                    }

                    override fun onError(e: Throwable) {
                        error.value = e.localizedMessage
                    }

                    override fun onComplete() {
                    }
                })
        )
    }
}