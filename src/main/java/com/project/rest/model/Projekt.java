package com.project.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="projekty")
public class Projekt {
    @Id
    @GeneratedValue
    @Column(name="projekt_id")
    private Integer projektId;

    @NotBlank(message = "Pole nazwa nie może być puste!")
    @Size(min = 3, max = 50, message = "Nazwa musi zawierać od {min} do {max} znaków!")
    @Column(nullable = false, length = 50)
    private String nazwa;

    @Column(length=1000)
    private String opis;

    @CreationTimestamp
    @Column(name="dataczas_utworzenia", nullable = false, updatable = false)
    private LocalDateTime dataczasUtworzenia;

    @Column(name="data_oddania")
    private LocalDate dataOddania;

    @OneToMany(mappedBy = "projekt")
    @JsonIgnoreProperties({"projekt"})
    private List<Zadanie> zadania;

    @ManyToMany
    @JoinTable(name = "projekt_student",
            joinColumns = {@JoinColumn(name = "projekt_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    private Set<Student> studenci;

    public Integer getProjektId(){
        return projektId;
    }

    public void setProjektId(Integer projektId){
        this.projektId = projektId;
    }

    public String getNazwa(){
        return nazwa;
    }

    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }

    public String getOpis(){
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataczasUtworzenia() {
        return dataczasUtworzenia;
    }

    public void setDataczasUtworzenia(LocalDateTime dataczasUtworzenia) {
        this.dataczasUtworzenia = dataczasUtworzenia;
    }

    public LocalDate getDataOddania() {
        return dataOddania;
    }

    public void setDataOddania(LocalDate dataOddania) {
        this.dataOddania = dataOddania;
    }

    public Projekt(){
    }

    public Projekt(String nazwa, String opis){
        this.nazwa=nazwa;
        this.opis=opis;
    }

    public Projekt(String nazwa, String opis, LocalDate dataOddania){
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataOddania = dataOddania;
    }

    public Projekt(Integer projektId, String nazwa, String opis, LocalDateTime dataczasUtworzenia, LocalDate dataOddania) {
        this.projektId = projektId;
        this.nazwa = nazwa;
        this.opis = opis;
        this.dataczasUtworzenia = dataczasUtworzenia;
        this.dataOddania = dataOddania;
    }

}
