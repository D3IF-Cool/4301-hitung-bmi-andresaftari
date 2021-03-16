package org.d3if3049.hitungbmi.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.d3if3049.hitungbmi.R
import org.d3if3049.hitungbmi.databinding.FragmentHitungBinding

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(layoutInflater, container, false)

        binding.btnHitung.setOnClickListener { hitungBmi() }
        return binding.root
    }

    private fun hitungBmi() {
        val berat = binding.edtBerat.text.toString()
        if (TextUtils.isEmpty(berat)) {
            Toast.makeText(requireContext(), R.string.berat_invalid, Toast.LENGTH_LONG).show()
            binding.edtBerat.requestFocus()
            return
        }

        val tinggi = binding.edtTinggi.text.toString()
        if (TextUtils.isEmpty(tinggi)) {
            Toast.makeText(requireContext(), R.string.tinggi_invalid, Toast.LENGTH_LONG).show()
            binding.edtTinggi.requestFocus()
            return
        }

        val tinggiCm = tinggi.toFloat() / 100
        val selectedId = binding.rgGender.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(requireContext(), R.string.gender_invalid, Toast.LENGTH_LONG).show()
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
        const val CALCULATION_TAG = "Check Calculation: "
    }
}