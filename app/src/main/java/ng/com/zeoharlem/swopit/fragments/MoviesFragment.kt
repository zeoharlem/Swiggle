package ng.com.zeoharlem.swopit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import ng.com.zeoharlem.swopit.R
import ng.com.zeoharlem.swopit.adapters.MoviesViewPagerAdapter
import ng.com.zeoharlem.swopit.models.HomeDataSliderItems
import java.lang.Math.abs


class MoviesFragment : Fragment() {

    private lateinit var homeViewPager: ViewPager2
    private lateinit var sliderItems: ArrayList<HomeDataSliderItems>
    private lateinit var sliderAdapter: MoviesViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v           = inflater.inflate(R.layout.fragment_movies, container, false)
        homeViewPager   = v.findViewById(R.id.home_view_pager)

        sliderInit()
        setItemSliderImage()

        return v
    }

    private fun sliderInit(){
        sliderItems     = ArrayList()
        sliderAdapter   = MoviesViewPagerAdapter(homeViewPager, sliderItems)
        homeViewPager.adapter   = sliderAdapter
        homeViewPager.clipToPadding = false
        homeViewPager.clipChildren  = false
        homeViewPager.offscreenPageLimit    = 3
        homeViewPager.getChildAt(0).overScrollMode  = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer: CompositePageTransformer  = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val f: Float    = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + f * 0.15f
        }
        homeViewPager.setPageTransformer(compositePageTransformer)
    }

    private fun setItemSliderImage(){
        sliderItems.add(HomeDataSliderItems(R.drawable.slide1))
        sliderItems.add(HomeDataSliderItems(R.drawable.slide2))
    }
}