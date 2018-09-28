package com.fkorotkov.example.impl

import com.fkorotkov.example.ExampleServiceClient
import com.fkorotkov.services.example.grpc.ExampleGrpc

class ExampleServiceClientImpl(service: ExampleGrpc.ExampleFutureStub) : ExampleServiceClient {
}
