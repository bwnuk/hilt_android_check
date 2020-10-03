package com.github.wnuk.hilttutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
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

@ActivityScoped
class SomeClass
@Inject
constructor(
    private val someInterfaceImpl: SomeInterface
) {
    fun doAThing(): String {
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor(
    private val someDependency: String
) : SomeInterface {
    override fun getAThing(): String {
        return "A Thing ${someDependency}"
    }
}

interface SomeInterface {
    fun getAThing(): String
}

@InstallIn(ActivityComponent::class)
@Module
class MyModule {
    @ActivityScoped
    @Provides
    fun providesSomeString(): String{
        return "SomeString"
    }

    @ActivityScoped
    @Provides
    fun provideSomeInterface(someString: String): SomeInterface{
        return SomeInterfaceImpl(someString)
    }
}