package com.mathew.Plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.receiveParameters
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

    routing {

        post("checkout"){
            val formData = call.receiveParameters()
            val productId = formData["productId"]
            val quantity = formData["quantity"]
            call.respondText { "Order palced sucessfully ${productId} & Quantity : $quantity" }
        }

    }
}

/* Data class */
@Serializable
data class Product(
    val name: String,
    val category: String,
    val price: Int
)