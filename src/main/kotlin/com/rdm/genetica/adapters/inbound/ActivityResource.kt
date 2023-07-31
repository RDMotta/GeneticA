package com.rdm.genetica.adapters.inbound

import com.rdm.genetica.adapters.dto.ActivityDto
import com.rdm.genetica.application.domain.Activity
import com.rdm.genetica.application.ports.ActivityServicePort
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ActivityResource(@Autowired var activityServicePort: ActivityServicePort) {
    @PostMapping("/activity")
    fun saveActivity(@RequestBody activityDto: ActivityDto): ResponseEntity<Activity> {
        val activity = Activity(name = activityDto.name, point = activityDto.point, value = activityDto.value)

        return ResponseEntity<Activity>(activityServicePort.save(activity), HttpStatus.CREATED)
    }
}