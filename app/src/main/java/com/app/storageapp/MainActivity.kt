package com.app.storageapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.storageapp.databinding.ActivityMainBinding
import com.app.storageapp.filtersettings.FilterActivity
import com.app.storageapp.models.Car
import com.app.storageapp.recyclerview.ItemAdapter
import com.app.storageapp.settings.SettingsActivity

class MainActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val listAdapter = ItemAdapter(this)
    private val list = mutableListOf(
        Car(1, "Honda", 1994, "NSX-S"),
        Car(2, "Honda", 1995, "NSX"),
        Car(3, "Honda", 1996, "NSX"),
        Car(4, "Honda", 1997, "NSX-T"),
        Car(5, "Honda", 1998, "NSX-R"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
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
            super.onBackPressed();
        }
    }

    private fun initViews() {
        setUpActionBar()

        val recyclerView = binding.recyclerView
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter.also { it.submitList(list) }
        }


        binding.buttonAdd.setOnClickListener {
            val addFragment = AddFragment()
            //addFragment.setFragmentResultListener()
            addFragment.show(supportFragmentManager, null)
        }
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarMain.root)
        supportActionBar?.setIcon(R.drawable.ic_car)
        supportActionBar?.title = "Cars"
    }

    override fun onItemClick(position: Int) {
        val data = list[position].BuildDataString()
        val detailFragment = DetailFragment.newInstance(data)
        detailFragment.show(supportFragmentManager, null)
    }
}