package com.danieloliveira.clarochallenge.ui.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danieloliveira.clarochallenge.R
import com.danieloliveira.clarochallenge.enums.StringContants
import com.danieloliveira.clarochallenge.ui.adapters.MovieListAdapter
import com.danieloliveira.clarochallenge.utils.hideKeyboard
import com.danieloliveira.clarochallenge.utils.logIt
import com.danieloliveira.clarochallenge.viewmodel.MainActivityViewModel
import com.miguelcatalan.materialsearchview.MaterialSearchView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.material_searh_bar.*
import kotlinx.android.synthetic.main.navigation_view.*
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener,
    MaterialSearchView.OnQueryTextListener, CompoundButton.OnCheckedChangeListener {

    private val gridLayout: GridLayoutManager by inject()

    private val movieAdapter: MovieListAdapter by inject {
        parametersOf({ id: Int ->
            sendToDetailActivity(
                id
            )
        })
    }

    private val model: MainActivityViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioOptions.setOnCheckedChangeListener(this)

        setupToolbar()

        setupDrawerMenu()

        setupSearchBar()

        setupRecycler()

        observeData()

        model.fetchData()

    }

    private fun setupSearchBar() {
        searchView.setOnQueryTextListener(this)
    }

    private fun setupDrawerMenu() {
        val toogle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        adultContentSwitch.setOnCheckedChangeListener(this)

        adultContentSwitch.isChecked = model.isAdultContentEnabled()

        when(model.getTypeSearch()) {
            StringContants.POPULAR.const -> popular.isChecked = true
            StringContants.UPCOMING.const -> upComing.isChecked = true
            StringContants.TOP_RATED.const -> topRated.isChecked = true
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun observeData() {

        model.moviesData.observe(this@MainActivity, Observer {
            it?.let {
                movieAdapter.addMovieList(it)
                model.page = it.page?.plus(1)
                progressBar.visibility = View.GONE
                return@Observer
            }

            toast("Error -> Null Object")
        })
    }

    private fun setupRecycler() {

        recycler.apply {
            layoutManager = gridLayout
            adapter = movieAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (!recyclerView.canScrollVertically(1) && movieAdapter.itemCount > 0) {
                        model.fetchData()
                    }
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val item: MenuItem = menu!!.findItem(R.id.actionSearch)
        searchView.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.favoriteList -> {
                requestFavoriteMovies()
            }
        }
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
            return
        }
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
            return
        }
        super.onBackPressed()
    }

    override fun onCheckedChanged(radioGroup: RadioGroup?, radioButton: Int) {
        when (radioButton) {
            R.id.popular -> {
                requestMovies(typeSearch = StringContants.POPULAR, clearAdapter = true)
                model.setTypeSearch(StringContants.POPULAR.const)
            }
            R.id.topRated -> {
                requestMovies(typeSearch = StringContants.TOP_RATED, clearAdapter = true)
                model.setTypeSearch(StringContants.TOP_RATED.const)
            }
            R.id.upComing -> {
                requestMovies(typeSearch = StringContants.UPCOMING, clearAdapter = true)
                model.setTypeSearch(StringContants.UPCOMING.const)
            }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        "Query Text change".logIt()
        newText?.let {
            if (it.length > 3) {
                movieAdapter.clearMovieList()
                requestMovies(searchQuery = it)
                "onQueryTextChange".logIt()
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        "Query Summit".logIt()
        hideKeyboard(this)
        query?.let {
            movieAdapter.clearMovieList()
            requestMovies(searchQuery = it)
        }
        return true
    }

    private fun requestMovies(
        typeSearch: StringContants? = null, searchQuery: String? = null, clearAdapter: Boolean = false) {
        if (clearAdapter) movieAdapter.clearMovieList()

        progressBar.visibility = View.VISIBLE

        if(typeSearch != null) model.fetchData(typeSearch)

        if(searchQuery != null) model.fetchData(query = searchQuery)

    }

    private fun sendToDetailActivity(id: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(StringContants.MOVIE_ID.const, id)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        model.moviesData.removeObservers(this)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton?, changed: Boolean) {
        compoundButton?.let {
            model.enableAdultContent(it.isChecked)
        }
    }

    private fun requestFavoriteMovies() {
        movieAdapter.clearMovieList()
        progressBar.visibility = View.VISIBLE
        model.requestFavoriteMovies()
    }
}
