package com.fkorotkov.subtract.client

interface SubtractServiceClient {
  suspend fun calculate(a: Long, b: Long): Long
}
