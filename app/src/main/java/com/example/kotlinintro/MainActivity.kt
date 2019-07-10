package com.example.kotlinintro

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.IllegalArgumentException
import kotlin.math.max

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ifFlowControl()
        whenExpressionRatherThanSwitch()
        functionInKotlin() // in kotlin we can use fun outside of the class or inside of the class
        advancedFunctionInKotlin()
        lamdaInKotlin()
        nestedFunction()
        extensionFunction()
        higherOrderFunction()

        // how to use a class in kotlin
        var traditionalclass: TraditionalClass = TraditionalClass("test article", 30)

        // use a class with custom setter and getter
        val primaryConstructorClassWithSetterAndGetter = PrimaryConstructorClassWithSetterAndGetter("test")
        primaryConstructorClassWithSetterAndGetter.length = 50

        // use a class with secondary constructor and infinite constructor and how to access it
        val secondaryConstructorClass1 = SecondaryConstructorClass("title", 20)
        val secondaryConstructorClass2 = SecondaryConstructorClass("2and Title", 50, true)
        val secondaryConstructorClass3 = SecondaryConstructorClass("3and Title", 100, true, "John Doe")
        println(secondaryConstructorClass3.author)

        // *** Any type is equvalent of object in java

        // object is act like singlton is java -> the
        A.Funct()
        Constants.URL

        // use of companion object
        val person = Person.PersonFactory.create("Abozar", "raghib")

        // null safety in kotlin
        // with ? we can declare that the variable can have a null value
        val name: String? = null // name can have a string value or can be null

        // null safety call in kotlin
        println(name?.length) // this means that print the length of name of the name is not null

        val n: String =
            name ?: "null name" // if the name is not null put name in n otherwise put "null name" in n variable

        // access to enum class
        val d: Direction = Direction.UP
        // or we can access the value of enum by calling method enumValueOf
        enumValueOf<Direction>("UP")
        // method values return all the values in Direction enum class
        Direction.values()
        // we can access the code value inside of enum class Direction that have constructor
        val up = Direction.UP
        println(up.code)


    }

    private fun ifFlowControl() {
        val a = 1
        val b = 10

        println(maxOfTwoVal(a, b))
        Log.d("the max is", maxOfTwoVal(a, b).toString())
    }

    private fun maxOfTwoVal(a: Int, b: Int): Int {
        return if (a > b) {
            a
        } else {
            b
        }
    }

    // we can use when instead of if-else statement
    private fun whenExpressionRatherThanSwitch() {
        var arrayAny = arrayOf<Any?>(1, 2.0, 5F, "", true)
        for (i in arrayAny)
            when (i) {
                is String -> println("is String")
                is Int -> println("is Int")
                is Float, is Double -> println("is Float/Double")
                is Double -> println("is Double")
                is Boolean -> println("is Boolean")
                else -> println("is a null?")
            }

        val validNumbers = arrayOf(35)
        var arrayNumber = arrayOf(1, 2, 19, 20, 14, 35, 45)
        for (obj in arrayNumber)
            when (obj) {
                in 1..10 -> println("x is in the range")
                in validNumbers -> println("x is valide")
                !in 10..20 -> println("x is outside the range")
                else -> println("none of the above")
            }

        var arrayForLoops = arrayOf(12, 2.3, 45F, "a String", true, null)
        // arrayForLoops.indices => return the index of each element
        for (a in arrayForLoops.indices) {
            print(a) // print the index of each element
            println(arrayForLoops[a])  // print the value of each index
        }
        // or we can show the two above line and for loops with below code way
        for ((a, b) in arrayForLoops.withIndex()) {
            println("$a $b")
        }

        // the basic use of while loop
        var i = 5
        while (0 <= i) {
            println(i--)
        }

        var arraySafety = arrayOf(1, 2, 3, 4, 5, null)
        val iter = arraySafety.iterator()
        while (iter.hasNext()) {
            println("the iterator is ${iter.next()}")
        }

        // "do" run before check while
//        do {
//            val y = iter.next()
//            println(y)
//        } while (y != null)

        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 2) {
                println("Exit implicit return")
                return@forEach
            }
            println("the list of item is $it")
        }

    }


}

private fun functionInKotlin() {
    println(avg(12.0, 10.0))
    displayGreeting(
        message = "How are you",
        name = "Abozr"
    ) // we can use parameter in a disorder change but we must declare with name of parameters
    sum(1.0, 2.1, 3.3, 4.2, 5.4, 6.0) // use of vararg type -> which means we can use different values for fun

    val v = doubleArrayOf(1.0, 2.2, 3.3, 4.4)
    println(sum(*v))
}

fun avg(a: Double, b: Double): Double {
    return (a + b) / 2
}

// single expression function
fun avg2(a: Double, b: Double): Double = (a + b) / 2

// if the return type of a function is nothing we should use "unit" -> "void" in java , it is completely optional
fun printHello(s: String): Unit {
    println("Hello, $s")
}

// kotlin support default arguments in function decoration -> in the below function the parameter name has default value
fun displayGreeting(name: String = "Guest", message: String) {
    println("Hello $name, $message")
}

// varard type is the sequence of data in a single variable
fun sum(vararg num: Double): Double {
    return num.sum()
}

fun advancedFunctionInKotlin() {
    val (x, y) = sumMult(10, 20)
    println("$x $y")

    val (m, n, o) = sumMultSub(10, 20)
    println("$m $n $o")

}

fun sumMult(a: Int, b: Int): Pair<Int, Int> {
    return Pair(a + b, a * b)
}

fun sumMultSub(a: Int, b: Int): Triple<Int, Int, Int> {
    return Triple(a + b, a * b, a - b)
}

