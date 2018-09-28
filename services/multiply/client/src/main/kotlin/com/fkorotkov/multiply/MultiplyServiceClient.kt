package com.fkorotkov.multiply

interface MultiplyServiceClient {
  suspend fun calculate(a: Long, b: Long): Long
}
