package com.example.platziconf.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.platziconf.R
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
//llamamos a la animacion
        val animacion = AnimationUtils.loadAnimation(this,R.anim.animacion)
        ivLogoPlatziConf.startAnimation(animacion)
        val intent =  Intent(this, MainActivity::class.java)
        //para cambiar a la pantalla pricipal necesitamos evento de la animacion
        animacion.setAnimationListener(object : Animation.AnimationListener{
            //se crean los metodos para saber si pasa algo en la animacion por ahora nos interesa el end
            override fun onAnimationStart(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                startActivity(intent)
                //destruir el activity
                finish()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }
        })
    }
}