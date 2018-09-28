package com.fkorotkov.subtract.configuration

import com.fkorotkov.services.subtract.grpc.SubtractGrpc
import com.fkorotkov.subtract.SubtractServiceClient
import com.fkorotkov.subtract.impl.SubtractServiceClientImpl
import io.grpc.ManagedChannelBuilder

object SubtractServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): SubtractServiceClient {
    val channel = ManagedChannelBuilder.forAddress("subtract", GRPC_PORT)
        .enableRetry()
        .maxRetryAttempts(3)
        .build()
    val futureStub = SubtractGrpc.newFutureStub(channel)

    return SubtractServiceClientImpl(futureStub)
  }
}
