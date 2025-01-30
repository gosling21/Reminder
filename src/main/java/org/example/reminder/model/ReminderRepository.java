package org.example.reminder.model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    //List<Reminder> findByDate(LocalDate targetDate);

    Iterable<Reminder> findAllByTitleLikeIgnoreCase(String title);

    Reminder findById(long id);

}

