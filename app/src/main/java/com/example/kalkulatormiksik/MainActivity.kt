package com.example.kalkulatormiksik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet.VISIBLE
import com.example.kalkulatormiksik.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle("BMI Calculator")
        binding.button.setOnClickListener {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            val weight = binding.weight?.text.toString().toDouble()
            val height = binding.height?.text.toString().toDouble()

            val bmi = (weight / (height * height)*10000)
            binding.bmi?.text = df.format(bmi)
        }

    }
}