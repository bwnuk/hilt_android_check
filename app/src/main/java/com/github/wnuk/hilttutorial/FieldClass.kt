package com.github.wnuk.hilttutorial

import javax.inject.Inject

class FieldClass @Inject constructor(
    private val constructClass: ConstructClass
) {
    fun doAThing(): String {
        return "Look a thing!"
    }

    fun doOtherThing(): String {
        return constructClass.doOtherThin()
    }
}