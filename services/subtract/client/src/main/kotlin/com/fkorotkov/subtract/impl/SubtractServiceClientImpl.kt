package com.fkorotkov.subtract.impl

import com.fkorotkov.subtract.SubtractServiceClient
import com.fkorotkov.services.subtract.grpc.SubtractGrpc

class SubtractServiceClientImpl(service: SubtractGrpc.SubtractFutureStub) : SubtractServiceClient {
}
