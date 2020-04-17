package com.fkorotkov.add

import com.fkorotkov.add.configuration.AddServiceConfiguration
import com.fkorotkov.services.add.grpc.AddGrpcKt
import com.fkorotkov.services.add.grpc.CalculateRequest
import com.fkorotkov.services.add.grpc.CalculateResponse
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

class AddServiceImpl : AddGrpcKt.AddCoroutineImplBase() {
  override suspend fun calculate(request: CalculateRequest): CalculateResponse {
    val result = request.operandOne + request.operandTwo
    return CalculateResponse.newBuilder()
      .setResult(result)
      .build()
  }
}
