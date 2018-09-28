package com.fkorotkov.subtract.impl

import com.fkorotkov.services.subtract.grpc.CalculateRequest
import com.fkorotkov.services.subtract.grpc.SubtractGrpc
import com.fkorotkov.subtract.SubtractServiceClient
import kotlinx.coroutines.experimental.guava.await

class SubtractServiceClientImpl(private val service: SubtractGrpc.SubtractFutureStub) : SubtractServiceClient {
  override suspend fun calculate(a: Long, b: Long): Long {
    val request = CalculateRequest.newBuilder()
        .setOperandOne(a)
        .setOperandTwo(b)
        .build()
    return service.calculate(request).await().result
  }
}
