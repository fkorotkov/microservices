package com.fkorotkov.calculator

import com.fkorotkov.add.TestAddServiceProvider
import com.fkorotkov.subtract.TestSubtractServiceProvider
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class TestCalculatorServiceProviderTest {
  lateinit var addProvider: TestAddServiceProvider
  lateinit var subtractProvider: TestSubtractServiceProvider
  lateinit var calculatorProvider: TestCalculatorServiceProvider

  @Before
  fun setUp() {
    addProvider = TestAddServiceProvider()
    addProvider.start()
    subtractProvider = TestSubtractServiceProvider()
    subtractProvider.start()
    calculatorProvider = TestCalculatorServiceProvider(addProvider.client, subtractProvider.client)
    calculatorProvider.start()
  }

  @After
  fun tearDown() {
    calculatorProvider.stop()
    addProvider.stop()
    subtractProvider.stop()
  }

  @Test
  fun addTwoNumbers() {
    val result = runBlocking {
      calculatorProvider.client.evaluate("1 + 2")
    }
    assertEquals(3, result)
  }

  @Test
  fun subtractTwoNumbers() {
    val result = runBlocking {
      calculatorProvider.client.evaluate("1 - 2")
    }
    assertEquals(-1, result)
  }
}