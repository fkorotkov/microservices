package com.fkorotkov.multiply.configuration

import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.multiply.impl.MultiplyServiceClientImpl
import com.fkorotkov.services.multiply.grpc.MultiplyGrpcKt
import io.grpc.ManagedChannelBuilder

object MultiplyServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): MultiplyServiceClient {
    val channel = ManagedChannelBuilder.forAddress("multiply", GRPC_PORT)
      .enableRetry()
      .maxRetryAttempts(3)
      .build()

    val stub = MultiplyGrpcKt.MultiplyCoroutineStub(channel)
    return MultiplyServiceClientImpl(stub)
  }
}
