package com.example.raspadinha_sorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.raspadinha_sorte.databinding.ActivityCadastroBinding
import com.example.raspadinha_sorte.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCadastroBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        auth = FirebaseAuth.getInstance()

        //botão voltar
        binding.buttonVoltar.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }
        // botão cadastrar
        binding.buttonCadastrar.setOnClickListener {

            val intent = Intent(this, PrincipalActivity::class.java)

            startActivity(intent)

            finish()
        }
    }


}