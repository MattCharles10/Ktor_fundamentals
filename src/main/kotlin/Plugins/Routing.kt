package com.mathew.Plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.receive
import io.ktor.server.request.receiveChannel
import io.ktor.server.request.receiveStream
import io.ktor.server.request.receiveText
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readText
import java.io.File
import java.io.FileOutputStream

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

            val file = File("uploads/sample1.jpg").apply {
                parentFile?.mkdirs()
            }

            //val byteArray = call.receive<ByteArray>()
            //file.writeBytes(byteArray)

            val stream = call.receiveStream()

            FileOutputStream(file).use { outputStream ->
                stream.copyTo(outputStream, bufferSize = 16*1024)
            }

            call.respondText("File upload success")

        }
    }

}

/*          */



