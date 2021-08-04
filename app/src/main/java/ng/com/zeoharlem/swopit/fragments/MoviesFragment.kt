package ng.com.zeoharlem.swopit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ng.com.zeoharlem.swopit.R
import ng.com.zeoharlem.swopit.adapters.MoviesViewPagerAdapter
import ng.com.zeoharlem.swopit.adapters.PopularMoviesAdapter
import ng.com.zeoharlem.swopit.models.HomeDataSliderItems
import ng.com.zeoharlem.swopit.util.Constants
import ng.com.zeoharlem.swopit.util.NetworkResults
import ng.com.zeoharlem.swopit.viewmodels.MainViewModel

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var v: View
    private lateinit var homeViewPager: ViewPager2
    private lateinit var recyclerView: ShimmerRecyclerView
    private lateinit var sliderItems: ArrayList<HomeDataSliderItems>
    private lateinit var sliderAdapter: MoviesViewPagerAdapter
    private val popularMoviesAdapter by lazy { PopularMoviesAdapter() }
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v               = inflater.inflate(R.layout.fragment_movies, container, false)
        homeViewPager   = v.findViewById(R.id.home_view_pager)

        mainViewModel   = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        sliderInit()
        setItemSliderImage()

        //Set RecyclerView
        setUpRecyclerView()
        //Get Api Data
        requestApiData()
        return v
    }

    private fun requestApiData(){
        mainViewModel.getPopularMovies(applyQueries())
        mainViewModel.popularResponse.observe(viewLifecycleOwner, { response ->
            when(response){
                is NetworkResults.Success -> {
                    hideShimmerEffect()
                    response.data?.let{
                        popularMoviesAdapter.setData(it)
                    }
                }
                is NetworkResults.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(requireActivity(), response.message.toString(), Toast.LENGTH_LONG).show()
                }
                is NetworkResults.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }

    private fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String>    = HashMap()
        //queries["number"]   = "50"
        queries["api_key"]  = Constants.API_KEY
        return queries
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

    private fun setUpRecyclerView(){
        recyclerView                = v.findViewById<ShimmerRecyclerView>(R.id.movies_shimmer_recycler_view)
        recyclerView.layoutManager  = LinearLayoutManager(requireContext())
        recyclerView.adapter        = popularMoviesAdapter
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        recyclerView.hideShimmer()
    }
}