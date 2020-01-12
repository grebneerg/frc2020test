package com.grebneerg.tobor

fun <N: Comparable<N>> N.bound(min: N, max: N): N = when {
    min > this -> min
    max < this -> max
    else -> this
}
