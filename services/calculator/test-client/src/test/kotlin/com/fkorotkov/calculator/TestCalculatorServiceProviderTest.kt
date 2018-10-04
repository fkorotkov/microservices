package com.fkorotkov.calculator

import com.fkorotkov.add.TestAddServiceProvider
import com.fkorotkov.multiply.TestMultiplyServiceProvider
import com.fkorotkov.subtract.TestSubtractServiceProvider
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCalculatorServiceProviderTest {
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
  fun complex() = runBlocking {
    val result = calculatorProvider.client.evaluate("1+2*3-(4-5*6)")
    assertEquals(33, result)
  }

  @Test
  fun addTwoNumbers() = runBlocking {
    val result = calculatorProvider.client.evaluate("1 + 2")
    assertEquals(3, result)
  }

  @Test
  fun subtractTwoNumbers() = runBlocking {
    val result = calculatorProvider.client.evaluate("1 - 2")
    assertEquals(-1, result)
  }

  @Test
  fun multiplyTwoNumbers() = runBlocking {
    val result = calculatorProvider.client.evaluate("2 * 2")
    assertEquals(4, result)
  }
}