package com.github.wnuk.hilttutorial

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FieldClass @Inject constructor(
) {
    fun doAThing(): String {
        return "Look a thing!"
    }
}