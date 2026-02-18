package com.mathew.Plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.receive
import io.ktor.server.request.receiveChannel
import io.ktor.server.request.receiveNullable
import io.ktor.server.request.receiveStream
import io.ktor.server.request.receiveText
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.copyAndClose
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readText
import jdk.jfr.Category
import kotlinx.serialization.Serializable
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

            val file = File("uploads/sample2.jpg").apply {
                parentFile?.mkdirs()
            }

            //val byteArray = call.receive<ByteArray>()
            //file.writeBytes(byteArray)

            //val stream = call.receiveStream()

           // FileOutputStream(file).use { outputStream ->
           //     stream.copyTo(outputStream, bufferSize = 16*1024)
           // }

            val channel = call.receiveChannel()
            channel.copyAndClose(file.writeChannel())

            call.respondText("File upload success")

            post("product") {
                val product = call.receiveNullable<Product>()
                    ?: return@post call.respond(HttpStatusCode.BadRequest)

                call.respond(product)
            }

        }
    }

}

/*          */

@Serializable
data class Product(
    val name:String,
    val category: String,
    val price : Int
)


