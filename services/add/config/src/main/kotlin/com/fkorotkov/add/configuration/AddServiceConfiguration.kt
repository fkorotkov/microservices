package com.fkorotkov.add.configuration

import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.add.impl.AddServiceClientImpl
import com.fkorotkov.services.add.grpc.AddGrpcKt
import io.grpc.ManagedChannelBuilder

object AddServiceConfiguration {
  val GRPC_PORT = 8239

  fun createClient(): AddServiceClient {
    val channel = ManagedChannelBuilder.forAddress("add", GRPC_PORT)
      .enableRetry()
      .maxRetryAttempts(3)
      .build()
    val stub = AddGrpcKt.AddCoroutineStub(channel)
    return AddServiceClientImpl(stub)
  }
}
