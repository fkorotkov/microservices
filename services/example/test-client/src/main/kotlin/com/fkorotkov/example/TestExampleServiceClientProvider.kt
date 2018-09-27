package com.fkorotkov.example

import io.grpc.ManagedChannel
import io.grpc.inprocess.InProcessChannelBuilder
import io.grpc.inprocess.InProcessServerBuilder
import com.fkorotkov.example.grpc.ExampleGrpc
import com.fkorotkov.example.impl.ExampleServiceClientImpl
import java.util.concurrent.TimeUnit


class TestExampleServiceProvider() {
  companion object {
    private val testServiceName = "example"
  }

  private val serviceImpl: ExampleServiceImpl = ExampleServiceImpl()

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

  fun createClient(): ExampleServiceClient {
    val futureStub = ExampleGrpc.newFutureStub(inprocessChannel)
    return ExampleServiceClientImpl(futureStub)
  }

  fun createAsyncClient(): ExampleServiceClient {
    val futureStub = ExampleGrpc.newFutureStub(inprocessChannel)
    return ExampleServiceClientImpl(futureStub)
  }
}
