package com.github.wnuk.hilttutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var someClass: SomeClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(someClass.doAThing())
    }
}

class SomeClass
@Inject
constructor(
    private val someInterfaceImpl: SomeInterface
){
    fun doAThing(): String{
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor(): SomeInterface {
    override fun getAThing() : String{
        return "A Thing"
    }
}

interface SomeInterface{
    fun getAThing(): String
}