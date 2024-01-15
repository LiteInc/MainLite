package com.inc.lite.mainlite

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.inc.lite.mainlite.theme.MainLiteTheme

class MainLiteActivity  : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainLiteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
        hideSystemUI(window)
        stayFullscreen()
        startStation()

    }

    override fun onResume() {
        super.onResume()
        startStation()
    }

    private fun startStation(){
        val appStartIntent = Intent("com.inc.lite.station.Start")

        // Start the MainActivity using the created intent
        try{
            this.startActivity(appStartIntent)

        }catch (e: Exception){
            e.message?.let { Log.d("OnBootUpReceiver", it) }
        }
    }
    private fun setAsDefaultLauncher() {
        val packageManager = packageManager
        val componentName = ComponentName(packageName, MainLiteActivity::class.java.name)

        packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val selector = Intent(Intent.ACTION_MAIN)
        selector.addCategory(Intent.CATEGORY_HOME)
        startActivity(selector)

        packageManager.setComponentEnabledSetting(
            componentName,
            PackageManager.COMPONENT_ENABLED_STATE_DEFAULT,
            PackageManager.DONT_KILL_APP
        )
    }
    fun getInstance(): Context{
        return this.application.applicationContext
    }

    fun hideSystemUI(window: Window) {

        //Hide the status bars

        WindowCompat.setDecorFitsSystemWindows(window, false)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_IMMERSIVE)
        }
        else {
            window.insetsController?.apply {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                or View.SYSTEM_UI_FLAG_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_IMMERSIVE)
//                    systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            val decorView = window.decorView
            val flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
            decorView.systemUiVisibility = flags


        }

    }

    fun stayFullscreen(){
        val rootView = window.decorView
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight > screenHeight * 0.15) {
                // Keyboard is open
            } else {
                rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                hideSystemUI(window)
                // Keyboard is closed
                // Here you can hide the navigation bar if needed
            }
        }
        rootView.viewTreeObserver.addOnGlobalLayoutListener(listener)
        hideSystemUI(window)
        try {
            Intent("android.intent.action.WebViewActivity").apply {
                ContextCompat.startActivity(this@MainLiteActivity,this,null)
            }
        }catch (e : Exception){
            Log.e("MainLiteActivity","Error on WebViewActivity : ${e.message}")
        }
    }


}

