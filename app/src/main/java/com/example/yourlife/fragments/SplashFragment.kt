package com.example.yourlife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.example.yourlife.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    private val splashAnimation by lazy {
        binding.fragSplashAnimation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onPlayAnimation(splashAnimation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onPlayAnimation(view: LottieAnimationView) {
        view.playAnimation()

        view.addAnimatorUpdateListener {
            it.doOnEnd {
                findNavController().navigate("HomeScreen")
            }
        }
    }

}

