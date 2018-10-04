@file:Suppress("DEPRECATION")

package com.fkorotkov.calculator

import com.fkorotkov.add.TestAddServiceProvider
import com.fkorotkov.multiply.TestMultiplyServiceProvider
import com.fkorotkov.subtract.TestSubtractServiceProvider
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class KotlinConfDemoTest {
  lateinit var addProvider: TestAddServiceProvider
  lateinit var subtractProvider: TestSubtractServiceProvider
  lateinit var multiplyProvider: TestMultiplyServiceProvider
  lateinit var calculatorProvider: TestCalculatorServiceProvider

  @Before
  fun setUp() {
    addProvider = TestAddServiceProvider()
    addProvider.start()
    subtractProvider = TestSubtractServiceProvider()
    subtractProvider.start()
    multiplyProvider = TestMultiplyServiceProvider()
    multiplyProvider.start()
    calculatorProvider = TestCalculatorServiceProvider(addProvider.client, subtractProvider.client, multiplyProvider.client)
    calculatorProvider.start()
  }

  @After
  fun tearDown() {
    calculatorProvider.stop()
    addProvider.stop()
    multiplyProvider.stop()
    subtractProvider.stop()
  }

  @Test
  fun demo() = runBlocking {
    val result = calculatorProvider.client.evaluate("(1+2)*(3+4)")
    assertEquals(21, result)
  }
}