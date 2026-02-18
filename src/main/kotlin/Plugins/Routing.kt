package com.mathew.Plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.receive
import io.ktor.server.request.receiveChannel
import io.ktor.server.request.receiveText
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readText
import java.io.File

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Server is running!")
        }

        post("greet"){
            val name = call.receiveText()
            call.respondText { "Hello , $name" }
        }

        post("channel") {
            val channel = call.receiveChannel()
            val test = channel.readRemaining().readText()
            call.respondText(test)
        }

        //file uploading
        post("upload"){

            val file = File("uploads/sample.jpg").apply {
                parentFile?.mkdirs()
            }

            val byteArray = call.receive<ByteArray>()

            file.writeBytes(byteArray)

            call.respondText("File upload success")

        }
    }

}

/*          */



