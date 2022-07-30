package com.example.raspadinha_sorte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.anupkumarpanwar.scratchview.ScratchView
import com.example.raspadinha_sorte.databinding.ActivityMainBinding
import com.example.raspadinha_sorte.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.dialog_view.view.*
import kotlin.random.Random


class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var auth: FirebaseAuth
    private var youWin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        auth = FirebaseAuth.getInstance()

        // logica raspagem 0
        scratch_view.setRevealListener(object : ScratchView.IRevealListener {
            override fun onRevealed(scratchView: ScratchView?) {

                resultado(1,randomSorte())
            }

            override fun onRevealPercentChangedListener(
                scratchView: ScratchView?,
                percent: Float
            ) {
            }
        })
        // logica raspagem 1
        scratch_view1.setRevealListener(object : ScratchView.IRevealListener {
            override fun onRevealed(scratchView1: ScratchView?) {
                resultado(2,randomSorte())
            }

            override fun onRevealPercentChangedListener(
                scratchView1: ScratchView?,
                percent: Float
            ) {
            }
        })
        // logica raspagem 2
        scratch_view2.setRevealListener(object : ScratchView.IRevealListener {
            override fun onRevealed(scratchView2: ScratchView?) {

                resultado(3,randomSorte())
            }

            override fun onRevealPercentChangedListener(
                scratchView2: ScratchView?,
                percent: Float
            ) {
            }
        })
        // logica raspagem 3
        scratch_view3.setRevealListener(object : ScratchView.IRevealListener {
            override fun onRevealed(scratchView3: ScratchView?) {
                resultado(4,randomSorte())
            }

            override fun onRevealPercentChangedListener(
                scratchView3: ScratchView?,
                percent: Float
            ) {
            }
        })

        //botão sair
        binding.botaosair.setOnClickListener {
            auth.signOut()

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)

            finish()
        }

    }

    private fun showDialog() {
        val view = View.inflate(this@PrincipalActivity, R.layout.dialog_view, null)

        val builder = AlertDialog.Builder(this@PrincipalActivity)
        builder.setView(view)

        val dialog = builder.create()

        // image 0
        iv_premium.setImageResource(
            if (youWin) R.drawable.img_win
            else R.drawable.img_lose
        )
        // image 1
        iv_premium1.setImageResource(
            if (youWin) R.drawable.img_win
            else R.drawable.img_lose
        )
        // image 2
        iv_premium2.setImageResource(
            if (youWin) R.drawable.img_win
            else R.drawable.img_lose
        )
        // image 3
        iv_premium3.setImageResource(
            if (youWin) R.drawable.img_win
            else R.drawable.img_lose
        )

        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)


        view.title.text = if (youWin) "Parabéns" else "Não foi dessa vez :("
        view.subtitle.text = if (youWin) "Você ganhou uma casa!" else "Tente novamente"

        view.iv_status.background = if (youWin) ContextCompat.getDrawable(this, R.drawable.bg_iv)
        else ContextCompat.getDrawable(this, R.drawable.bg_iv_lose)

        view.iv_status.setImageResource(
            if (youWin) R.drawable.ic_check
            else R.drawable.ic_close_black_24dp
        )

        view.btn_confirm.setBackgroundColor(
            if (youWin) ContextCompat.getColor(this, R.color.colorGreen)
            else ContextCompat.getColor(this, R.color.colorRed)
        )

        view.btn_confirm.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun randomSorte(): Int {
        var escolha = (0..1).random()
        return escolha

    }

    fun resultado(parametro: Int, telas: Int) {
        val view = View.inflate(this@PrincipalActivity, R.layout.dialog_view, null)

        val builder = AlertDialog.Builder(this@PrincipalActivity)
        builder.setView(view)

        val dialog = builder.create()

        // image 0
        iv_premium.setImageResource(
            if (parametro == 1) R.drawable.img_win
            else R.drawable.img_lose
        )
        // image 1
        iv_premium1.setImageResource(
            if (parametro == 1) R.drawable.img_win
            else R.drawable.img_lose
        )
        // image 2
        iv_premium2.setImageResource(
            if (parametro == 1) R.drawable.img_win
            else R.drawable.img_lose
        )
        // image 3
        iv_premium3.setImageResource(
            if (parametro == 1) R.drawable.img_win
            else R.drawable.img_lose
        )
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)

        if (telas != null) {
            if (telas == 0 && parametro != null) {
                if (telas == 0) {
                    view.title.text = if (parametro == 1) "Parabéns" else "Não foi dessa vez :("
                    view.subtitle.text =
                        if (parametro == 1) "Você ganhou uma casa!" else "Tente novamente"
                    view.iv_status.background =
                        if (parametro == 1) ContextCompat.getDrawable(this, R.drawable.bg_iv)
                        else ContextCompat.getDrawable(this, R.drawable.bg_iv_lose)

                    view.iv_status.setImageResource(
                        if (parametro ==1) R.drawable.ic_check
                        else R.drawable.ic_close_black_24dp
                    )

                    view.btn_confirm.setBackgroundColor(
                        if (parametro ==1) ContextCompat.getColor(this, R.color.colorGreen)
                        else ContextCompat.getColor(this, R.color.colorRed)
                    )
                }
            }
            else if (telas == 1 && parametro != null) {
                if (telas == 1) {
                    view.title.text = if (parametro == 1) "Parabéns" else "Não foi dessa vez :("
                    view.subtitle.text =
                        if (parametro == 1) "Você ganhou uma casa!" else "Tente novamente"

                    view.iv_status.background =
                        if (parametro == 1) ContextCompat.getDrawable(this, R.drawable.bg_iv)
                        else ContextCompat.getDrawable(this, R.drawable.bg_iv_lose)

                    view.iv_status.setImageResource(
                        if (parametro ==1) R.drawable.ic_check
                        else R.drawable.ic_close_black_24dp
                    )

                    view.btn_confirm.setBackgroundColor(
                        if (parametro ==1) ContextCompat.getColor(this, R.color.colorGreen)
                        else ContextCompat.getColor(this, R.color.colorRed)
                    )
                }
            }
            else if (telas == 2 && parametro != null) {
                if (telas == 2) {
                    view.title.text = if (parametro == 1) "Parabéns" else "Não foi dessa vez :("
                    view.subtitle.text =
                        if (parametro == 1) "Você ganhou uma casa!" else "Tente novamente"

                    view.iv_status.background =
                        if (parametro == 1) ContextCompat.getDrawable(this, R.drawable.bg_iv)
                        else ContextCompat.getDrawable(this, R.drawable.bg_iv_lose)

                    view.iv_status.setImageResource(
                        if (parametro ==1) R.drawable.ic_check
                        else R.drawable.ic_close_black_24dp
                    )

                    view.btn_confirm.setBackgroundColor(
                        if (parametro ==1) ContextCompat.getColor(this, R.color.colorGreen)
                        else ContextCompat.getColor(this, R.color.colorRed)
                    )

                }
            }
            else if (telas == 3 && parametro != null) {
                if (telas == 3) {
                    view.title.text = if (parametro == 1) "Parabéns" else "Não foi dessa vez :("
                    view.subtitle.text =
                        if (parametro == 1) "Você ganhou uma casa!" else "Tente novamente"

                    view.iv_status.background =
                        if (parametro == 1) ContextCompat.getDrawable(this, R.drawable.bg_iv)
                        else ContextCompat.getDrawable(this, R.drawable.bg_iv_lose)

                    view.iv_status.setImageResource(
                        if (parametro ==1) R.drawable.ic_check
                        else R.drawable.ic_close_black_24dp
                    )

                    view.btn_confirm.setBackgroundColor(
                        if (parametro ==1) ContextCompat.getColor(this, R.color.colorGreen)
                        else ContextCompat.getColor(this, R.color.colorRed)
                    )

                }
            } else {
                print("tente novamente mais tarde")
            }

        }
        view.btn_confirm.setOnClickListener {
            dialog.dismiss()
        }
    }
}