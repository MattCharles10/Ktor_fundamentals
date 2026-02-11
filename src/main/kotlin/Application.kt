package com.mathew

import com.mathew.Plugins.configureResorces
import com.mathew.Plugins.configureRouting
import com.mathew.Plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureResorces()
    configureRouting()
    configureSerialization()
}
