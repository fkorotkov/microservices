package com.fkorotkov.calculator

import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.add.configuration.AddServiceConfiguration
import com.fkorotkov.calculator.configuration.CalculatorServiceConfiguration
import com.fkorotkov.calculator.evaluator.AsyncEvaluator
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc
import com.fkorotkov.services.calculator.grpc.EvaluateRequest
import com.fkorotkov.services.calculator.grpc.EvaluateResponse
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.experimental.runBlocking

fun main() {
  val addServiceClient = AddServiceConfiguration.createClient()

  val serviceImpl = CalculatorServiceImpl(addServiceClient)
  val server = ServerBuilder.forPort(CalculatorServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class CalculatorServiceImpl(addServiceClient: AddServiceClient) : CalculatorGrpc.CalculatorImplBase() {
  private val evaluator = AsyncEvaluator(addServiceClient)
  override fun evaluate(request: EvaluateRequest, responseObserver: StreamObserver<EvaluateResponse>) {
    try {
      val result = runBlocking {
        evaluator.evaluate(request.expression).await()
      }
      val response = EvaluateResponse.newBuilder()
          .setResult(result)
          .build()
      responseObserver.onNext(response)
      responseObserver.onCompleted()
    } catch (e: Exception) {
      responseObserver.onError(e)
    }
  }
}
