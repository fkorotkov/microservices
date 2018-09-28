package com.fkorotkov.add.impl

import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.services.add.grpc.AddGrpc

class AddServiceClientImpl(service: AddGrpc.AddFutureStub) : AddServiceClient {
}
