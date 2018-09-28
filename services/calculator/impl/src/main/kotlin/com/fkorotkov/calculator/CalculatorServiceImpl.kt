package com.fkorotkov.calculator

import com.fkorotkov.calculator.configuration.CalculatorServiceConfiguration
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc
import io.grpc.ServerBuilder

fun main() {
  val serviceImpl = CalculatorServiceImpl()
  val server = ServerBuilder.forPort(CalculatorServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class CalculatorServiceImpl : CalculatorGrpc.CalculatorImplBase()
