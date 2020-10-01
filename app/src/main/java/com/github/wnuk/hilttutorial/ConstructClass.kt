package com.github.wnuk.hilttutorial

import javax.inject.Inject

class ConstructClass
@Inject
constructor() {
    fun doOtherThin(): String{
        return "Look did a thing"
    }
}