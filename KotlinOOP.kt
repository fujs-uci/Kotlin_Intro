package com.example.helloworld

import android.os.DropBoxManager
import java.lang.ArithmeticException
import java.lang.IllegalArgumentException

fun personEx() {
    // Creating an instance of a class
    var justin = Person("Justin", "California")
    var john = Person()
    var johnPeterson = Person(lastName="Peterson")

    // Exercise 46
    var phoneOne = MobilePhone("android", "Google", "pixel")
    var phoneTwo = MobilePhone("android", "Google", "pixel 2")
    var phoneThree = MobilePhone("android", "Google", "pixel 3")

    // Class properties and methods
    justin.stateHobby()
    justin.hobby = "Dancing"
    justin.stateHobby()

    // Using secondary Constructor
    var steven = Person("Steven", "Lim",  24)
}

// Classes and Initializers
class Person(firstName: String="John", lastName: String="Doe") {
    // Member Variables: Class Properties
    var age : Int? = null
    var hobby : String = "watch Netflix"
    var firstName : String? = null
    var lastName : String? = null

    // Initializer block
    init {
        this.firstName = firstName
        this.lastName = lastName
        println("$firstName $lastName is born.")
    }

    // Member secondary Constructor
    constructor(firstName: String, lastName: String, age: Int) : this(firstName, lastName){
        this.age = age
        println("$firstName $lastName is $age years old.")
    }

    // Member Functions: Class Methods
    fun stateHobby(){
        println("$firstName hobby is $hobby.")
    }
}

// Exercise 46
class MobilePhone(osName: String, brand:String, model:String) {
    init {
        println("$brand $model operates with $osName.")
    }
}

// Late init, getters and setters
fun video51(){
    var myCar = Car()
    myCar.owner
    println("brand is : ${myCar.myBrand}")
    myCar.maxSpeed = 200 // set()
    println("My car's speed is ${myCar.maxSpeed}") // get()
}

class Car(){
    // declare variable owner w/o initializing it
    lateinit var owner : String
    val myBrand : String = "BMW"
        // Custom gettier
        get() {
            return field.toLowerCase()
        }

    var maxSpeed: Int = 250
        get() = field
        set(value) {
            field = if(value > 0) value else throw IllegalArgumentException("Maxspeed cannot be 0")
        }

    var myModel : String = "M5"
        private set // Makes the setter only available inside the class

    init {
        this.myModel = "M3"
        this.owner = "Frank"
    }
}

// Data classes

// data class min 1 parameter
// data class inherits from class Any
data class User(val id: Long, var name: String)

fun dataClasses(){
    val user1 = User(1, "Justin")

    user1.name = "Michael"
    val user2 = User(1, "Michael")
    println(user1 == user2)
    println(user1)

    // .copy()
    val updatedUser = user1.copy(name = "Justin Lim")
    println(user1)
    println(updatedUser)

    // .component1()
    println(updatedUser.component1())
    println(updatedUser.component2())

    val (id, name) = updatedUser
    println("$id and $name")
}

// Classes Exercise
class MobilePhone2(osName: String, brand: String, model: String){
    init {
        println("The phone $model from $brand uses $osName as its Operating System")
    }

    private var battery : Int = 25

    fun chargeBattery(charged : Int){
        println("Current Phone battery: $battery")
        this.battery += charged
        println("Charged Phone now at: $battery")

    }
}

fun classesEx(){
    var myPhone = MobilePhone2("Android", "Google", "pixel 3")
    myPhone.chargeBattery(50)
}

// Inheritance
// All class by default are final
// use open to explicitly define a class as inheritable
open class Vehicle { // Super/Parent/Base class

}

open class Bike(val name: String, val brand: String)
    : Vehicle() { //Sub/Child/Derived class of Vehicle
    open var range: Double = 0.0 // open allows for sub classes to override

    fun extendRange(amount: Double) {
        if (amount>0)
            range += amount
    }

    open fun drive(distance: Double){
        println("Drove for $distance")
    }
}

open class ElectricBike(name: String, brand:String, batteryLife: Double)
    : Bike(name, brand) { //Sub/Child/Derived class of Bike

    override var range = batteryLife * 6

    override fun drive(distance: Double){
        println("Drove for $distance with electricity.")
    }

    fun drive(){
        println("My Ebike drove for $range")
    }
}

