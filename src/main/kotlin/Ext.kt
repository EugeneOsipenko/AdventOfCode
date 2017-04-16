

inline fun <T> List<T>.applyOn(index: Int, action: (T) -> Unit): List<T> {
    action(this[index])
    return this
}
