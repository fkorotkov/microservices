package com.fkorotkov.subtract

interface SubtractServiceClient {
  suspend fun calculate(a: Long, b: Long): Long
}
