package com.fkorotkov.calculator.client

interface CalculatorServiceClient {
  suspend fun evaluate(expression: String): Long
}
