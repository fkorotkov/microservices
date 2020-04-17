package com.fkorotkov.multiply

import com.fkorotkov.multiply.impl.MultiplyServiceClientImpl
import com.fkorotkov.services.multiply.grpc.MultiplyGrpcKt
import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import java.util.concurrent.TimeUnit


class TestMultiplyServiceProvider {
  companion object {
    private val testServiceName = "multiply"
  }

  private val serviceImpl: MultiplyServiceImpl = MultiplyServiceImpl()

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

  val client: MultiplyServiceClient by lazy {
    val futureStub = MultiplyGrpcKt.MultiplyCoroutineStub(inprocessChannel)
    MultiplyServiceClientImpl(futureStub)
  }
}
