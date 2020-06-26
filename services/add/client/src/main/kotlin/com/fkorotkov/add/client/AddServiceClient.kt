package com.fkorotkov.add.client

interface AddServiceClient {
  suspend fun calculate(a: Long, b: Long): Long
}
