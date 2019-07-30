package com.mobiotics.encyption.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.mobiotics.encyption.R
import com.mobiotics.encyption.utils.AppConstants
import com.mobiotics.encyption.utils.CommonUtils
import kotlinx.android.synthetic.main.activity_encryption_decryption.*

class EncryptionDecryptionActivity : AppCompatActivity(), View.OnClickListener {

    private var callerType: Int = AppConstants.TYPE_ENCRYPT;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryption_decryption)
        callerType = intent.getIntExtra("caller_type", AppConstants.TYPE_ENCRYPT)
        tvSubmit.setOnClickListener(this@EncryptionDecryptionActivity)
        setMyActionBar()
        setTextWatcher()

    }

    private fun setTextWatcher() {
        etInputText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (s.toString().length > 0){
                    tvSubmit.isEnabled = true
                }else{
                    tvSubmit.isEnabled = false
                }
                tvResult.text = getString(R.string.result)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not Needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not Needed
            }

        })
    }

    private fun setMyActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        if (callerType == AppConstants.TYPE_ENCRYPT) {
            supportActionBar?.title = getString(R.string.encryption)
        }else{
            supportActionBar?.title = getString(R.string.decryption)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvSubmit -> {
                CommonUtils.hideSoftKeyboard(this@EncryptionDecryptionActivity)
                encryptDecryptData(etInputText.text.toString())
            }
        }
    }

    /* private fun showErrorMessage() {
         var errorMessage: String;
         if (callerType == AppConstants.TYPE_ENCRYPT) {
             errorMessage = getString(R.string.enter_something_to_encrypt)
         }else{
             errorMessage = getString(R.string.enter_something_to_decrypt)
         }
         Toast.makeText(this@EncryptionDecryptionActivity, errorMessage, Toast.LENGTH_SHORT).show()
     }*/

    private fun encryptDecryptData(data: String) {
        if (callerType == AppConstants.TYPE_ENCRYPT) {
            tvResult.text = getString(R.string.result) + encryptString(data)
        }else{
            tvResult.text = getString(R.string.result) + decryptString(data)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun encryptString(str: String): String {
        var count = 1
        var i = 0
        val res = StringBuilder()
        while (i < str.length - 1) {
            if (str[i] == str[i + 1]) {
                count++
            } else {
                res.append(str[i]).append(count)
                count = 1
            }
            i++
        }
        res.append(str[i]).append(count)
        return res.toString()
    }

    private fun decryptString(str: String): String {
        val res = StringBuilder()
        var i = 0
        var count: Int
        try {
            while (i < str.length - 1) {
                count = Character.getNumericValue(str[i + 1])
                for (j in 1..count) {
                    res.append(str[i])
                }
                i = i + 2
            }
        } catch (e: Exception) {
            Toast.makeText(this@EncryptionDecryptionActivity, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
            e.printStackTrace()
            return ""
        }
        return res.toString()
    }
}