fun inheritanceEx(){
    var myBike = Bike("Fire", brand="Audi")
    var myEBike = ElectricBike("s-3", "Tesla", 200.0)

    // Polymorphism
    // Different Classes using the same methods because they derive from the same parent class
    myBike.drive(200.0)
    myEBike.drive(200.0)
    myEBike.drive()

}

// Interfaces: extend functionality of classes
// BLue print for classes to inherit from and perform polymorphism
interface Drivable{
    val maxSpeed: Double
    fun drive(): String
    fun brake(){
        println("The drivable is braking")
    }
}

open class Boat(name: String, override val maxSpeed: Double) : Drivable {
    // override fun drive(): String = "Boat drove"
    override fun drive(): String{ // defining interface funciton
        return "Boat drove."
    }
}

open class ElectricBoat(batteryLife: Double, name: String, override val maxSpeed: Double)
    : Boat(name, maxSpeed) {

    override fun drive(): String = "EBoat drove." // defining interface function

    override fun brake(){
        super.brake() // uses code from the interface function
        println("Electric car uses battery to brake.")
    }
}

fun interfaceEx(){
    var boat1 = Boat("TIlly", 100.0)
    var eboat1 = ElectricBoat(200.0, "Tam", 250.0)

    println(boat1.drive())
    println(eboat1.drive())
    boat1.brake()
    eboat1.brake()
}

// Abstract Classes
fun abstractEx(){
    val human = Human("Justin", "California", 165.0, 10.0)
    val elephant = Elephant("Jim", "Cambodia", 3000.00, 25.0)

    human.run()
    elephant.run()

    human.breath()
    elephant.breath()

    // Code below will not work because Mammal is abstract
    // val mammal = Mammal("Steve", 200.00, 55.0)
}

// Abstract cannot be instantiated
// can inherit subclasses from abstract
// properties and methods are not abstract unless explicitly stated
abstract class Mammal(private val name: String,
                      private val origin: String,
                      private val weight: Double){

    // Abstract property: must be overridden by subclass
    abstract var maxSpeed: Double

    // Abstract Methods: must be implemented by subclasses
    abstract fun run()
    abstract fun breath()

    // Concrete (non abstract) methods
    fun displayDetails(){
        println("Name: $name, Origin: $origin, Weight: $weight, Max speed: $maxSpeed")
    }
}

class Human(name:String, origin:String, weight: Double, override var maxSpeed: Double) :
        Mammal(name, origin, weight){

    override fun run(){
        println("Human runs on two legs")
    }

    override  fun breath(){
        println("Human breaths air with lungs")
    }
}

class Elephant(name:String, origin:String, weight: Double, override var maxSpeed: Double) :
    Mammal(name, origin, weight){

    override fun run(){
        println("Elephant runs on four legs")
    }

    override fun breath(){
        println("Elephant breaths air with lungs")
    }
}

// Type Casting

fun typeCastingEx(){
    val stringList: List<String> = listOf("Justin", "Steve", "Tom")
    val mixedList: List<Any> = listOf("Justin", 25, 10.001, 'A')

    for(value in mixedList){
        if(value is Int){
            println("Integer")
        } else if(value is Double){
            println("Double")
        } else if(value is String){
            println("String")
        } else {
            println("Unknown")
        }
    }

    // using when
    for(value in mixedList){
        when(value){
            is Int -> println("Integer")
            is Double -> println("Double")
            is String -> println("String")
            else -> println("Unknown")
        }
    }

    // Smart cast
    // obj1 decalred as type Any, but kotlin will detect String
    val obj1: Any = "I have a dream"
    if(obj1 !is String){
        println("Not string")
    } else {
        println(obj1.length)
    }

    // Explicit (UNSAFE) Casting with "as"
    // Will work is obj1 is string, other wise throw Exception
    val str1: String = obj1 as String
    println(str1.length)

    // code below throws e because Explicit casting an Int to String
    // val obj2: Any = 1337
    // val str2: String = obj2 as String
    // println(str2)

    // Explicit (SAFE) casting with "as?"
    val obj3: Any = 1337
    val str3: String? = obj3 as? String // works
    println(str3)
}

