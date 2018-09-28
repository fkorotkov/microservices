package com.fkorotkov.subtract

import com.fkorotkov.services.subtract.grpc.CalculateRequest
import com.fkorotkov.services.subtract.grpc.CalculateResponse
import com.fkorotkov.services.subtract.grpc.SubtractGrpc
import com.fkorotkov.subtract.configuration.SubtractServiceConfiguration
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

fun main() {
  val serviceImpl = SubtractServiceImpl()
  val server = ServerBuilder.forPort(SubtractServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class SubtractServiceImpl : SubtractGrpc.SubtractImplBase() {
  override fun calculate(request: CalculateRequest, responseObserver: StreamObserver<CalculateResponse>) {
    val result = request.operandOne - request.operandTwo
    val response = CalculateResponse.newBuilder()
        .setResult(result)
        .build()
    responseObserver.onNext(response)
    responseObserver.onCompleted()
  }
}
