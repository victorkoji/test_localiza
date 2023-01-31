package com.api.test_localiza.components

import com.api.test_localiza.dto.TimeView
import org.springframework.stereotype.Component

@Component
class TimeComponent {

    fun timeToSeconds(hours: Long, minutes: Long, seconds: Long): Long {
        return (hours * 3600) + (minutes * 60) + seconds
    }

    fun secondsToTime(seconds: Long): TimeView {
        return TimeView(
             hours = seconds / 3600,
             minutes = (seconds % 3600) / 60,
             seconds = seconds % 60
        )
    }
}