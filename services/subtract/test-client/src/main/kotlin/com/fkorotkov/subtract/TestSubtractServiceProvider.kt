package com.fkorotkov.subtract

import com.fkorotkov.services.subtract.grpc.SubtractGrpc
import com.fkorotkov.subtract.impl.SubtractServiceClientImpl
import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import java.util.concurrent.TimeUnit


class TestSubtractServiceProvider() {
  companion object {
    private val testServiceName = "subtract"
  }

  private val serviceImpl: SubtractServiceImpl = SubtractServiceImpl()

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

  val client: SubtractServiceClient by lazy {
    val futureStub = SubtractGrpc.newFutureStub(inprocessChannel)
    SubtractServiceClientImpl(futureStub)
  }
}
