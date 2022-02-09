package com.app.storageapp.filtersettings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.app.storageapp.R

class FilterFragment : PreferenceFragmentCompat() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.filter)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }
}