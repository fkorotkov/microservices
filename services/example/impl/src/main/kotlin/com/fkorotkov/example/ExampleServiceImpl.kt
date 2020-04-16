package com.fkorotkov.example

import com.fkorotkov.example.configuration.ExampleServiceConfiguration
import com.fkorotkov.services.example.grpc.ExampleGrpc
import io.grpc.ServerBuilder

fun main() {
  val serviceImpl = ExampleServiceImpl()
  val server = ServerBuilder.forPort(ExampleServiceConfiguration.GRPC_PORT)
    .addService(serviceImpl)
    .build()
    .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class ExampleServiceImpl : ExampleGrpc.ExampleImplBase()
