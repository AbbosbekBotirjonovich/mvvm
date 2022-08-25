package mobiler.abbosbek.mvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mobiler.abbosbek.mvvm.databinding.CategoryItemLayoutBinding
import mobiler.abbosbek.mvvm.model.CategoryModel

class CategoryAdapter(val items : List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {
    inner class ItemHolder(val binding: CategoryItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        Glide.with(holder.binding.root.context)
            .load("http://osonsavdo.herokuapp.com/images/${item.icon}")
            .into(holder.binding.imgCategory)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}