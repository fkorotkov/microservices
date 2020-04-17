package com.fkorotkov.example.impl

import com.fkorotkov.example.ExampleServiceClient
import com.fkorotkov.services.example.grpc.ExampleGrpcKt

class ExampleServiceClientImpl(private val stub: ExampleGrpcKt.ExampleCoroutineStub) : ExampleServiceClient {
}