fun lamdaInKotlin() {
    val msg = { msg: String ->
        println("Hello $msg")
    }
    msg("Abozar")

    val list = listOf("Hello", "There", "Man")
    println(list.last())
    println(list.last { it.length == 5 })
    // it keyword is something like this in java
}

fun nestedFunction() {
    circAndArea(20.0)
}

fun circAndArea(radius: Double) {
    fun calCirc(radius: Double): Double = (2 * Math.PI) * radius
    val c = "%.2f".format(calCirc(radius))

    fun calArea(radius: Double): Double = (Math.PI) * Math.pow(radius, 2.0)
    val a = "%.2f".format(calArea(radius))

    println("Circle circumference: $c and circle area: $a")
}

fun extensionFunction() {
    println("hello there man".upperCaseFirstLetter())
}

// extension function allows to add behavior to object without actually having to inherite the object and creating new class
// below function is an example of extension function
fun String.upperCaseFirstLetter(): String {
    return this.substring(0, 1).toUpperCase().plus(this.substring(1))
}

fun higherOrderFunction() {

    // pass function in another function
    println(
        circleOperation(
            10.0,
            ::calCirc
        )
    ) // paranthese signified that we run the function -> so we don't actually run the function the cause of we not put parantese in calCirc
    println(circleOperation(12.5, ::calArea))
}

// examples of higher order function
fun calCirc(radius: Double): Double = (2 * Math.PI) * radius

fun calArea(radius: Double): Double = (Math.PI) * Math.pow(radius, 2.0)

// the second parameter of this function is another function
fun circleOperation(radius: Double, op: (Double) -> Double): Double {
    val result = op(radius)
    return result
}

// traditional way of declare classs and constructor
class TraditionalClass {
    var title: String
    var length: Int

    constructor(title: String, length: Int) {
        this.title = title
        this.length = length
    }
}

// *** another way of declare class is like below -> that called primary constructor, so parameters can get default value
// *** we can declare custom setter and getter
class PrimaryConstructorClassWithSetterAndGetter(var title: String = "Default Title") {

    var length: Int = 0
        // create custom setter for variable length
        set(length) {
            if (length.equals(0)) {
                throw IllegalArgumentException("Length must not be empty")
            }
            field = length
        }
        get() {
            // inc method increase one number to length
            return field.inc()
        }
}

// *** create a class with secondary constructor
class SecondaryConstructorClass(var title: String = "Default Title", length: Int) {
    var published: Boolean = true
    var author: String = ""

    // *** this is a secondary constructor the return of this constructor is name and length -> this refer to the class name which means SecondaryConstructorClass
    constructor(name: String, length: Int, published: Boolean) : this(name, length) {
        this.published = published
    }

    // infinite constructor
    constructor(name: String, length: Int, published: Boolean, author: String) : this(name, length, published) {
        this.author = author
    }
}

// *** object is something like singelton in java -> when we create object there is only one instance of it
object A {
    fun Funct() {

    }
}

object Constants {
    val URL: String = "http://www.google.com"
}

// *** Kotlin doesn't support static method or classes so instead of static we have companion object in kotlin
// *** companion object is a an object that belong to it class -> they are singleton actually , the class that have companion object is a companion class
// *** companion objects is lazy that means they will be instantiated only we needed for the first time
class Person private constructor(var fName: String, var lName: String) {
    init {
        // the init block run every time the Person class instantiated
        count++
    }

    // we can name for companion object
    companion object PersonFactory {
        var count: Int = 0
        // inside of create fun we instance of a Person class
        fun create(fName: String, lName: String): Person = Person(fName, lName)

        init {
            println("Created Object")
        }
    }
}

// use of data class instead of setter and getter which means pojo modela class in java
// each parameters in this class has setter and getter methods
data class CreateADataClass(var fName: String, var lName: String, var age: Int, var height: Int, var weight: Int)

// *** enum class in kotlin
// *** enum class can also have constructor
enum class Direction(val code: Int) {
    UP(1),
    DOWN(2),
    LEFT(3),
    RIGHT(4),
    NONE(0)
}

// *** Sealed class is an abstract class that we have never create an object from it
// we can use sealed class to extend other classes
sealed class Animal

class Tiger : Animal()
class Cat : Animal()
class Dog : Animal()

// *** declare abstract class -> in abstract class we can define methods that have body
abstract class Company(val name: String, val employeeNumber: Int) {
    abstract fun getEarnings(): Double

    // this fun is not abstract
    fun companyName(): String {
        return name + "Inc"
    }
}

// *** interfaces are naturally abstract classes
class One

class Two
interface InterfaceClass {
    fun funOne(x: Int): One
    fun funTwo(s: String): Two

    // in interface class we can have methods that have logic like below fun
    fun funThree(x: Int): Int {
        return x
    }

    // *** interface have not state so we can not put variable inside it but we can creat a setter and getter for a variable inside of a interface
    var b: Boolean
        get() = true
        set(value) {
            b = value
        }
}

// interfaces should implement otherwise they are useless, the symbol or column : here means implement in java
// each class can implement lots of interfaces but just one class can extend
class Three : InterfaceClass {
    override fun funOne(x: Int): One {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun funTwo(s: String): Two {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun funThree(x: Int): Int {
        return super.funThree(x)
    }

    override var b: Boolean
        get() = false
        set(value) {
            b = !value
        }
}


// we can use custom type with typealias keyword
typealias Name = String

typealias Age = Int

// in this class fname and lname is a Name type that refers to String
data class UseTypeAliasClass(val fName: Name, val lName: Name, val age: Age)