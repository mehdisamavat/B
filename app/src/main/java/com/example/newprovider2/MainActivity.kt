package com.example.newprovider2

import android.database.ContentObserver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import com.example.newprovider2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        val uri= Uri.parse("content://com.example.newprovider/domains")



        contentResolver!!.query(uri, null, null, null, null)?.let {
            var count=0
                while (it.moveToNext()) {
                    count++
                }
            it.close()
            binding.countText.text = "size= $count"
        }



      contentResolver.registerContentObserver(uri, true, object : ContentObserver(Handler(
            Looper.getMainLooper())) { override fun onChange(selfChange: Boolean) {
            println("onChange")
           var count=0

            val cursor = contentResolver!!.query(uri, null, null, null, null)

            cursor?.let {
//                val titleIndex = it.getColumnIndex("name")
                while (it.moveToNext()) {
                    count++
                }
                binding.countText.text=count.toString()
            }


            cursor?.close()


        } })

    }
}