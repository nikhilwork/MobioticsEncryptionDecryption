package com.mobiotics.encyption.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mobiotics.encyption.R
import com.mobiotics.encyption.utils.AppConstants
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnEncrypt.setOnClickListener(){
            callEncryptedActivity()
        }

        btnDecrypt.setOnClickListener(){
            callDecryptedActivity()
        }
    }

    fun callEncryptedActivity(){
        val intent = Intent(this@HomeActivity, EncryptionDecryptionActivity::class.java)
        intent.putExtra("caller_type", AppConstants.TYPE_ENCRYPT)
        startActivity(intent)
    }

    fun callDecryptedActivity(){
        val intent = Intent(this@HomeActivity, EncryptionDecryptionActivity::class.java)
        intent.putExtra("caller_type", AppConstants.TYPE_DECRYPT)
        startActivity(intent)
    }
}
