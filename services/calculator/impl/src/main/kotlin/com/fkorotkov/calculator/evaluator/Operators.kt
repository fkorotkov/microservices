package com.fkorotkov.calculator.evaluator

import com.fathzer.soft.javaluator.BracketPair
import com.fathzer.soft.javaluator.Operator
import com.fathzer.soft.javaluator.Parameters

object Operators {
  // see precedence here: https://introcs.cs.princeton.edu/java/11precedence/
  val PLUS = Operator("+", 2, Operator.Associativity.LEFT, 8)
  val MINUS = Operator("-", 2, Operator.Associativity.LEFT, 8)
  val MULTIPLY = Operator("*", 2, Operator.Associativity.LEFT, 12)

  val defaultParameters =
      Parameters().apply {
        add(PLUS)
        add(MINUS)
        add(MULTIPLY)
        addExpressionBracket(BracketPair.PARENTHESES)
      }
}
