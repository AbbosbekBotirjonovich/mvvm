package mobiler.abbosbek.mvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobiler.abbosbek.mvvm.model.CategoryModel
import mobiler.abbosbek.mvvm.repository.MyRepository

class MainViewModel : ViewModel() {

    var repository = MyRepository()

    private var _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> get() {return _progress}

    private var _error = MutableLiveData<String>()
    val error : LiveData<String> get() {return _error}

    private var _categoryList = MutableLiveData<List<CategoryModel>>()
    val categoryList : LiveData<List<CategoryModel>> get() {return _categoryList}

    fun getCategories(){
        repository.getCategories(_progress,_error,_categoryList)
    }
}