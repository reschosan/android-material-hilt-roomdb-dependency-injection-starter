package com.example.myapplication.utils

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    companion object {
        private val sSubject = PublishSubject.create<String>()

        fun subscribe(message: String) {

            sSubject.onNext(message)
        }

        fun getEvents(): Observable<String> {
            return sSubject
        }
    }
}