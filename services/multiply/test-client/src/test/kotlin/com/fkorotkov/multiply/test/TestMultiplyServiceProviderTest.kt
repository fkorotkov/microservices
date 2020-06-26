package com.fkorotkov.multiply.test

import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
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
