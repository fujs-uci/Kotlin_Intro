package com.example.mycalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    /*
        can click numbers, decimal, or subtraction if tvInput.text is ""
        if tvInput is not "" and no operator exists, an operator can be added to the end
        store the string before the operator
        once operator exists, next value can be a number or decimal
        once operator exists, any operator will only replace current operator
        equal will perform the operation
     */
    private var lastNumeric = false
    private var hasDecimal = false
    private var currOperator = ""
    private var firstValue = ""
    private var lastValue = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        tvInput.append((view as Button).text)
        lastNumeric = true
        if (currOperator.contentEquals("")) {
            firstValue = tvInput.text.toString()
        } else {
            lastValue += view.text.toString()
        }
    }

    fun onClear(view: View) {
        /*
        reset all variables
        reset tvInput
         */
        tvInput.text = ""
        lastNumeric = false
        hasDecimal = false
        currOperator = ""
        firstValue = ""
        lastValue = ""
    }

    fun onDecimal(view: View) {
        /*
        A user can add a decimal once per value
        On an empty tvInput or right after an operator, the decimal will produce "0."
        A btnSubtractRes can precede the use of a decimal
         */
        if ("".contentEquals(tvInput.text) || tvInput.text.toString()
                .contentEquals(getString(R.string.btnSubtractRes))
            || (!"".contentEquals(currOperator) && "".contentEquals(lastValue))
        ) {
            val decimalValue = "0${(view as Button).text}"
            tvInput.append(decimalValue)
            hasDecimal = true
            if ("".contentEquals(currOperator)) {
                firstValue += decimalValue
            } else {
                lastValue += decimalValue
            }
        } else if (lastNumeric && !hasDecimal) {
            tvInput.append((view as Button).text)
            hasDecimal = true
        }
    }

    private fun replaceOperator(operator: String): Boolean {
        val operatorText = currOperator
        val hasMultiply = operatorText.contains(getString(R.string.btnMultiplyRes))
        val hasDivide = operatorText.contains(getString(R.string.btnDivideRes))
        val hasAddition = operatorText.contains(getString(R.string.btnAdditionRes))
        val hasSubtraction = operatorText.contains(getString(R.string.btnSubtractRes))

        return if (hasMultiply || hasDivide || hasAddition || hasSubtraction) {
            currOperator = operator
            tvInput.text = firstValue + currOperator + lastValue
            true

        } else {
            false
        }
    }

    private fun insertOperator(operator: String) {
        firstValue = tvInput.text.toString()
        tvInput.append(operator)
        currOperator = operator
        lastNumeric = false
        hasDecimal = false
    }

    fun onDivide(view: View) {
        if (!replaceOperator("${(view as Button).text}")) {
            insertOperator(getString(R.string.btnDivideRes))
        }
    }

    fun onAddition(view: View) {
        if (!replaceOperator("${(view as Button).text}")) {
            insertOperator(getString(R.string.btnAdditionRes))
        }
    }

    fun onSubtraction(view: View) {
        /*
        Can be the first thing on a tvInput
        Cannot be added to the second value after an operator
         */
        if ("".contentEquals(currOperator) && "".contentEquals(tvInput.text)) {
            tvInput.append("-")
        } else if (!replaceOperator("${(view as Button).text}") && lastNumeric) {
            insertOperator(getString(R.string.btnSubtractRes))
        }
    }

    fun onMultiply(view: View) {
        if (!replaceOperator("${(view as Button).text}")) {
            insertOperator(getString(R.string.btnMultiplyRes))
        }
    }

    fun onEqual(view: View) {
        /*
        use double if first or last value have a decimal
        check if firstValue is negative
         */
        val checkOperator = currOperator.contentEquals("")
        val checkFirstValue = firstValue.contentEquals("")
        val checkLastValue = lastValue.contentEquals("")

        if (!checkOperator && !checkFirstValue && !checkLastValue) {
            val firstNum = firstValue.toDouble()
            val lastNum = lastValue.toDouble()

            if (lastNum.compareTo(0) == 0 && currOperator.contentEquals(getString(R.string.btnDivideRes))) {
                Toast.makeText(
                    this,
                    "Dividing by zero is undefined.",
                    Toast.LENGTH_SHORT).show()

            } else {
                val result = when (currOperator) {
                    getString(R.string.btnAdditionRes) -> "${firstNum + lastNum}"
                    getString(R.string.btnSubtractRes) -> "${firstNum - lastNum}"
                    getString(R.string.btnMultiplyRes) -> "${firstNum * lastNum}"
                    else -> "${firstNum / lastNum}"
                }

                onClear(view)
                firstValue = result
                lastNumeric = true
                tvInput.text = result
            }
        }
    }
}
