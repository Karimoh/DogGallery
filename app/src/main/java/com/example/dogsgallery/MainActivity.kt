package com.example.dogsgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.dogsgallery.model.AppViewModel
import com.example.dogsgallery.model.Fabrica
import com.example.dogsgallery.screen.NavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appModel by viewModels<AppViewModel>() {
            Fabrica(this)
        }
        setContent {
            NavigationHost(appModel)
        }
    }
}