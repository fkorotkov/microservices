package com.fkorotkov.multiply.client

interface MultiplyServiceClient {
  suspend fun calculate(a: Long, b: Long): Long
}
