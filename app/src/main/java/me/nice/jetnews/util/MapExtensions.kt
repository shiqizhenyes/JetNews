package me.nice.jetnews.util

/**
 * 拓展添加或者移除
 */
internal fun <E> MutableSet<E>.addOrRemove(element: E) {
    if (!add(element)) {
        remove(element)
    }
}