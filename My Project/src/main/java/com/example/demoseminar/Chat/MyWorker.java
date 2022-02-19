package com.example.demoseminar.Chat;

import androidx.annotation.WorkerThread;

import java.lang.annotation.Annotation;

public class MyWorker implements WorkerThread {
    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
