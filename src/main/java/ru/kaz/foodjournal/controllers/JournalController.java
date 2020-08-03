package ru.kaz.foodjournal.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kaz.foodjournal.models.Record;
import ru.kaz.foodjournal.repo.RecordRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JournalController {

    private final RecordRepository recordRepository;

    public JournalController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @GetMapping("/journal")
    public String Journal(Model model){
        Iterable<Record> records = recordRepository.findAll();
        model.addAttribute("records", records);
        return "journal";
    }

    @GetMapping("/journal/add")
    public String JournalAdd(Model model){
        return "journal-add";
    }

    @PostMapping("/journal/add")
    public String JournalRecordAdd(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime, @RequestParam String products,
                                   @RequestParam String symptoms, @RequestParam String medicines, Model model){

        Record record = new Record(dateTime, products, symptoms, medicines);
        recordRepository.save(record);

        return "redirect:/journal";
    }

    @GetMapping("/journal/{id}/edit")
    public String JournalRecordEdit(@PathVariable(value = "id") long id, Model model){
        if(!recordRepository.existsById(id)) {
            return "redirect:/journal";
        }
        Optional<Record> record = recordRepository.findById(id);
        List<Record> res = new ArrayList<>();
        record.ifPresent(res::add);
        model.addAttribute("record", res);
        return "journal-edit";
    }

    @PostMapping("/journal/{id}/edit")
    public String JournalRecordEdit(@PathVariable(value = "id") long id, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                                    @RequestParam String products, @RequestParam String symptoms, @RequestParam String medicines, Model model){

        Record record = recordRepository.findById(id).orElseThrow(ArithmeticException::new);
        record.setDateTime(dateTime);
        record.setProducts(products);
        record.setSymptoms(symptoms);
        record.setMedicines(medicines);
        recordRepository.save(record);

        return "redirect:/journal";
    }

    @PostMapping("/journal/{id}/delete")
    public String journalRecordDelete(@PathVariable(value = "id") long id, Model model){
        Record record = recordRepository.findById(id).orElseThrow(ArithmeticException::new);
        recordRepository.delete(record);
        return "redirect:/journal";
    }

}
