package mobiler.abbosbek.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import mobiler.abbosbek.mvvm.adapter.CategoryAdapter
import mobiler.abbosbek.mvvm.databinding.ActivityMainBinding
import mobiler.abbosbek.mvvm.model.CategoryModel
import mobiler.abbosbek.mvvm.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        viewModel.progress.observe(this,Observer{
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.error.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.categoryList.observe(this){
            setCategoryData(it)
        }

        loadData()

    }

    private fun setCategoryData(items : List<CategoryModel>){
        binding.recycler.layoutManager = GridLayoutManager(this,2)
        binding.recycler.adapter = CategoryAdapter(items)
    }

    private fun loadData(){
        viewModel.getCategories()
    }
}