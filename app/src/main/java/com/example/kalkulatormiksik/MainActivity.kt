package com.example.kalkulatormiksik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import com.example.kalkulatormiksik.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDarkTheme = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isDarkTheme = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        binding.apply {
            button.setOnClickListener {
                val df = DecimalFormat("#.##")
                df.roundingMode = RoundingMode.CEILING
                setTheme(R.style.Theme_MaterialComponents_Light)
                if (weight.text.isEmpty() || height.text.isEmpty()) {
                    bmi.text = getString(R.string.submitError)
                    return@setOnClickListener
                } else {
                    val weightVal = weight.text.toString().toDouble()
                    val heightVal = height.text.toString().toDouble()
                    val bmiVal = weightVal / (heightVal * heightVal) * 10000
                    print(bmiVal)
                    bmi.text = df.format(bmiVal)
                }
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val nightMode = menu?.findItem(R.id.themeSwitch)
        nightMode?.title =
            if (isDarkTheme) getString(R.string.switchToLightTheme) else getString(R.string.switchToDarkTheme)
        nightMode?.setOnMenuItemClickListener {
            isDarkTheme = !isDarkTheme
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                nightMode.title = getString(R.string.switchToLightTheme)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
            true
        }

        return true
    }

}
