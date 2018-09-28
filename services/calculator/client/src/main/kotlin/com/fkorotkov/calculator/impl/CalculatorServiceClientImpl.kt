package com.fkorotkov.calculator.impl

import com.fkorotkov.calculator.CalculatorServiceClient
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc
import com.fkorotkov.services.calculator.grpc.EvaluateRequest
import kotlinx.coroutines.experimental.guava.await

class CalculatorServiceClientImpl(private val service: CalculatorGrpc.CalculatorFutureStub) : CalculatorServiceClient {
  override suspend fun evaluate(expression: String): Long {
    val request = EvaluateRequest.newBuilder()
        .setExpression(expression)
        .build()
    return service.evaluate(request).await().result
  }
}
