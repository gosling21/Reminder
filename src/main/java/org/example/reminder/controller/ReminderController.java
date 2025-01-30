package org.example.reminder.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.reminder.model.Reminder;

import org.example.reminder.service.ReminderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/api/v1/reminder")
@RequiredArgsConstructor
public class ReminderController {

    @Autowired
    public ReminderController(ReminderServiceImpl reminderService) {
        this.reminderService = reminderService;
    }

    private final ReminderServiceImpl reminderService;

    @PostMapping("/create")
    public void createReminder(@RequestBody Reminder reminder) {
        reminderService.createReminder(reminder);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }

    /*@PutMapping("/update/{id}")
    public void updateReminder(@PathVariable Long id, @RequestBody Reminder updatedReminder) {
        reminderService.updateReminder(id, updatedReminder);
    }
    */
    @GetMapping("/list")
    public Map<String, Object> getAllReminders(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {
        return reminderService.getAllRemindersPaged(page, size);
    }

    @GetMapping("/sort")
    public List<Reminder> sortReminders(@RequestParam String by) { //@RequestParam извлекает из url параметр
        Sort sort = Sort.by(by);
        return reminderService.getAllRemindersSorted(sort);
    }


    // todo: фильтр
    /*@GetMapping("/filtr/date/{day}/{month}/{year}")
    public List<Reminder> filterReminders(@PathVariable int day, @PathVariable int month, @PathVariable int year) {
        return reminderService.filterReminders(day, month, year);
    }
*/
}
