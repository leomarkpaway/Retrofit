package com.leomarkpaway.retrofit.common.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

inline fun <reified T : Activity> Context.createIntent(): Intent = Intent(this, T::class.java)

inline fun <reified T : Activity> Context.createIntent(configure: Intent.() -> Unit = {}): Intent {
    val intent = Intent(this, T::class.java)
    intent.configure()
    return intent
}