package com.fkorotkov.calculator

import com.fkorotkov.calculator.impl.CalculatorServiceClientImpl
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc
import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import java.util.concurrent.TimeUnit


class TestCalculatorServiceProvider() {
  companion object {
    private val testServiceName = "calculator"
  }

  private val serviceImpl: CalculatorServiceImpl = CalculatorServiceImpl()

  private val inProcessServer = InProcessServerBuilder.forName(testServiceName)
      .addService(serviceImpl)
      .directExecutor()
      .build()

  fun start() = inProcessServer.start()

  fun stop() {
    inProcessServer.shutdownNow().awaitTermination(1, TimeUnit.MINUTES)
    inprocessChannel.shutdownNow().awaitTermination(1, TimeUnit.MINUTES)
  }

  private val inprocessChannel: ManagedChannel by lazy {
    InProcessChannelBuilder.forName(testServiceName).directExecutor().build()
  }

  fun createClient(): CalculatorServiceClient {
    val futureStub = CalculatorGrpc.newFutureStub(inprocessChannel)
    return CalculatorServiceClientImpl(futureStub)
  }

  fun createAsyncClient(): CalculatorServiceClient {
    val futureStub = CalculatorGrpc.newFutureStub(inprocessChannel)
    return CalculatorServiceClientImpl(futureStub)
  }
}
