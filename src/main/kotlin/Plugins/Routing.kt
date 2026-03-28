package com.mathew.Plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.request.receiveParameters
import kotlinx.serialization.Serializable

fun Application.configureRouting() {

    routing {

        // ✅ Root route (fixes your failing test)
        get("/") {
            call.respondText("Hello World!")
        }

        // ✅ Checkout route
        post("/checkout") {
            val formData = call.receiveParameters()

            val productId = formData["productId"] ?: "Unknown"
            val quantity = formData["quantity"] ?: "0"

            call.respondText(
                "Order placed successfully Product Id : $productId & Quantity : $quantity",
                status = HttpStatusCode.OK
            )
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