// Array Lists
fun arrayListEx(){
    // ArrayList: read & write functions, sequence of insertion order, non synchronized may contain duplicates
    // ArrayList are dynamic arrays that can increase/decrease size requirements
    // .add(E), .clear(), .get(E), remove(E)

    // empty arraylist
    val arrayList = ArrayList<String>()
    arrayList.add("one")
    arrayList.add("two")

    for(i in arrayList){
        print("$i ")
    }

    print("\n")
    // using collections
    val arrayList2: ArrayList<String> = ArrayList<String>(5)
    var list1: MutableList<String> = mutableListOf<String>()

    list1.add("one")
    list1.add("two")

    arrayList2.addAll(list1)

    val itr = arrayList.iterator()
    while(itr.hasNext()){
        print("${itr.next()} ")
    }
    println("\nSize of array: ${arrayList2.size}")

    // get()
    println("${arrayList2.get(1)} == ${arrayList2[1]}")

    // arrayList Excercise
    println("ArrayList Exercise: ${arrayListAvg(2.33, 4.55, 6.77, 8.99, 10.11)}")
}
// arrayList Exercise
fun arrayListAvg(one: Double, two: Double, three: Double, four: Double, five: Double) : Double{
    val arrayList: ArrayList<Double> = ArrayList<Double>(5)
    arrayList.add(one)
    arrayList.add(two)
    arrayList.add(three)
    arrayList.add(four)
    arrayList.add(five)
    var total: Double = 0.0
    for(i in arrayList){
        total += i
    }
    return total/arrayList.size
}

// Lambda Expressions
fun lambdaEx(){
    // Lambda expression is a function w/ no name
    // function literals passed as an expression and not declared
    // val name(variables) -> return = {varaibles -> body of lamnda}

    val addNumberResult: Int = addNumber(5, 3)
    println("Using a funcion: 5 + 3 = $addNumberResult")

    val lambdaResult: (Int, Int) -> Int = {a: Int, b: Int -> a + b}
    println("Using lambda 1: 5 + 3 = ${lambdaResult(5, 3)}")

    val lambdaResult2 = {a: Int, b: Int -> a+b}
    println("Using lambda 2: 5 + 3 = ${lambdaResult2(5,3)}")

}

fun addNumber(a: Int, b: Int) : Int {
    return a + b
}

// Visibility Modifiers
// keywords used to restrict use of classes, interface, methods, and properties
// public, private, protected, internal

fun visibilityModifierEx(){

    // Public can be accessed out of the scope it was declared
    val publicObject: PublicClass = PublicClass()
    println(publicObject.publicProperty)
    println(publicObject.PublicMethod())

    // Private cannot be accessed out of the scope it was declared
    val privateObject: PrivateClass = PrivateClass()
    //println(privateObject.privateProperty)  // Will not work
    //println(privateObject.PrivateMethod())  // Will not word
    println(privateObject.usePrivateMembers())

    // Protected inheritance
    val protectedClass: DerivedProtected = DerivedProtected()
    println("This is protected: ${protectedClass.getValue()}")
    val openProtectedClass: OpenDerivedProtected = OpenDerivedProtected()
    println("This is protected open: ${openProtectedClass.getValue()}")

}

// public is accessible everywhere, implied default modifier
public class PublicClass{
    val publicProperty: String = "This is public"

    public fun PublicMethod() : String{
        return "This is public as well"
    }
}

// private is accessible only within scope
private class PrivateClass{
    private val privateProperty: String = "This is private"

    private fun privateMethod(): String{
        return "This is private as well"
    }

    fun usePrivateMembers(): String{
        return "Public method calling private members: $privateProperty ${privateMethod()}"
    }
}

// internal makes the field visible only inside module it is implemented in

// open
// Kotlin all classes are final by default and cannot be inheritable by default
// Java is opposite

// protected with class/interface give visibility to its class or subclass only
// protected declaration in subclasses are also protected
open class OpenProtected{
    var g = 1 // public by default thus visible to all
    private var h = 2 // only visible to OpenProtected
    protected val i = 0 // visible but final to subclasses
    protected open val j = 5 // visible and open to override
    internal val k = 6 // visible inside the same module/file
}

