package com.antoniocostadossantos.meuspontos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniocostadossantos.meuspontos.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat

const val POINTS_VALUE = 2.5

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.convertPoints.setOnClickListener {
            checkFields()
        }
    }

    private fun checkFields() {
        val real = binding.realInput.text.toString()
        val dolar = binding.dolarInput.text.toString()
        val points = binding.pointsInput.text.toString()

        when {
            real.isNullOrEmpty() -> {
                binding.realInput.error = "Preencha esse campo"
            }

            dolar.isNullOrEmpty() -> {
                binding.dolarInput.error = "Preencha esse campo"
            }

            points.isNullOrEmpty() -> {
                binding.pointsInput.error = "Preencha esse campo"
            }
            else -> {
                binding.realLabel.error = null
                binding.dolarLabel.error = null
                binding.pointsInput.error = null
                convertPoints()
            }
        }
    }

    private fun convertPoints() {
        val realPurchaseValue = binding.realInput.text.toString().toDouble()
        val dolarValue = binding.dolarInput.text.toString().toDouble()
        val multiplier = binding.pointsInput.text.toString().toDouble()

        val convertion = (realPurchaseValue / dolarValue) * multiplier

        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        val result = df.format(convertion).replace(",", ".")

        binding.convetionResult.text = getString(R.string.result_text, result)
    }
}