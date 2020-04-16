package com.fkorotkov.add

import com.fkorotkov.add.configuration.AddServiceConfiguration
import com.fkorotkov.services.add.grpc.AddGrpc
import com.fkorotkov.services.add.grpc.CalculateRequest
import com.fkorotkov.services.add.grpc.CalculateResponse
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

fun main() {
  val serviceImpl = AddServiceImpl()
  val server = ServerBuilder.forPort(AddServiceConfiguration.GRPC_PORT)
    .addService(serviceImpl)
    .build()
    .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class AddServiceImpl : AddGrpc.AddImplBase() {
  override fun calculate(request: CalculateRequest, responseObserver: StreamObserver<CalculateResponse>) {
    val result = request.operandOne + request.operandTwo
    val response = CalculateResponse.newBuilder()
      .setResult(result)
      .build()
    responseObserver.onNext(response)
    responseObserver.onCompleted()
  }
}
