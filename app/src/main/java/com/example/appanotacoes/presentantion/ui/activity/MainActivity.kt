package com.example.appanotacoes.presentantion.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.appanotacoes.R
import com.example.appanotacoes.databinding.ActivityMainBinding
import com.example.appanotacoes.presentantion.adapter.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        eventoClique()
        navegacaoViewPager()
    }

    private fun navegacaoViewPager() {
        viewPager = binding.viewPager
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
        //viewPager.isUserInputEnabled=true
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.paginaAnotacao -> {
                    viewPager.setCurrentItem(0, true);true
                }
                R.id.pesquisaAnotacao -> {
                    viewPager.setCurrentItem(1, true);true
                }
                else -> false
            }
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun eventoClique() {
        binding.fbCreateAnotacao.setOnClickListener {
            val intent = Intent(this, CriacaoAnotacaoActivity::class.java)
            startActivity(intent)
        }
    }
}