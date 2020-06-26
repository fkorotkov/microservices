package com.fkorotkov.subtract.configuration

import com.fkorotkov.services.subtract.grpc.SubtractGrpcKt
import com.fkorotkov.subtract.client.SubtractServiceClient
import com.fkorotkov.subtract.client.impl.SubtractServiceClientImpl
import io.grpc.ManagedChannelBuilder

object SubtractServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): SubtractServiceClient {
    val channel = ManagedChannelBuilder.forAddress("subtract", GRPC_PORT)
      .enableRetry()
      .maxRetryAttempts(3)
      .build()

    val stub = SubtractGrpcKt.SubtractCoroutineStub(channel)
    return SubtractServiceClientImpl(stub)
  }
}
