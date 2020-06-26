package com.fkorotkov.add.client.impl

import com.fkorotkov.add.client.AddServiceClient
import com.fkorotkov.services.add.grpc.AddGrpcKt
import com.fkorotkov.services.add.grpc.CalculateRequest

class AddServiceClientImpl(private val stub: AddGrpcKt.AddCoroutineStub) : AddServiceClient {
  override suspend fun calculate(a: Long, b: Long): Long {
    val request = CalculateRequest.newBuilder()
      .setOperandOne(a)
      .setOperandTwo(b)
      .build()
    return stub.calculate(request).result
  }
}
