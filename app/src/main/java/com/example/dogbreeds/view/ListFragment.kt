package com.example.dogbreeds.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.dogbreeds.R
import com.example.dogbreeds.model.DogBreed
import com.example.dogbreeds.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogsListAdapter = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //creates menu
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        dogsList.apply {
            //order element sequentially
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        refreshLayout.setOnRefreshListener {
            dogsList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.dogs.observe(this, Observer { dogs: List<DogBreed> ->
            dogs?.let{
                dogsListAdapter.updateDogList(dogs)
                dogsList.visibility = View.VISIBLE
            }
        })

        viewModel.dogsLoadError.observe(this, Observer {isError: Boolean ->
            isError?.let{
                listError.visibility = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer{isLoading: Boolean ->
            isLoading?.let {
                loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    listError.visibility = View.GONE
                    dogsList.visibility = View.GONE
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionSettings -> {
                view?.let { Navigation.findNavController(it).navigate(ListFragmentDirections.actionSettings())}
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
