package com.fkorotkov.add

import com.fkorotkov.add.impl.AddServiceClientImpl
import com.fkorotkov.services.add.grpc.AddGrpcKt
import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import java.util.concurrent.TimeUnit


class TestAddServiceProvider {
  companion object {
    private val testServiceName = "add"
  }

  private val serviceImpl: AddServiceImpl = AddServiceImpl()

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

  val client: AddServiceClient by lazy {
    val futureStub = AddGrpcKt.AddCoroutineStub(inprocessChannel)
    AddServiceClientImpl(futureStub)
  }
}
