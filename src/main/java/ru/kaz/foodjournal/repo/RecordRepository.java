package ru.kaz.foodjournal.repo;

import org.springframework.data.repository.CrudRepository;
import ru.kaz.foodjournal.models.Record;

public interface RecordRepository extends CrudRepository<Record, Long> {
}
