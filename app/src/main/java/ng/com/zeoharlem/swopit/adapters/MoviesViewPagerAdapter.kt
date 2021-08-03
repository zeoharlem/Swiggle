package ng.com.zeoharlem.swopit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import ng.com.zeoharlem.swopit.R
import ng.com.zeoharlem.swopit.models.HomeDataSliderItems

class MoviesViewPagerAdapter(val viewPager2: ViewPager2, val imageList: ArrayList<HomeDataSliderItems>): RecyclerView.Adapter<MoviesViewPagerAdapter.HomeSliderViewHolder>() {

    inner class HomeSliderViewHolder(var view: View): RecyclerView.ViewHolder(view){
        val imgItem = view.findViewById<ImageView>(R.id.view_pager_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSliderViewHolder {
        val layoutInflater  = LayoutInflater.from(parent.context)
        val view            = layoutInflater.inflate(R.layout.home_view_pager_item, parent, false)
        return HomeSliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeSliderViewHolder, position: Int) {
        val imageListing    = imageList[position]
        holder.imgItem.setImageResource(imageListing.imageItem)
        if(imageList.size == position){
            viewPager2.post(run)
        }
    }

    override fun getItemCount(): Int = imageList.size

    val run = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }

}