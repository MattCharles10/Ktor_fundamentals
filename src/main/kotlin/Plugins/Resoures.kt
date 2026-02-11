package com.mathew.Plugins

import io.ktor.resources.Resource
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.resources.Resources


fun Application.configureResorces(){
    install(Resources)
}