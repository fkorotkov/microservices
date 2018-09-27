package com.fkorotkov.example.configuration

import io.grpc.ManagedChannelBuilder
import com.fkorotkov.example.ExampleServiceClient
import com.fkorotkov.example.grpc.ExampleGrpc
import com.fkorotkov.example.impl.ExampleServiceClientImpl

object ExampleServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): ExampleServiceClient {
    val channel = ManagedChannelBuilder.forAddress("example", GRPC_PORT)
      .enableRetry()
      .maxRetryAttempts(3)
      .build()
    val futureStub = ExampleGrpc.newFutureStub(channel)

    return ExampleServiceClientImpl(futureStub)
  }
}
