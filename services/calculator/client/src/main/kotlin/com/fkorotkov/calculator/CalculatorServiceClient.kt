package com.fkorotkov.calculator

interface CalculatorServiceClient {
  suspend fun evaluate(expression: String): Long
}
