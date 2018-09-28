package com.fkorotkov.calculator.impl

import com.fkorotkov.calculator.CalculatorServiceClient
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc

class CalculatorServiceClientImpl(service: CalculatorGrpc.CalculatorFutureStub) : CalculatorServiceClient {
}
