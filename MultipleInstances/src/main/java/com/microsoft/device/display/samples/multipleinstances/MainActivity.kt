package com.microsoft.device.display.samples.multipleinstances

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addShortcuts()
    }

    private fun addShortcuts() {
        val intent = Intent(
            Intent.ACTION_MAIN, Uri.EMPTY,
            this,
            MainActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val intent2 = Intent(
            Intent.ACTION_MAIN,
            Uri.EMPTY,
            this,
            SecondActivity::class.java
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val shortcutManager = getSystemService<ShortcutManager>(ShortcutManager::class.java)
        val shortcut = ShortcutInfo.Builder(this, "id1")
            .setShortLabel("New Instance")
            .setLongLabel("New Instance")
            .setIntent(intent)
            .build()
        val shortcut2 = ShortcutInfo.Builder(this, "id2")
            .setShortLabel("Second Activity")
            .setLongLabel("Second Activity")
            .setIntent(intent2)
            .build()

        if (shortcutManager != null) {
            shortcutManager.dynamicShortcuts = listOf(shortcut, shortcut2)
        }
    }
}
