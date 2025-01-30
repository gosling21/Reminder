package org.example.reminder.service;

import lombok.AllArgsConstructor;
import org.example.reminder.model.Reminder;
import org.example.reminder.model.ReminderRepository;

import org.example.reminder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;

    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    public void sendReminderNotification(Reminder reminder, User user) {
        // Отправка email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Reminder: " + reminder.getTitle());
        message.setText(reminder.getDescription());
        //mailSender.send(message);

        // Отправка в Telegram
        //sendTelegramNotification(user.getTelegramUsername(), reminder);
    }

    private void sendTelegramNotification(String username, Reminder reminder) {
        String telegramApiUrl = "https://api.telegram.org/bot<TOKEN>/sendMessage";
        String message = "🔔 Напоминание: " + reminder.getTitle() + "\n" + reminder.getDescription();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(telegramApiUrl, Map.of(
                "chat_id", username,
                "text", message
        ), String.class);
    }


    public List<Reminder> getAllRemindersSorted(Sort sort) {
        return reminderRepository.findAll(sort);
    }


    public Map<String, Object> getAllRemindersPaged(int page, int size) {
        Page<Reminder> remindersPage = reminderRepository.findAll(PageRequest.of(page, size));

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("total", remindersPage.getTotalElements());
        response.put("reminders", remindersPage.getContent());    // текущие элементы на странице

        return response;
    }

    @Override
    public Reminder findById(Long id) {
        return reminderRepository.findById(id).orElse(null);
    }

    //обновление напоминания по id из url и по данным из json
    /*@Override
    public void updateReminder(Long id, Reminder updatedReminder) {
        Reminder existingReminder = reminderRepository.findById(id).orElse(null);
        if (existingReminder != null) {
            // Обновляем только те поля, которые были переданы
            if (updatedReminder.getTitle() != null) {
                existingReminder.setTitle(updatedReminder.getTitle());
            }
            if (updatedReminder.getDescription() != null) {
                existingReminder.setDescription(updatedReminder.getDescription());
            }
            if (updatedReminder.getRemind() != null) {
                existingReminder.setRemind(updatedReminder.getRemind());
            }
            reminderRepository.save(existingReminder);
        } else {
            System.out.println("Reminder not found");
        }
    }

     */
}
