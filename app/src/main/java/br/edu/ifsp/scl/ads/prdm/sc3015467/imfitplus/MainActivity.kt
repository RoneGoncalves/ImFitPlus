package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        amb.startBt.setOnClickListener {
            val intent = Intent(this,  PersonalDataActivity::class.java);
            startActivity(intent);
        }
    }
}