package com.fkorotkov.calculator.test

import com.fkorotkov.add.client.AddServiceClient
import com.fkorotkov.calculator.CalculatorServiceImpl
import com.fkorotkov.calculator.client.CalculatorServiceClient
import com.fkorotkov.calculator.client.impl.CalculatorServiceClientImpl
import com.fkorotkov.multiply.client.MultiplyServiceClient
import com.fkorotkov.services.calculator.grpc.CalculatorGrpcKt
import com.fkorotkov.subtract.client.SubtractServiceClient
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
    val futureStub = CalculatorGrpcKt.CalculatorCoroutineStub(inprocessChannel)
    CalculatorServiceClientImpl(futureStub)
  }
}
