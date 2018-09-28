package com.fkorotkov.add

import com.fkorotkov.add.configuration.AddServiceConfiguration
import com.fkorotkov.services.add.grpc.AddGrpc
import io.grpc.ServerBuilder

fun main() {
  val serviceImpl = AddServiceImpl()
  val server = ServerBuilder.forPort(AddServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class AddServiceImpl : AddGrpc.AddImplBase()
