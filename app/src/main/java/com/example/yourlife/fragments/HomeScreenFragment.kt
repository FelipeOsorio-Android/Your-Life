package com.example.yourlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yourlife.PreferenceKey
import com.example.yourlife.R
import com.example.yourlife.dataStore
import com.example.yourlife.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCalculate.isEnabled = false
        animation.setMaxProgress(0.65F)
        animation.playAnimation()

        var altura = ""
        var peso = ""

        editHeight.editText?.doOnTextChanged { text, _, _, _ ->
            text?.let {
                altura = it.toString()
            }

            btnCalculate.isEnabled = altura.isNotEmpty() && peso.isNotEmpty()
        }

        editWeight.editText?.doOnTextChanged { text, _, _, _ ->
            text?.let {
                peso = it.toString()
            }

            btnCalculate.isEnabled = altura.isNotEmpty() && peso.isNotEmpty()
        }


        btnCalculate.setOnClickListener {

            val userImc = peso.toInt()/altura.toDouble().pow(2)

            CoroutineScope(Dispatchers.IO).launch {
                saveUserImc(userImc)
            }

            findNavController().navigate(R.id.action_homeScreenFragment_to_resultScreenFragment)
        }
    }

    private suspend fun saveUserImc(userImc: Double) {
        binding.root.context.dataStore.edit {
            it[PreferenceKey.userImc] = userImc
        }
    }
}