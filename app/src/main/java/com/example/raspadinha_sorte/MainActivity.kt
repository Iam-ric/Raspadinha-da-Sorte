package com.example.raspadinha_sorte


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.JsonToken
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.raspadinha_sorte.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)


        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("454178112414-dn4gqesgs62b8l9he4e2q7h39aomn5ra.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.botaoEntrar.setOnClickListener {
            try {
                auth.signInWithEmailAndPassword(
                    binding.editTextTextUsuario.text.toString(),
                    binding.editTextSenha.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            var user: auth.currentUser //updateUI(user)

                            abrePrincipal()

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            Toast.makeText(
                                baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show() //updateUI(null)
                        } // ...
                    }
            } catch (e: Exception) {

                Toast.makeText(
                    baseContext, "Digite usuario e senha",
                    Toast.LENGTH_SHORT
                ).show() //updateUI(user)
            }
        }

        binding.botaoGoogle.setOnClickListener {
            sigIn()
        }

        binding.textCadastro.setOnClickListener{

            val intent = Intent(this, CadastroActivity::class.java)

            startActivity(intent)
        }

    }

    private fun sigIn() {
        val intent = googleSignInClient.signInIntent
        abreActivity.launch(intent)
    }

    var abreActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            val intent = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            try {
                val conta = task.getResult(ApiException::class.java)
                if (conta != null) {
                    conta.idToken?.let { loginComGoogle(it) }
                }

            } catch (exception: ApiException) {


            }
        }
    }

    private fun loginComGoogle(token: String) {
        val credencial = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credencial).addOnCompleteListener(this)
        { task: Task<AuthResult> ->

            if (task.isSuccessful) {
                val currentUser = auth.currentUser

                if (currentUser != null) {
                    Toast.makeText(
                        baseContext, "Usuario." + currentUser.email + "logado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                abrePrincipal()

            } else {

                Toast.makeText(
                    baseContext, "invalido",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }

    fun criarUsuarioESenha(email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha)
    }


    fun abrePrincipal() {

        Toast.makeText(baseContext, "Authentication.", Toast.LENGTH_SHORT).show()

        binding.editTextTextUsuario.text.clear()
        binding.editTextSenha.text.clear()

        val intent = Intent(this, PrincipalActivity::class.java)

        startActivity(intent)

        finish()

    }


    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        if (currentUser != null) {
            if (currentUser.email?.isNotEmpty() == true) {
                Toast.makeText(
                    baseContext, "Usuario." + currentUser.email + "logado",
                    Toast.LENGTH_SHORT
                ).show() //updateU
                abrePrincipal()
            }
        }
        //updateUI(currentUser)
    }

}

class auth {
    class currentUser {
    }
}
