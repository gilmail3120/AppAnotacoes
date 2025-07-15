package com.example.appanotacoes.presentantion.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appanotacoes.presentantion.ui.fragment.PesquisaAnotacaoFragment
import com.example.appanotacoes.presentantion.ui.fragment.PrincipalFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val fragments = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PrincipalFragment()
            1 -> PesquisaAnotacaoFragment()
            else-> PrincipalFragment()
        }
    }

    override fun getItemCount(): Int {
        return fragments
    }

}