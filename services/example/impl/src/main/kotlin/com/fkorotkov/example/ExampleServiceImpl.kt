package com.fkorotkov.example

import io.grpc.ServerBuilder
import com.fkorotkov.example.configuration.ExampleServiceConfiguration
import com.fkorotkov.example.grpc.ExampleGrpc

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