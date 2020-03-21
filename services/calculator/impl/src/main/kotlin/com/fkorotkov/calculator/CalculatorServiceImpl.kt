package com.fkorotkov.calculator

import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.add.configuration.AddServiceConfiguration
import com.fkorotkov.calculator.configuration.CalculatorServiceConfiguration
import com.fkorotkov.calculator.evaluator.AsyncEvaluator
import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.multiply.configuration.MultiplyServiceConfiguration
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc
import com.fkorotkov.services.calculator.grpc.EvaluateRequest
import com.fkorotkov.services.calculator.grpc.EvaluateResponse
import com.fkorotkov.subtract.SubtractServiceClient
import com.fkorotkov.subtract.configuration.SubtractServiceConfiguration
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {
  val addServiceClient = AddServiceConfiguration.createClient()
  val subtractServiceClient = SubtractServiceConfiguration.createClient()
  val multiplyServiceClient = MultiplyServiceConfiguration.createClient()

  val serviceImpl = CalculatorServiceImpl(addServiceClient, subtractServiceClient, multiplyServiceClient)
  val server = ServerBuilder.forPort(CalculatorServiceConfiguration.GRPC_PORT)
      .addService(serviceImpl)
      .build()
      .start()
  println("Started GRPC server on ${server.port} port...")
  server.awaitTermination()
}

class CalculatorServiceImpl(
    addServiceClient: AddServiceClient,
    subtractServiceClient: SubtractServiceClient,
    multiplyServiceClient: MultiplyServiceClient
) : CalculatorGrpc.CalculatorImplBase() {
  private val evaluator = AsyncEvaluator(addServiceClient, subtractServiceClient, multiplyServiceClient)
  override fun evaluate(request: EvaluateRequest, responseObserver: StreamObserver<EvaluateResponse>) {
    GlobalScope.launch {
      val result = evaluator.evaluate(request.expression).await()
      val response = EvaluateResponse.newBuilder()
          .setResult(result)
          .build()
      responseObserver.onNext(response)
      responseObserver.onCompleted()
    }.invokeOnCompletion { ex ->
      ex?.also { responseObserver.onError(it) }
    }
  }
}
