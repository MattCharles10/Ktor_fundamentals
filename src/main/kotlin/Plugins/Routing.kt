package com.mathew.Plugins

import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.resources.Resource
import io.ktor.server.request.receiveText
import io.ktor.server.resources.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Server is running!")
        }

        post("greet"){
            val name = call.receiveText()
            call.respondText { "Hello , $name" }
        }
    }

}

/*    */



