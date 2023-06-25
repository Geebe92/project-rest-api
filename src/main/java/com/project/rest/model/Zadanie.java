package com.project.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name="zadanie")
public class Zadanie {
    @ManyToOne
    @JoinColumn(name="projekt_id")
    @JsonIgnoreProperties({"zadania"})
    private Projekt projekt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="zadanie_id")
    private Integer zadanieId;

    @Column(length = 50)
    private String nazwa;

    @Column
    private Integer kolejnosc;

    @Column(length = 100)
    private String opis;

    @Column(name="dataczas_dodania", nullable = false)
    private LocalDateTime dataczasDodania;

    public Projekt getProjekt() {
        return projekt;
    }

    public void setProjekt(Projekt projekt) {
        this.projekt = projekt;
    }

    public Integer getZadanieId() {
        return zadanieId;
    }

    public void setZadanieId(Integer zadanieId) {
        this.zadanieId = zadanieId;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Integer getKolejnosc() {
        return kolejnosc;
    }

    public void setKolejnosc(Integer kolejnosc) {
        this.kolejnosc = kolejnosc;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getDataczasDodania() {
        return dataczasDodania;
    }

    public void setDataczasDodania(LocalDateTime dataczasDodania) {
        this.dataczasDodania = dataczasDodania;
    }

    public Zadanie(){
    }

    public Zadanie(String nazwa, Integer kolejnosc, String opis){
        this.nazwa = nazwa;
        this.kolejnosc = kolejnosc;
        this.opis = opis;
    }
}
