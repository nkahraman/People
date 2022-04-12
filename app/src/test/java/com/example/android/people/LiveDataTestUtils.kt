
package com.example.android.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.platform.app.InstrumentationRegistry
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

/**
 * Observes this [LiveData] and returns the value.
 *
 * @throws NullPointerException if the observed value is null.
 */
fun <T> LiveData<T>.observedValue(): T {
    var result: T? = null
    val latch = CountDownLatch(1)
    val observer = Observer<T> {
        result = it
        latch.countDown()
    }
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
        observeForever(observer)
    }
    latch.await(3000L, TimeUnit.MILLISECONDS)
    InstrumentationRegistry.getInstrumentation().runOnMainSync {
        removeObserver(observer)
    }
    return result!!
}
