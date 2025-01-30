package org.example.reminder.service;

import org.example.reminder.model.Reminder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReminderService  {
    void deleteReminder(Long id);
}
