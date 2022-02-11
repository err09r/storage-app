package com.app.storageapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.storageapp.constants.Constants.ADD_FRAGMENT_KEY
import com.app.storageapp.constants.Constants.DELETE_CAR
import com.app.storageapp.constants.Constants.DETAIL_FRAGMENT_KEY
import com.app.storageapp.constants.Constants.KEY_MODEL
import com.app.storageapp.constants.Constants.KEY_PRODUCER
import com.app.storageapp.constants.Constants.KEY_YEAR
import com.app.storageapp.constants.Constants.UPDATE_CAR
import com.app.storageapp.databinding.ActivityMainBinding
import com.app.storageapp.filtersettings.FilterActivity
import com.app.storageapp.models.Car
import com.app.storageapp.recyclerview.ItemAdapter
import com.app.storageapp.settings.SettingsActivity
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val listAdapter = ItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContentView(binding.root)
        initViews()
        viewModel.carList.observe(this) { carList ->
            listAdapter.submitList(carList)
        }

        supportFragmentManager.setFragmentResultListener(
            ADD_FRAGMENT_KEY,
            this
        ) { _, bundle ->
            val producer = bundle.getString(KEY_PRODUCER) ?: ""
            val year = bundle.getString(KEY_YEAR)?.toIntOrNull() ?: 0
            val model = bundle.getString(KEY_MODEL) ?: ""
            val car = Car(producer = producer, year = year, model = model)
            viewModel.insert(car)
        }

        supportFragmentManager.setFragmentResultListener(
            DETAIL_FRAGMENT_KEY,
            this
        ) { _, bundle ->
            bundle.getSerializable(UPDATE_CAR)?.let {
                viewModel.update(it as Car)
            }
            bundle.getSerializable(DELETE_CAR)?.let {
                viewModel.delete(it as Car)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val value = prefs.getString(getString(R.string.filter_list_key), DEFAULT_ORDER)?.toInt()
        viewModel.order.value = value
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.filter -> {
                val intent = Intent(this, FilterActivity::class.java)
                startActivity(intent)
            }
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            fm.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun initViews() {
        setUpActionBar()
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
        binding.buttonAdd.setOnClickListener {
            val addFragment = AddFragment()
            addFragment.show(supportFragmentManager, null)
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarMain.root)
        supportActionBar?.setIcon(R.drawable.ic_car)
        supportActionBar?.title = getString(R.string.text_screen_main)
    }

    override fun onItemClick(id: Int) {
        lifecycleScope.launch {
            val car = viewModel.getCarById(id)
            val detailFragment = DetailFragment.newInstance(car)
            detailFragment.show(supportFragmentManager, null)
        }
    }

    private companion object {
        private const val DEFAULT_ORDER = "0"
    }
}