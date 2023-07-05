package com.raghav.imageloadingwithoutlibrary

import java.lang.Thread

class ExampleThread(val task: () -> Unit) : Thread() {
    override fun run() {
        task()
    }
}
