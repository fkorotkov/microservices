package com.fkorotkov.multiply.impl

import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.services.multiply.grpc.CalculateRequest
import com.fkorotkov.services.multiply.grpc.MultiplyGrpc
import kotlinx.coroutines.guava.asDeferred

class MultiplyServiceClientImpl(val service: MultiplyGrpc.MultiplyFutureStub) : MultiplyServiceClient {
  override suspend fun calculate(a: Long, b: Long): Long {
    val request = CalculateRequest.newBuilder()
      .setOperandOne(a)
      .setOperandTwo(b)
      .build()
    return service.calculate(request).asDeferred().await().result
  }
}
