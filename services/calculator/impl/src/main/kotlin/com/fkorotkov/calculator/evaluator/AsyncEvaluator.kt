package com.fkorotkov.calculator.evaluator

import com.fathzer.soft.javaluator.AbstractEvaluator
import com.fathzer.soft.javaluator.Operator
import com.fkorotkov.add.AddServiceClient
import com.fkorotkov.multiply.MultiplyServiceClient
import com.fkorotkov.subtract.SubtractServiceClient
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

class AsyncEvaluator(
  val addServiceClient: AddServiceClient,
  val subtractServiceClient: SubtractServiceClient,
  val multiplyServiceClient: MultiplyServiceClient
) : AbstractEvaluator<Deferred<Long>>(Operators.defaultParameters) {
  override fun toValue(literal: String, evaluationContext: Any?): Deferred<Long> = async { literal.toLong() }

  override fun evaluate(operator: Operator, operands: MutableIterator<Deferred<Long>>, evaluationContext: Any?): Deferred<Long> {
    return async {
      val a = operands.next().await()
      val b = operands.next().await()
      when (operator) {
        Operators.PLUS -> addServiceClient.calculate(a, b)
        Operators.MINUS -> subtractServiceClient.calculate(a, b)
        Operators.MULTIPLY -> multiplyServiceClient.calculate(a, b)
        else -> throw IllegalStateException("Unknown operator ${operator.symbol}")
      }
    }
  }
}