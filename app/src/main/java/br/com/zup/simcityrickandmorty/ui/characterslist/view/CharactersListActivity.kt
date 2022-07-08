package br.com.zup.simcityrickandmorty.ui.characterslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.zup.simcityrickandmorty.databinding.ActivityCharactersListBinding

class CharactersListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharactersListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersListBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}