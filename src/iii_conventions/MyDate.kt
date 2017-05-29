package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return year.compareTo(other.year) + month.compareTo(other.month) + dayOfMonth.compareTo(other.dayOfMonth)
    }

    fun toInt(): Int = (year * 10000) + (month * 100) + dayOfMonth
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        var curDate = start
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean = curDate <= endInclusive

            override fun next(): MyDate {
                val tmp = curDate
                curDate = curDate.nextDay()
                return tmp
            }
        }
    }
}

operator fun DateRange.contains(other: MyDate): Boolean {
    return start.toInt() <= other.toInt() && other.toInt() <= endInclusive.toInt()
}