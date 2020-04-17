package com.fkorotkov.multiply.impl

import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.services.multiply.grpc.CalculateRequest
import com.fkorotkov.services.multiply.grpc.MultiplyGrpcKt

class MultiplyServiceClientImpl(private val stub: MultiplyGrpcKt.MultiplyCoroutineStub) : MultiplyServiceClient {
  override suspend fun calculate(a: Long, b: Long): Long {
    val request = CalculateRequest.newBuilder()
      .setOperandOne(a)
      .setOperandTwo(b)
      .build()
    return stub.calculate(request).result
  }
}
