package com.fkorotkov.add

import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class TestAddServiceProviderTest {
  lateinit var provider: TestAddServiceProvider

  @Before
  fun setUp() {
    provider = TestAddServiceProvider()
    provider.start()
  }

  @After
  fun tearDown() {
    provider.stop()
  }

  @Test
  fun addTwoNumbers() {
    val result = runBlocking {
      provider.client.calculate(1, 2)
    }
    assertEquals(3, result)
  }
}