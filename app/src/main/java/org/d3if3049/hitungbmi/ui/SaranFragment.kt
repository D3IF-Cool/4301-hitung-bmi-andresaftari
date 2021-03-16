package org.d3if3049.hitungbmi.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.d3if3049.hitungbmi.R
import org.d3if3049.hitungbmi.data.KategoriBmi
import org.d3if3049.hitungbmi.databinding.FragmentSaranBinding

class SaranFragment : Fragment() {
    private lateinit var binding: FragmentSaranBinding
    private val args: SaranFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaranBinding.inflate(layoutInflater, container, false)
        updateUI(args.kategori)
        return binding.root
    }

    private fun updateUI(kategori: KategoriBmi) {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        when (kategori) {
            KategoriBmi.KURUS -> {
                actionBar?.title = getString(R.string.judul_kurus)
                with(binding) {
                    ivBmi.setImageResource(R.drawable.kurus)
                    tvSaran.text = getString(R.string.saran_kurus)
                }
            }
            KategoriBmi.IDEAL -> {
                actionBar?.title = getString(R.string.judul_ideal)
                with(binding) {
                    ivBmi.setImageResource(R.drawable.ideal)
                    tvSaran.text = getString(R.string.saran_ideal)
                }
            }
            KategoriBmi.GEMUK -> {
                actionBar?.title = getString(R.string.judul_gemuk)
                with(binding) {
                    ivBmi.setImageResource(R.drawable.gemuk)
                    tvSaran.text = getString(R.string.saran_gemuk)
                }
            }
        }
    }
}