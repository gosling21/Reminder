package org.example.reminder.service;

import org.example.reminder.model.Reminder;
import org.example.reminder.model.ReminderRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface ReminderService  {


    void createReminder(Reminder reminder);


    List<Reminder> getAllReminders ();

    void deleteById(Long id);

    List<Reminder> getAllRemindersSorted(Sort sort);
}
