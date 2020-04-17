package com.fkorotkov.example.configuration

import com.fkorotkov.example.ExampleServiceClient
import com.fkorotkov.example.impl.ExampleServiceClientImpl
import com.fkorotkov.services.example.grpc.ExampleGrpcKt
import io.grpc.ManagedChannelBuilder

object ExampleServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): ExampleServiceClient {
    val channel = ManagedChannelBuilder.forAddress("example", GRPC_PORT)
      .enableRetry()
      .maxRetryAttempts(3)
      .build()

    val stub = ExampleGrpcKt.ExampleCoroutineStub(channel)
    return ExampleServiceClientImpl(stub)
  }
}
