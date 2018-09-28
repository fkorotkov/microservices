package com.fkorotkov.subtract

import com.fkorotkov.subtract.configuration.SubtractServiceConfiguration
import com.fkorotkov.services.subtract.grpc.SubtractGrpc
import io.grpc.ServerBuilder

fun main() {
  val serviceImpl = SubtractServiceImpl()
  val server = ServerBuilder.forPort(SubtractServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class SubtractServiceImpl : SubtractGrpc.SubtractImplBase()
