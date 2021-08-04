package ng.com.zeoharlem.swopit.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ng.com.zeoharlem.swopit.databinding.PopularMovieItemBinding
import ng.com.zeoharlem.swopit.models.Popular
import ng.com.zeoharlem.swopit.models.PopularResponse
import ng.com.zeoharlem.swopit.util.PopularDiffUtil

class PopularMoviesAdapter: RecyclerView.Adapter<PopularMoviesAdapter.MyPopularMovieViewHolder>() {

    private var popularList = emptyList<Popular>()

    class MyPopularMovieViewHolder(private val binding: PopularMovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(popular: Popular){
            binding.popular = popular
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyPopularMovieViewHolder {
                val layoutInflater  = LayoutInflater.from(parent.context)
                val binding         = PopularMovieItemBinding.inflate(layoutInflater, parent, false)
                return MyPopularMovieViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPopularMovieViewHolder {
        return MyPopularMovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyPopularMovieViewHolder, position: Int) {
        val currentPopular   = popularList[position]
        holder.bind(currentPopular)
    }

    override fun getItemCount(): Int {
        return popularList.size
    }

    fun setData(newData: PopularResponse){
        val popularDiffUtil = PopularDiffUtil(popularList, newData.populars)
        val diffUtilPopular = DiffUtil.calculateDiff(popularDiffUtil)
        popularList = newData.populars
        diffUtilPopular.dispatchUpdatesTo(this)
    }
}