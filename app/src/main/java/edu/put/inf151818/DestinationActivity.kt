package edu.put.inf151818

import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.threeten.bp.Month
import android.os.Bundle
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.TextView
import android.text.TextWatcher
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.temporal.ChronoUnit
import android.text.Editable

fun calculateEasterDate(year: Int): LocalDate {
    val a = year % 19
    val b = year / 100
    val c = year % 100
    val d = b / 4
    val e = b % 4
    val f = (b + 8) / 25
    val g = (b - f + 1) / 3
    val h = (19 * a + b - d - g + 15) % 30
    val i = c / 4
    val k = c % 4
    val L = (32 + 2 * e + 2 * i - h - k) % 7
    val m = (a + 11 * h + 22 * L) / 451
    val month = (h + L - 7 * m + 114) / 31
    val day = ((h + L - 7 * m + 114) % 31) + 1
    return LocalDate.of(year, month, day)
}

class DestinationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        val numberEditText = findViewById<EditText>(R.id.res)
        val increaseButton = findViewById<Button>(R.id.inc)
        val dayPicker1= findViewById<NumberPicker>(R.id.day1)
        var dayPicker2 = findViewById<NumberPicker>(R.id.day2)
        val monthPicker1 = findViewById<NumberPicker>(R.id.buisness)
        var monthPicker2 = findViewById<NumberPicker>(R.id.month2)
        val yearPicker1 = findViewById<NumberPicker>(R.id.year1)
        var yearPicker2 = findViewById<NumberPicker>(R.id.year2)

        increaseButton.setOnClickListener {
            val number = numberEditText.text.toString().toInt()

            val currentDays = dayPicker1.value
            val currentMonths = monthPicker1.value
            val currentYears = yearPicker1.value

            var date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            date1 = date1.plusDays(number.toLong())

            dayPicker2.minValue = 1
            dayPicker2.maxValue = date1.month.length(date1.isLeapYear)
            dayPicker2.value = date1.dayOfMonth

            monthPicker2.minValue = 1
            monthPicker2.maxValue = 12
            monthPicker2.value = date1.monthValue

            yearPicker2.minValue = 1900
            yearPicker2.maxValue = 2100
            yearPicker2.value = date1.year
        }



        /*numberEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used in this example
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used in this example
            }

            override fun afterTextChanged(s: Editable?) {
                val number = s.toString().toIntOrNull() ?: return

                val currentDays = dayPicker1.value
                val currentMonths = monthPicker1.value
                val currentYears = yearPicker1.value

                val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
                date1.plusDays(number.toLong())

                dayPicker2.value = date1.dayOfMonth
                monthPicker2.value = date1.monthValue
                yearPicker2.value = date1.year

                dayPicker2.setValue(date1.dayOfMonth)
                monthPicker2.setValue(date1.monthValue)
                yearPicker2.setValue(date1.year)
            }
        })*/




        val min = 1
        val maxDay=31
        val maxMonth = 12
        val maxYear = 2100
        val minYear = 1900

        dayPicker1.value=9
        dayPicker2.value=9
        monthPicker1.value=5
        monthPicker2.value=5
        yearPicker1.value=2023
        yearPicker2.value=2023

        dayPicker1.minValue=min
        dayPicker2.minValue=min
        monthPicker1.minValue=min
        monthPicker2.minValue=min
        yearPicker1.minValue=minYear
        yearPicker2.minValue=minYear

        dayPicker1.maxValue=maxDay
        dayPicker2.maxValue=maxDay
        monthPicker1.maxValue=maxMonth
        monthPicker2.maxValue=maxMonth
        yearPicker1.maxValue=maxYear
        yearPicker2.maxValue=maxYear



        monthPicker1.setOnValueChangedListener { picker, _, _ ->
            val selectedDate = picker.value
            if(selectedDate == 2) {
                dayPicker1.maxValue=28
            } else if (selectedDate == 1 || selectedDate == 3  || selectedDate == 5
                || selectedDate == 7 || selectedDate ==8 || selectedDate==10 || selectedDate==12) {
                dayPicker1.maxValue=31
            }
            else{
                dayPicker1.maxValue=30
            }

            val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val date2 = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)
            val daysDifference = ChronoUnit.DAYS.between(date1, date2)

            val startDate = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val endDate = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)

            val holidays = mutableListOf(
                LocalDate.of(2022, 12, 24)  // Example holiday date 1
            )

            var currentDate = startDate
            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    holidays.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

            val startYear = 1900
            val endYear = 2100

            for (year in startYear..endYear) {
                val easterDate = calculateEasterDate(year)
                holidays.add(easterDate)
                holidays.add(easterDate.plusDays(60))
                holidays.add(LocalDate.of(year, Month.JANUARY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 6))
                holidays.add(LocalDate.of(year, Month.MAY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 3))
                holidays.add(LocalDate.of(year, Month.AUGUST, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 26))
            }

            currentDate = startDate
            var businessDays = 0L

            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate !in holidays) {
                    businessDays++
                }
                currentDate = currentDate.plusDays(1)
            }


            val daysDifferenceTextView = findViewById<EditText>(R.id.res)
            val businessDaysDifferenceTextView = findViewById<TextView>(R.id.robocze)
            daysDifferenceTextView.setText("$daysDifference")
            businessDaysDifferenceTextView.setText("Dni roboczych pomiędzy datami: ${businessDays-1}")
        }

        monthPicker2.setOnValueChangedListener { picker, _, _ ->
            val selectedDate = picker.value
            if(selectedDate == 2) {
                dayPicker2.maxValue=28
            } else if (selectedDate == 1 || selectedDate == 3  || selectedDate == 5
                || selectedDate == 7 || selectedDate ==8 || selectedDate==10 || selectedDate==12) {
                dayPicker2.maxValue=31
            }
            else{
                dayPicker2.maxValue=30
            }

            val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val date2 = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)
            val daysDifference = ChronoUnit.DAYS.between(date1, date2)

            val startDate = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val endDate = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)

            val holidays = mutableListOf(
                LocalDate.of(2022, 12, 24)  // Example holiday date 1
            )

            var currentDate = startDate
            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    holidays.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

            val startYear = 1900
            val endYear = 2100

            for (year in startYear..endYear) {
                val easterDate = calculateEasterDate(year)
                holidays.add(easterDate)
                holidays.add(easterDate.plusDays(60))
                holidays.add(LocalDate.of(year, Month.JANUARY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 6))
                holidays.add(LocalDate.of(year, Month.MAY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 3))
                holidays.add(LocalDate.of(year, Month.AUGUST, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 26))
            }

            currentDate = startDate
            var businessDays = 0L

            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate !in holidays) {
                    businessDays++
                }
                currentDate = currentDate.plusDays(1)
            }


            val daysDifferenceTextView = findViewById<EditText>(R.id.res)
            val businessDaysDifferenceTextView = findViewById<TextView>(R.id.robocze)
            daysDifferenceTextView.setText("$daysDifference")
            businessDaysDifferenceTextView.setText("Dni roboczych pomiędzy datami: ${businessDays-1}")

        }

        dayPicker1.setOnValueChangedListener { picker, _, _ ->
            val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val date2 = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)
            val daysDifference = ChronoUnit.DAYS.between(date1, date2)

            val startDate = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val endDate = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)

            val holidays = mutableListOf(
                LocalDate.of(2022, 12, 24)  // Example holiday date 1
            )

            var currentDate = startDate
            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    holidays.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

            val startYear = 1900
            val endYear = 2100

            for (year in startYear..endYear) {
                val easterDate = calculateEasterDate(year)
                holidays.add(easterDate)
                holidays.add(easterDate.plusDays(60))
                holidays.add(LocalDate.of(year, Month.JANUARY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 6))
                holidays.add(LocalDate.of(year, Month.MAY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 3))
                holidays.add(LocalDate.of(year, Month.AUGUST, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 26))
            }

            currentDate = startDate
            var businessDays = 0L

            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate !in holidays) {
                    businessDays++
                }
                currentDate = currentDate.plusDays(1)
            }


            val daysDifferenceTextView = findViewById<EditText>(R.id.res)
            val businessDaysDifferenceTextView = findViewById<TextView>(R.id.robocze)
            daysDifferenceTextView.setText("$daysDifference")
            businessDaysDifferenceTextView.setText("Dni roboczych pomiędzy datami: ${businessDays-1}")
        }

        dayPicker2.setOnValueChangedListener { picker, _, _ ->
            val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val date2 = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)
            val daysDifference = ChronoUnit.DAYS.between(date1, date2)

            val startDate = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val endDate = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)

            val holidays = mutableListOf(
                LocalDate.of(2022, 12, 24)  // Example holiday date 1
            )

            var currentDate = startDate
            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    holidays.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

            val startYear = 1900
            val endYear = 2100

            for (year in startYear..endYear) {
                val easterDate = calculateEasterDate(year)
                holidays.add(easterDate)
                holidays.add(easterDate.plusDays(60))
                holidays.add(LocalDate.of(year, Month.JANUARY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 6))
                holidays.add(LocalDate.of(year, Month.MAY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 3))
                holidays.add(LocalDate.of(year, Month.AUGUST, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 26))
            }

            currentDate = startDate
            var businessDays = 0L

            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate !in holidays) {
                    businessDays++
                }
                currentDate = currentDate.plusDays(1)
            }


            val daysDifferenceTextView = findViewById<EditText>(R.id.res)
            val businessDaysDifferenceTextView = findViewById<TextView>(R.id.robocze)
            daysDifferenceTextView.setText("$daysDifference")
            businessDaysDifferenceTextView.setText("Dni roboczych pomiędzy datami: ${businessDays-1}")
        }

        yearPicker1.setOnValueChangedListener { picker, _, _ ->
            val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val date2 = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)
            val daysDifference = ChronoUnit.DAYS.between(date1, date2)

            val startDate = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val endDate = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)

            val holidays = mutableListOf(
                LocalDate.of(2022, 12, 24)  // Example holiday date 1
            )

            var currentDate = startDate
            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    holidays.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

            val startYear = 1900
            val endYear = 2100

            for (year in startYear..endYear) {
                val easterDate = calculateEasterDate(year)
                holidays.add(easterDate)
                holidays.add(easterDate.plusDays(60))
                holidays.add(LocalDate.of(year, Month.JANUARY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 6))
                holidays.add(LocalDate.of(year, Month.MAY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 3))
                holidays.add(LocalDate.of(year, Month.AUGUST, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 26))
            }

            currentDate = startDate
            var businessDays = 0L

            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate !in holidays) {
                    businessDays++
                }
                currentDate = currentDate.plusDays(1)
            }


            val daysDifferenceTextView = findViewById<EditText>(R.id.res)
            val businessDaysDifferenceTextView = findViewById<TextView>(R.id.robocze)
            daysDifferenceTextView.setText("$daysDifference")
            businessDaysDifferenceTextView.setText("Dni roboczych pomiędzy datami: ${businessDays-1}")
        }

        yearPicker2.setOnValueChangedListener { picker, _, _ ->
            val date1 = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val date2 = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)
            val daysDifference = ChronoUnit.DAYS.between(date1, date2)

            val startDate = LocalDate.of(yearPicker1.value, monthPicker1.value, dayPicker1.value)
            val endDate = LocalDate.of(yearPicker2.value, monthPicker2.value, dayPicker2.value)

            val holidays = mutableListOf(
                LocalDate.of(2022, 12, 24)  // Example holiday date 1
            )

            var currentDate = startDate
            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate.dayOfWeek == DayOfWeek.SATURDAY || currentDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    holidays.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

            val startYear = 1900
            val endYear = 2100

            for (year in startYear..endYear) {
                val easterDate = calculateEasterDate(year)
                holidays.add(easterDate)
                holidays.add(easterDate.plusDays(60))
                holidays.add(LocalDate.of(year, Month.JANUARY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 6))
                holidays.add(LocalDate.of(year, Month.MAY, 1))
                holidays.add(LocalDate.of(year, Month.JANUARY, 3))
                holidays.add(LocalDate.of(year, Month.AUGUST, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 1))
                holidays.add(LocalDate.of(year, Month.NOVEMBER, 11))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 25))
                holidays.add(LocalDate.of(year, Month.DECEMBER, 26))
            }

            currentDate = startDate
            var businessDays = 0L

            while (currentDate.isBefore(endDate.plusDays(1))) {
                if (currentDate !in holidays) {
                    businessDays++
                }
                currentDate = currentDate.plusDays(1)
            }


            val daysDifferenceTextView = findViewById<EditText>(R.id.res)
            val businessDaysDifferenceTextView = findViewById<TextView>(R.id.robocze)
            daysDifferenceTextView.setText("$daysDifference")
            businessDaysDifferenceTextView.setText("Dni roboczych pomiędzy datami: ${businessDays-1}")
        }
    }


}