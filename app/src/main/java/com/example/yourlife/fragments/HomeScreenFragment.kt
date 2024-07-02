package com.example.yourlife.fragments

import android.os.Bundle
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yourlife.R
import com.example.yourlife.databinding.FragmentHomeBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale
import kotlin.math.pow

class HomeScreenFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val animation by lazy {
        binding.fragHomeAnim
    }

    private val editHeight by lazy {
        binding.editTxtHeight
    }

    private val editWeight by lazy {
        binding.editTxtWeight
    }

    private val btnCalculate by lazy {
        binding.btnCalculate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        btnCalculate.isEnabled = false
        animation.setMaxProgress(0.65F)
        animation.playAnimation()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editHeight.editText?.doOnTextChanged { _, _, _, _ ->  enableButton() }

        editWeight.editText?.doOnTextChanged { _, _, _, _ -> enableButton() }

        btnCalculate.setOnClickListener {

            val bundle = bundleOf("userImc" to calculateImc())

            findNavController().navigate(
                R.id.action_homeScreenFragment_to_resultScreenFragment,
                args = bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun enableButton() {

        val altura = editHeight.editText?.text.toString()
        val peso = editWeight.editText?.text.toString()

        btnCalculate.isEnabled = altura.isNotEmpty() && peso.isNotEmpty()
    }

    private fun calculateImc(): String {

        val altura = editHeight.editText?.text.toString().toDouble()
        val peso = editWeight.editText?.text.toString().toInt()

        val userImc = peso/altura.pow(2)

        val formatter = DecimalFormat("#00.00",DecimalFormatSymbols.getInstance(Locale("pt", "br")))

        val userImcFormatter = formatter.format(userImc)

        return userImcFormatter
    }
}