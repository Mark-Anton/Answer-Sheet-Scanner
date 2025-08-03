package com.example.answersheetscanner

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigate to camera or upload file
        findViewById<Button>(R.id.buttonOpenCamera).setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }
    }
}
