package com.fkorotkov.example.client.impl

import com.fkorotkov.example.client.ExampleServiceClient
import com.fkorotkov.services.example.grpc.ExampleGrpcKt

class ExampleServiceClientImpl(private val stub: ExampleGrpcKt.ExampleCoroutineStub) : ExampleServiceClient {
}
