package com.example.yourlife.fragments

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.yourlife.R
import com.example.yourlife.databinding.FragmentSplashBinding

class SplashFragment : Fragment(), AnimatorListener {

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

        splashAnimation.playAnimation()
        splashAnimation.addAnimatorListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAnimationStart(animation: Animator) {}

    override fun onAnimationEnd(animation: Animator) {
        findNavController().navigate(R.id.action_splashFragment_to_homeScreenFragment)
    }

    override fun onAnimationCancel(animation: Animator) {}

    override fun onAnimationRepeat(animation: Animator) {}
}

