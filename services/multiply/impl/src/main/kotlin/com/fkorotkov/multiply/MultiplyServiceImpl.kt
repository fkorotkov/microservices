package com.fkorotkov.multiply

import com.fkorotkov.multiply.configuration.MultiplyServiceConfiguration
import com.fkorotkov.services.multiply.grpc.CalculateRequest
import com.fkorotkov.services.multiply.grpc.CalculateResponse
import com.fkorotkov.services.multiply.grpc.MultiplyGrpc
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver

fun main() {
  val serviceImpl = MultiplyServiceImpl()
  val server = ServerBuilder.forPort(MultiplyServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class MultiplyServiceImpl : MultiplyGrpc.MultiplyImplBase() {
  override fun calculate(request: CalculateRequest, responseObserver: StreamObserver<CalculateResponse>) {
    val result = request.operandOne * request.operandTwo
    val response = CalculateResponse.newBuilder()
        .setResult(result)
        .build()
    responseObserver.onNext(response)
    responseObserver.onCompleted()
  }

}
