package org.example.reminder.service;

import lombok.AllArgsConstructor;
import org.example.reminder.model.Reminder;
import org.example.reminder.model.ReminderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    List<Reminder> reminders;

    private final ReminderRepository reminderRepository;

    public void createReminder(Reminder reminder) {
        reminderRepository.save(reminder);
        reminders.add(reminder);
    }

    @Override
    public void deleteById(Long id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public List<Reminder> getAllRemindersSorted(Sort sort) {
        return reminderRepository.findAll(sort);
    }

    @Override
    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }



}

