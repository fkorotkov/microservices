package com.fkorotkov.calculator.configuration

import com.fkorotkov.calculator.CalculatorServiceClient
import com.fkorotkov.calculator.impl.CalculatorServiceClientImpl
import com.fkorotkov.services.calculator.grpc.CalculatorGrpcKt
import io.grpc.ManagedChannelBuilder

object CalculatorServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): CalculatorServiceClient {
    val channel = ManagedChannelBuilder.forAddress("calculator", GRPC_PORT)
      .enableRetry()
      .maxRetryAttempts(3)
      .build()
    val stub = CalculatorGrpcKt.CalculatorCoroutineStub(channel)
    return CalculatorServiceClientImpl(stub)
  }
}
