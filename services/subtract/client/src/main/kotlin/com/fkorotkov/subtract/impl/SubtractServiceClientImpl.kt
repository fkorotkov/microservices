package com.fkorotkov.subtract.impl

import com.fkorotkov.services.subtract.grpc.CalculateRequest
import com.fkorotkov.services.subtract.grpc.SubtractGrpcKt
import com.fkorotkov.subtract.SubtractServiceClient

class SubtractServiceClientImpl(private val stub: SubtractGrpcKt.SubtractCoroutineStub) : SubtractServiceClient {
  override suspend fun calculate(a: Long, b: Long): Long {
    val request = CalculateRequest.newBuilder()
      .setOperandOne(a)
      .setOperandTwo(b)
      .build()
    return stub.calculate(request).result
  }
}
