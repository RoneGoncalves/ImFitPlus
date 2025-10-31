package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.databinding.ActivityHealthReportBinding

class HealthReportActivity : AppCompatActivity() {
    private val ahrb: ActivityHealthReportBinding by lazy { 
        ActivityHealthReportBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ahrb.root)



    }
}