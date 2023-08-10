package com.rdm.genetica.adapters.inbound

import com.rdm.genetica.adapters.dto.ActivityDto
import com.rdm.genetica.application.domain.Activity
import com.rdm.genetica.application.domain.PageInfo
import com.rdm.genetica.application.ports.ActivityServicePort
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ActivityResource(@Autowired var activityServicePort: ActivityServicePort) {
    @PostMapping("/activity")
    fun saveActivity(@RequestBody activityDto: ActivityDto): ResponseEntity<Activity> {
        val activity = Activity(name = activityDto.name, point = activityDto.point, value = activityDto.value)
        return ResponseEntity<Activity>(activityServicePort.save(activity), HttpStatus.CREATED)
    }

    @GetMapping("/activity")
    fun getAllActivity(
      @PageableDefault(page = 0, size = 5, sort = ["activityId"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<Page<Activity>> {
        val pageInfo = PageInfo(pageNumber = pageable.pageNumber, pageSize = pageable.pageSize)
        BeanUtils.copyProperties(pageable, pageInfo)
        val list = activityServicePort.findAll(pageInfo)
        return ResponseEntity<Page<Activity>>(
                PageImpl(list, pageable, list.size.toLong()),
                HttpStatus.OK)
    }

    @GetMapping("/activity/{activityId}")
    fun getOneActivity(@PathVariable(value = "activityId") activityId: UUID): ResponseEntity<Any> {
        val activityModelOptional = activityServicePort.findById(activityId)
        return if (!activityModelOptional.isPresent) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found.")
        } else {
            ResponseEntity.status(HttpStatus.OK).body(activityModelOptional.get())
        }
    }

    @DeleteMapping("/activity/{activityId}")
    fun deleteOneActivity(@PathVariable(value = "activityId") activityId: UUID) {
        val activityModelOptional = activityServicePort.findById(activityId)
        if (!activityModelOptional.isPresent) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activity not found.")
        } else {
            activityServicePort.delete(activityModelOptional.get())
            ResponseEntity.status(HttpStatus.OK).body("Activity has deleted.")
        }
    }
}