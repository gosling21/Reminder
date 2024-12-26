package org.example.reminder.controller;

import lombok.AllArgsConstructor;
import org.example.reminder.model.Reminder;

import org.example.reminder.service.ReminderServiceImpl;

import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping ("/api/v1/reminder")
@AllArgsConstructor
public class ReminderController {

    private final ReminderServiceImpl reminderService;

    @PostMapping("/create")
    public void createReminder(@RequestBody Reminder reminder) {
        reminderService.createReminder(reminder);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReminder(@PathVariable Long id) {
        reminderService.deleteById(id);
    }

    @PutMapping
    public void updateReminder(@RequestBody Reminder reminder) {

    }

    @GetMapping("/list")
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }

    @GetMapping("/sort")
    public List<Reminder> sortReminders(@RequestParam String by) { //@RequestParam извлекает из url параметр
        Sort sort = Sort.by(by);
        return reminderService.getAllRemindersSorted(sort);
    }

    void forGit(){
        System.out.println("дай запушить");
    }
}
