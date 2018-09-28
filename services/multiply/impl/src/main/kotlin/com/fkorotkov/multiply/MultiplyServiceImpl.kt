package com.fkorotkov.multiply

import com.fkorotkov.multiply.configuration.MultiplyServiceConfiguration
import com.fkorotkov.services.multiply.grpc.MultiplyGrpc
import io.grpc.ServerBuilder

fun main() {
  val serviceImpl = MultiplyServiceImpl()
  val server = ServerBuilder.forPort(MultiplyServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class MultiplyServiceImpl : MultiplyGrpc.MultiplyImplBase()
