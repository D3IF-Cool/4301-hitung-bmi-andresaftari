package org.d3if3049.hitungbmi

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.d3if3049.hitungbmi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener { calculateBMI() }
        binding.btnReset.setOnClickListener { resetData() }
    }

    private fun calculateBMI() {
        val berat = binding.edtBerat.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(this, R.string.berat_invalid, Toast.LENGTH_LONG).show()
            binding.edtBerat.requestFocus()
            return
        }

        val tinggi = binding.edtTinggi.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(this, R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            binding.edtTinggi.requestFocus()
            return
        }

        val tinggiCm = tinggi.toFloat() / 100
        val selectedId = binding.rgGender.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, R.string.gender_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val isMale = selectedId == R.id.rb_pria
        val bmi = berat.toFloat() / (tinggiCm * tinggiCm)
        val kategori = getKategori(bmi, isMale)

        with(binding) {
            bmiTextView.text = getString(R.string.bmi_x, bmi)
            kategoriTextView.text = getString(R.string.kategori_x, kategori)
        }

        Log.d(CALCULATION_TAG, "$bmi, $kategori")
    }

    private fun resetData() = with(binding) {
        edtBerat.text.clear()
        rbPria.isChecked = false
        rbWanita.isChecked = false
        edtTinggi.text.clear()
        bmiTextView.text = ""
        kategoriTextView.text = ""

        Log.d(RESET_DATA_TAG, "$edtBerat, $edtTinggi")
    }

    private fun getKategori(bmi: Float, isMale: Boolean): String {
        val stringRes = if (isMale) when {
            bmi < 20.5 -> R.string.kurus
            bmi >= 27.0 -> R.string.gemuk
            else -> R.string.ideal
        }
        else when {
            bmi < 18.5 -> R.string.kurus
            bmi >= 25.0 -> R.string.gemuk
            else -> R.string.ideal
        }
        return getString(stringRes)
    }

    companion object {
        const val CALCULATION_TAG = "calculateBMI()"
        const val RESET_DATA_TAG = "resetData()"
    }
}