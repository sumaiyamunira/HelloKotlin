package com.sumaiya.hellokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sumaiya.hellokotlin.dto.Person
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    val year = getCurrentYear()
    val month = getCurrentMonth()
    val monthsArray = mutableListOf<String>("January", "February", "March", "April", "May", "June", "July")
    val numberList = listOf<Int>(200,35,5,130,150,20)
    val nameList = arrayListOf<String>("Mohammad", "Khadiza", "Anis", "Ibrahim",  "Musa")
    val personList = arrayListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val yearMsg = doYearCheck(year)
        println(yearMsg)

        val monthMsg = doMonthCheck(month)
        println(monthMsg)


        printLeapYearMsg(year, { y ->
            val isLeapYear = if (((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0)) {
                true
            } else {
                false
            }
            isLeapYear
        })

        printCurrentMonth()


        val dateTime = getCurrentDateTime()
        println("Current Date Time is " + dateTime)

        loadPersonData()
        val person : Person? = printPersonData(22)
        sortNumberAndPrint()
        test(person)

    }
    //    Example of apply, run
    private fun test(person : Person?) {
        person?.apply {
             userName = "New Name $userName"
        }

        person?.run {
            println("finally the name is $userName")
        }
    }

     //    Example of sort, with
    private fun sortNumberAndPrint() {
        println("Unsorted full list: ${numberList}")
         val firstAndLast = with(numberList) {
             "The first element is ${first()}," +
                     " the last element is ${last()}"
         }

         println(firstAndLast)
         val sortedList = numberList.sorted()
         println("Sorted full list: ${sortedList}")
    }

    //    Example of for loop
    private fun printCurrentMonth() : Unit{
        for (months in monthsArray) {
            if (months == doMonthCheck(month)) {
                println("Current month is $months")
            }
        }
    }


    //    Example of data class, let
    private fun loadPersonData() {
        for (i in 0..4){
            val user = Person (nameList[i], i+20)
            personList.add(user)
        }

        Person("Test user", 100).let {
            personList.add(it)
        }
    }

    //    Example of filter, map and do while loop data, also, print
    private fun printPersonData(age : Int): Person? {
        println("Print all user: ")
        var index = 0;
        do {
            println(personList[index])
            index++
        } while (index<personList.size)

        personList.filter {
            it.age == age
        }.map {
            return it.also {
                println("User name of age $age  $it")
            }
        }
        return null
    }


    fun getCurrentMonth(): Int {
        return Calendar.getInstance().get(Calendar.MONTH) + 1
    }

    // Example usages of library java.util and java.text.SimpleDateFormat
    fun getCurrentDateTime(): String {
        val sdf = SimpleDateFormat("YYYY MM dd hh:mm a")
        return sdf.format(Calendar.getInstance().time)
    }


    fun getCurrentYear(): Int {
        return Calendar.getInstance().get(Calendar.YEAR)
    }


    //    Example with if/else condition
    fun doYearCheck(y: Int): String {
        val msg = if (y == 2021) {
            "Happy New Year"
        } else {
            "It's not a New Year"
        }
        return msg
    }

    //    Example with switch case
    fun doMonthCheck(month: Int): String {
        val msg = when (month) {
            1 -> "January"
            2 -> "February"
            3 -> "March"
            4 -> "April"
            else -> "Invalid Month"

        }
        return msg
    }

    //    Example with Arithmatic operation
    fun doLeapYearCheck(y: Int): Boolean {
        val isLeapYear = if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
            true
        } else {
            false
        }
        return isLeapYear
    }

    //    Example of lamda & Higher Order Function
    fun printLeapYearMsg(y: Int, doLeapYearCheck: (Int) -> Boolean): Unit {
        val isTheYearLeap = doLeapYearCheck(y)
        if (isTheYearLeap) {
            println("Leap Year $y")
        } else {
            println("Not Leap Year $y")
        }
    }
}
