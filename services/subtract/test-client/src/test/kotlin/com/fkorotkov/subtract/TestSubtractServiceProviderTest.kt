package com.fkorotkov.subtract

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestSubtractServiceProviderTest {
  lateinit var provider: TestSubtractServiceProvider

  @Before
  fun setUp() {
    provider = TestSubtractServiceProvider()
    provider.start()
  }

  @After
  fun tearDown() {
    provider.stop()
  }

  @Test
  fun SubtractTwoNumbers() {
    val result = runBlocking {
      provider.client.calculate(1, 2)
    }
    assertEquals(-1, result)
  }

}
