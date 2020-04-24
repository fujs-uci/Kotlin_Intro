package com.example.helloworld

fun basics() {
    val myName = "Justin" //val = immutable, var = mutable
    print("Hello " + myName + "!")

    // String
    val aString = "This is a string"
    val charInString = aString[0]
    val lastCharInString = aString[aString.length-1]

    // Char: single quotes exclusive to char
    val aChar: Char = 'a'
    val bChar = '1'
    val cChar = '$'

    // Integer: default in Int
    val aByte: Byte = 13 // 8 bit
    val aShort: Short = 125 // 16 bit
    val aInt: Int = 123123123 // 32 bit
    val aLong: Long = 123_123_123_123_123_123// 64 bit

    // Floating point: default is double unless F is explicit
    val aFloat: Float = 10.25F
    val aDouble: Double = 3.1459265

    // Boolean
    var aBoolean: Boolean = true
    aBoolean = false

    // Basic Data types Exercise
    var eCourse: String = "Android Masterclass"
    var eFloat: Float = 13.37F
    val ePie: Double = 3.14159265358979
    var eByte: Byte = 25
    var eShort: Short = 2020
    var eLong: Long = 18881234567
    var eBool: Boolean = true
    var eChar: Char = 'a'

    // Operators (+, -, *, /, %)
    var addition = 5+8
    var subtraction = 10-5
    var multiplication = 2*3
    var division = 12/4
    var modular = 10%7

    // Comparison Operators (==, !=, <=, >=, <, >)
    val isEqual = 5==3
    val notEqual = 5!=3
    val greaterEqual = 5>=3
    val lesserEqual = 5<=3
    val isGreater = 5>3
    val isLesser = 5<3

    // String interpolation
    println("Is equal: $isEqual")
    println("Is not equal ${5!=3}")

    // Assignment Operators (+=, -=, *=, /= %=)
    var testNum = 10
    testNum += 5
    print(testNum)
    testNum -= 3
    print(testNum)
    testNum *= 4
    print(testNum)
    testNum %= 4
    print(testNum)
    testNum /= 2
    print(testNum)

    // Increment ++ and Decrement --
    var incNum = 10
    println("Increment: $incNum")
    println("Increment: ${incNum++}")
    println("Increment: ${++incNum}")

    var decNum = incNum
    println("Decrement: ${--decNum}")

    // If statements
    var curHeight = 15.25
    var nexHeight = 20.75

    if (curHeight > nexHeight){
        println("Talller: $curHeight")
    } else if (curHeight < nexHeight){
        println("Taller: $nexHeight")
    } else{
        println("Same height")
    }

    var curName = "Justin"
    if (curName == "Justin") {
        println("Welcome home.")
    } else {
        println("Access denied.")
    }

    var testBool = true
    if (testBool) {
        println("This is true")
    }

    // If statement exercise
    var curAge = 22

    if (curAge >= 21) {
        println("You may drink")
    } else if (curAge >= 18) {
        println("You may vote")
    } else if (curAge >= 16) {
        println("You can drive")
    } else {
        println("You are a minor")
    }

    // when expression: executes top to bottom, stop when a section is true
    var season = 3
    when(season) {
        1 -> println("Spring")
        2 -> println("Summer")
        3 -> {
            println("Fall")
            println("Autumn")
        }
        4 -> {
            println("Winter")
        }
        else -> println("Invalid")
    }
    var curMonth = 3
    when(curMonth) {
        in 3..5 -> println("Spring")
        in 6..8 -> println("Summer")
        in 9..11 -> println("Fall")
        in 12 downTo 2 -> println("Winter") // 12, 1, 2 -> println("Winter")
    }

    // When expression exercise
    var nexAge = 16
    when(nexAge) {
        !in 0..20 -> {
            println("You can drink, vote, and drive")
        }
        in 20 downTo 16 -> {
            println("You can vote and drive")
        }
        in 17 downTo 16 -> {
            println("You can drive")
        }
        else -> println("You are a minor")
    }

    // Any and Is
    var checkType: Any = 13.37
    when(checkType) {
        is Int -> println("$checkType is Integer")
        is Double -> println("$checkType is Double")
        is String -> println("$checkType is String")
        !is Float -> println("$checkType is not a float")
        else -> println("$checkType is Not applicable")
    }

    // While loop: Loops a block of code as long as a chosen condition is true
    var whileVar = 0
    while(whileVar <= 10) {
        println("$whileVar is the number")
        whileVar++
    }

    // While loop exercise
    var whileEx = 100
    while(whileEx >= 0) {
        if(whileEx%2==0){
            print("$whileEx, ")
        }
        if(whileEx%20==0){
            print("\n")
        }
        whileEx--
    }

    // Do While: execute the block of code, then if the condition is true, loop the block
    var doWhileEx = 0
    do {
        println("$doWhileEx is the current number")
        doWhileEx++
    } while(doWhileEx<3)

    // For loops
    for(num in 1..10){
        print("$num")
    }

    for(num in 1 until 10){  // 1.until(10)
        print("$num")
    }

    for(num in 10 downTo 1){
        print("$num")
    }

    for( num in 10 downTo 1 step 2){ //10.downTo(1).step(2)
        print("$num")
    }

    // For loop exercise
    for(num in 0.until(10000)){
        if(num == 9001) {
            println("It is over 9000!")
        }
    }

    var humidityLevel = 80
    var humidity = "humid"

    while(humidity=="humid"){
        humidityLevel -= 5
        print("Decrease humidiy. ")
        if(humidityLevel <= 60){
            humidity = "comfy"
            print("It is $humidity now. ")
        } else {
            print("It is $humidity. ")
        }

    }
    print("\n")
}

// Functions
fun myFunction() {
    println("Called my function.")
}

// function( parameters ) : return type
fun addUp(a: Int, b:Int) : Int {
    return a+b
}

fun twoAverage(a: Double, b: Double) : Double {
    return (a + b)/2
}

fun functionsEx(){
    myFunction()
    println("${addUp(5,10)} is the result")
    println("${twoAverage(7.0, 8.0)} is the average")
}

fun nullablesEx(){
    //Nullable
    var myName = "Justin"

    // adding a question mark means it can be null
    var myNameNullable : String? = "Justin"
    myNameNullable = "Not null"
    println("$myName is myname.")
    println("$myNameNullable is a null.")

    println("${myName.length} is how long my name is.")
    println("${myNameNullable?.length} is how long null is.")

    // Let is a lambda function, where "it" becomes the variable name for the parent
    myNameNullable?.let{
        println(it.length)
    }

    // Elvis operator
    // If null, use the provided value, else existing value
    var nullableName = myNameNullable ?: "Is null"
    println(nullableName)

    // Not null saftey, if nullableName is null, below will throw exception
    nullableName!!.toLowerCase()
}