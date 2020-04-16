package com.fkorotkov.calculator

import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.calculator.impl.CalculatorServiceClientImpl
import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.services.calculator.grpc.CalculatorGrpc
import com.fkorotkov.subtract.SubtractServiceClient
import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import java.util.concurrent.TimeUnit


class TestCalculatorServiceProvider(
  addServiceClient: AddServiceClient,
  subtractServiceClient: SubtractServiceClient,
  multiplyServiceClient: MultiplyServiceClient
) {
  companion object {
    private val testServiceName = "calculator"
  }

  private val serviceImpl: CalculatorServiceImpl = CalculatorServiceImpl(addServiceClient, subtractServiceClient, multiplyServiceClient)

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

  val client: CalculatorServiceClient by lazy {
    val futureStub = CalculatorGrpc.newFutureStub(inprocessChannel)
    CalculatorServiceClientImpl(futureStub)
  }
}
