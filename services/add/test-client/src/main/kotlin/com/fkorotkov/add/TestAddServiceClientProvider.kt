package com.fkorotkov.add

import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import com.fkorotkov.add.impl.AddServiceClientImpl
import com.fkorotkov.services.add.grpc.AddGrpc
import java.util.concurrent.TimeUnit


class TestAddServiceProvider() {
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

  fun createClient(): AddServiceClient {
    val futureStub = AddGrpc.newFutureStub(inprocessChannel)
    return AddServiceClientImpl(futureStub)
  }

  fun createAsyncClient(): AddServiceClient {
    val futureStub = AddGrpc.newFutureStub(inprocessChannel)
    return AddServiceClientImpl(futureStub)
  }
}
