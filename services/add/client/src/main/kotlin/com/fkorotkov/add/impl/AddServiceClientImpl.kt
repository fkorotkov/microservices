package com.fkorotkov.add.impl

import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.services.add.grpc.AddGrpc
import com.fkorotkov.services.add.grpc.CalculateRequest
import kotlinx.coroutines.guava.asDeferred

class AddServiceClientImpl(private val service: AddGrpc.AddFutureStub) : AddServiceClient {
  override suspend fun calculate(a: Long, b: Long): Long {
    val request = CalculateRequest.newBuilder()
        .setOperandOne(a)
        .setOperandTwo(b)
        .build()
    return service.calculate(request).asDeferred().await().result
  }
}
