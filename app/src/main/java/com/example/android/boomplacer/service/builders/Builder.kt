package com.example.android.boomplacer.service.builders

import com.example.android.boomplacer.model.PropertyNotSetException

open class Builder {
    protected fun throwPropertyNotSetException(propertyName: String): Nothing {
        throw PropertyNotSetException(
            "Property $propertyName was not configured."
        )
    }
}