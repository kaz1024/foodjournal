package ru.kaz.foodjournal.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime dateTime;

    public Record() {
    }

    public Record(LocalDateTime dateTime, String products, String symptoms, String medicines) {
        this.dateTime = dateTime;
        this.products = products;
        this.symptoms = symptoms;
        this.medicines = medicines;
    }

    private String products;

    private String symptoms;

    private String medicines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String food) {
        this.products = food;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptom) {
        this.symptoms = symptom;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
