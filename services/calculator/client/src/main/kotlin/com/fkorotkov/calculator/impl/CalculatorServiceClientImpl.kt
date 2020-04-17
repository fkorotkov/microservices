package com.fkorotkov.calculator.impl

import com.fkorotkov.calculator.CalculatorServiceClient
import com.fkorotkov.services.calculator.grpc.CalculatorGrpcKt
import com.fkorotkov.services.calculator.grpc.EvaluateRequest

class CalculatorServiceClientImpl(private val stub: CalculatorGrpcKt.CalculatorCoroutineStub) : CalculatorServiceClient {
  override suspend fun evaluate(expression: String): Long {
    val request = EvaluateRequest.newBuilder()
      .setExpression(expression)
      .build()
    return stub.evaluate(request).result
  }
}
