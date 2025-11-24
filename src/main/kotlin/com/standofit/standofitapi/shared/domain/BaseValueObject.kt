package com.standofit.standofitapi.shared.domain

abstract class BaseValueObject<T>(val value: T) {

    init {
        validateType(value)
    }

    protected abstract fun validateType(value: T)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is BaseValueObject<*>) return false
        return value == other.value
    }

    override fun hashCode(): Int = value?.hashCode() ?: 0

    override fun toString(): String = value.toString()
}
