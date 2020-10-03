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
import javax.inject.Qualifier

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
    @Impl1 private val someInterfaceImpl: SomeInterface,
    @Impl2 private val someInterfaceImplSecond: SomeInterface
) {
    fun doAThing(): String {
        return "Look I got: ${someInterfaceImpl.getAThing()}"
    }
    fun doAThingSecond(): String {
        return "Look I got: ${someInterfaceImplSecond.getAThing()}"
    }
}

class SomeInterfaceImpl
@Inject
constructor() : SomeInterface {
    override fun getAThing(): String {
        return "A Thing 1"
    }
}

class SomeInterfaceImplSecond
@Inject
constructor() : SomeInterface {
    override fun getAThing(): String {
        return "A Thing 2"
    }
}

interface SomeInterface {
    fun getAThing(): String
}

@InstallIn(ActivityComponent::class)
@Module
class MyModule {

    @Impl1
    @ActivityScoped
    @Provides
    fun provideSomeInterface(): SomeInterface{
        return SomeInterfaceImpl()
    }

    @Impl2
    @ActivityScoped
    @Provides
    fun provideSomeInterfaceSecond(): SomeInterface{
        return SomeInterfaceImplSecond()
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2