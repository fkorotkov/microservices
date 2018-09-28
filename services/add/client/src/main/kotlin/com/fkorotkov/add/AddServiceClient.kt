package com.fkorotkov.add

interface AddServiceClient {
  suspend fun calculate(a: Long, b: Long): Long
}
