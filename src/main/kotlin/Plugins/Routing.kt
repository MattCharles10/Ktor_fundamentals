package com.mathew.Plugins

import io.ktor.http.HttpMethod
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.resources.Resource

fun Application.configureRouting() {

    install(RoutingRoot){
        route("/" , HttpMethod.Get){
            handle {
                call.respondText { "Hello world 123" }
            }
        }
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("blogs/{id}"){
            val id = call.pathParameters["id"]
            val q1 = call.queryParameters["q1"]
            val q2 = call.queryParameters["q2"]
            call.respondText { "Blog with id $id and query is $q1 & query 2 is $q2"  }
        }

        get(Regex(".+/test")){
            call.respondText { "Api_response_test" }
        }

        //api/v1/users
        //api/v2/users
        //api/v3/users

        get(Regex("api/(?<apiVersion>v[1-3])/users")){
            val version = call.pathParameters["apiVersion"]
            call.respondText { "Api Version is $version" }
        }
    }
}

/*    */

@Resource("blogs")
    class Blogs(val sort : String? = "new"){
        @Resource("{id}")
        data class Blog(val parent:Blogs = Blogs(),val id:String)
    }

