package com.example.ilabankassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ilabankassignment.adapter.CarouselItemAdapter
import com.example.ilabankassignment.adapter.ListItemAdapter
import com.example.ilabankassignment.databinding.ActivityMainBinding
import com.example.ilabankassignment.model.CarouselAndListItemModel
import com.example.ilabankassignment.model.ListItemModel
import com.example.ilabankassignment.repository.DataRepository
import com.example.ilabankassignment.util.ICommonMethods
import com.example.ilabankassignment.util.Status
import com.example.ilabankassignment.viewmodel.CarouselAndListViewModel
import com.example.ilabankassignment.viewmodel.MainViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs

class MainActivity : AppCompatActivity(), ICommonMethods {

    private lateinit var viewModel: CarouselAndListViewModel
    private lateinit var carouselItemAdapter: CarouselItemAdapter
    private lateinit var listItemAdapter: ListItemAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialSetUp()
        callDataService()
        setObserver()
    }

    override fun initialSetUp() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(DataRepository())
        )[CarouselAndListViewModel::class.java]
    }

    override fun callDataService() {
        //To be used for fetching any remote or local data
    }

    override fun setObserver() {
        viewModel.listLiveData.observe(this,
            Observer {
                when (it.status) {
                    Status.LOADING -> {
                        Log.d("setObserver", "LOADING")
                    }

                    Status.SUCCESS -> {
                        Log.d("setObserver", "SUCCESS")
                        it.data?.let { carouselAndListItemModelResponse ->
                            if (carouselAndListItemModelResponse.isNotEmpty()) {
                                setCarouselViewPagerData(
                                    carouselAndListItemModelResponse
                                )
                                setListItemData(carouselAndListItemModelResponse[0].listItems)
                            } else {
                                showNoDataFound(getString(R.string.no_data_found))
                            }
                        } ?: run {
                            showNoDataFound(getString(R.string.no_data_found))
                        }
                    }

                    Status.ERROR -> {
                        Log.d("setObserver", "ERROR")
                    }
                }
            })
    }

    override fun setOnClickListeners() {
        TODO("Not yet implemented")
    }


    private fun setCarouselViewPagerData(
        carouselAndListItemModel: List<CarouselAndListItemModel>
    ) {
        carouselItemAdapter =
            CarouselItemAdapter(carouselAndListItemModel)
        binding.vpCarousel.adapter = carouselItemAdapter

        binding.vpCarousel.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setListItemData(carouselAndListItemModel[position].listItems)
                searchFilterData(carouselAndListItemModel[position].listItems)
            }
        })

        setCustomItemWidth()

        //To display pager indicator
        TabLayoutMediator(binding.tabsIndicator, binding.vpCarousel) { _, _ ->
        }.attach()
    }

    private fun setCustomItemWidth() {
        binding.vpCarousel.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * abs(position))
            page.alpha = 0.5f + (1 - abs(position))
        }
        binding.vpCarousel.setPageTransformer(pageTransformer)

    }

    fun setListItemData(verticalListModel: List<ListItemModel>) {
        listItemAdapter = ListItemAdapter(verticalListModel)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listItemAdapter
            isNestedScrollingEnabled = true
        }
    }

    private fun showNoDataFound(errorText: String) {
        binding.tvNoData.visibility = View.VISIBLE
        binding.tvNoData.text = errorText
    }

    private fun hideNoDataFound() {
        binding.tvNoData.visibility = View.GONE
    }

    fun searchFilterData(verticalListModel: List<ListItemModel>) {
        val filterListData = ArrayList<ListItemModel>()
        clearSearch(verticalListModel)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(inputText: String?): Boolean {
                filterListData.clear()
                if (verticalListModel.isNotEmpty()) {
                    val data = verticalListModel.filter { item: ListItemModel ->
                        item.title.lowercase().contains(inputText?.lowercase().toString())
                    }
                    if (data.isEmpty()) {
                        showNoDataFound(getString(R.string.no_search_data_found))
                    } else {
                        hideNoDataFound()
                        filterListData.addAll(data)
                    }
                }
                setListItemData(filterListData)
                return false
            }
        })

        //Display close button on search bar
        val closeButtonId = resources.getIdentifier(
            "android:id/search_close_btn", null, null
        )
        val closeButtonImage = binding.searchView.findViewById(closeButtonId) as ImageView
        closeButtonImage.setImageResource(R.drawable.ic_close)

        closeButtonImage.setOnClickListener {
            clearSearch(verticalListModel)
            closeButtonImage.visibility = View.GONE
        }
    }

    private fun clearSearch(verticalListModel: List<ListItemModel>) {
        binding.searchView.setQuery("", true)
        binding.searchView.clearFocus()
        setListItemData(verticalListModel)
    }

}