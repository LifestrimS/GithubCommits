package com.lifestrim.githubcommits.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.lifestrim.githubcommits.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(ReposFragment(), null)
    }

}

fun AppCompatActivity.replaceFragment(fragment: Fragment, bundle: Bundle?){
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    if (bundle != null) {
        fragment.arguments = bundle
    }
    transaction.replace(R.id.fragment_container,fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}