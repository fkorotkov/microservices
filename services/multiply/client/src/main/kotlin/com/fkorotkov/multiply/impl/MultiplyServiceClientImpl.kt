package com.fkorotkov.multiply.impl

import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.services.multiply.grpc.MultiplyGrpc

class MultiplyServiceClientImpl(service: MultiplyGrpc.MultiplyFutureStub) : MultiplyServiceClient {
}
