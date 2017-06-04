import java.util.*

inline fun <T> List<T>.applyOn(index: Int, action: (T) -> Unit): List<T> {
    action(this[index])
    return this
}

inline fun <R> CharSequence.mapReduce(prev: R, operation: (R, Char) -> R): R {
    if (isEmpty()) throw UnsupportedOperationException("Empty CharSequence can't be reduced.")
    var accumulator = prev
    for (index in indices) {
        accumulator = operation(accumulator, this[index])
    }
    return accumulator
}

inline fun <T, R> List<T>.mapGrouped(transform: (f: T, s: T, t: T) -> R): List<R> {
    if (isEmpty()) throw UnsupportedOperationException("Empty list can't be mapGrouped.")
    if (size % 3 != 0) throw UnsupportedOperationException("The size of the list must be a multiple of 3.")

    val result = ArrayList<R>()
    var index = 0

    while (index < size) result.add(transform(this[index++], this[index++], this[index++]))
    return result
}

inline fun String.ordered() = map { it.toString() }.toList().sorted().reduce { acc, s -> acc + s }
