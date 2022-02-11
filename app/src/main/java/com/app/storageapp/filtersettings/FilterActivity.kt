package com.app.storageapp.filtersettings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.storageapp.R
import com.app.storageapp.databinding.ActivityFilterBinding

class FilterActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFilterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.filter_fragment_container, FilterFragment())
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbarFilter.root)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.text_screen_sort)
        }
    }
}