class DerivedProtected: OpenProtected(){
    // g, i, j, k = visible
    // h = hidden
    fun getValue() : Int {
        return i
    }
}

class OpenDerivedProtected: OpenProtected(){
    // g, i, j, k = visible
    // h = hidden
    fun getValue(): Int{
        return j
    }
    override val j = 10  // j is open
}

// Nested Class
// A class defined inside another class
// nested class by default is static: members can be accessed without instantiating an object
// nested class cannot accesses outclass members

class OuterClass{
    private var name: String = "Private to Outerclass"

    class NestedClass{
        var nestedDescription: String = "Nested Description"
        private var nestedId: Int = 10

        fun nestedFun() {
            //print("name is $name) // Will ot work
            println("Id is $nestedId")
        }
    }

    // inner class: cannot be declared inside interfaces or non-inner nested classes
    // inner classes can accesses parent class members, even if private
    // inner classes carry a reference of outerclass object
    inner class InnerClass{
        var innerDescription: String = "Inner Description"
        private var innerId: Int = 11

        fun innerFun(){
            println("name is $name") // can access parent private property
            println("Id = $innerId")
        }
    }
}

fun nestedClassEx(){
    // accessing nested class property w/o object instantiation
    println(OuterClass.NestedClass().nestedDescription)
    val obj = OuterClass.NestedClass() // object creation
    obj.nestedFun() // access member function

    // OuterClass(). instead of OuterClass.
    println(OuterClass().InnerClass().innerDescription)
    val obj2 = OuterClass().InnerClass() // object creation with reference to parent class
    obj2.innerFun() // access member function

}

// Save cast and Unsafe cast operator

fun safeUnsafeCastEx(){
    // as is unsafe cast operator
    var obj: Any? = null
    // val str: String = obj as String // will throw error
    val obj1: Any = 123
    // val str: String = obj1 as String // will throw error

    val obj2: Any? = "String unsafe cast"
    val str1: String? = obj2 as String

    // as? is safe cast operator
    val location: Any = "Kotlin"
    val safeString: String? = location as? String
    val safeInt: Int? = location as? Int
    println(safeString) // returns Kotlin
    println(safeInt) // returns null


}


// Exception handling
// Try, catch, finally, throw
// unchecked exceptions extend RunTimeException
    // ArithmeticException, ArrayIndexOutOfBoundException, SecurityException. NullPointerException
// checked exception extends Throwable class
    // IOexception, SQLexception
fun getNumber(str: String): Int{
    return try{ Integer.parseInt(str) } catch (e: NumberFormatException){ 0 }
}

// Multiple catches for different exception errors
fun multGetNUmber(str:String): Int{
    try{
        return Integer.parseInt(str)
    }catch(e: ArithmeticException){
        return 1
    }catch(e: NumberFormatException){
        return 2
    }
}

// Nested try catch
fun nestedGetNumber(str: String): Int{
    return try{
        try{
            Integer.parseInt(str)
        }catch(e: NumberFormatException){
            1
        }
    }catch(e: ArithmeticException){
        2
    }
}

// finally try catch
fun finallyGetNumber(str: String): Int{
    return try{
        Integer.parseInt(str)
    }catch(e: NumberFormatException){
        1
    }finally {
        print(" Finally ")
    }
}

// Throw example
fun throwGetNumber(age: Int): Int{
    if(age < 18){
        throw ArithmeticException("Number too small")
    }else{
        return age
    }
}

fun catchThrowGetNumber(age: Int): Int{
    return try{
        throwGetNumber(age)
    }catch (e: ArithmeticException){
        1
    }
}
fun exceptionEx(){
    println(getNumber("10"))
    println(getNumber("10.0"))

    println(multGetNUmber("10"))
    println(multGetNUmber("10.0"))

    println(nestedGetNumber("10"))
    println(nestedGetNumber("10.0"))

    println(finallyGetNumber("10"))
    println(finallyGetNumber("10.0"))

    println(catchThrowGetNumber(18))
    println(catchThrowGetNumber(10))
}
