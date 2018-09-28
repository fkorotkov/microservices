package com.fkorotkov.multiply

import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TestMultiplyServiceProviderTest {
  lateinit var provider: TestMultiplyServiceProvider

  @Before
  fun setUp() {
    provider = TestMultiplyServiceProvider()
    provider.start()
  }

  @After
  fun tearDown() {
    provider.stop()
  }

  @Test
  fun addTwoNumbers() {
    val result = runBlocking {
      provider.client.calculate(2, 2)
    }
    assertEquals(4, result)
  }
}