package com.project.rest.service;

import com.project.rest.model.Zadanie;
import com.project.rest.repository.ProjektRepository;
import com.project.rest.repository.StudentRepository;
import com.project.rest.repository.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ZadanieServiceImpl implements ZadanieService{

    private ZadanieRepository zadanieRepository;

    @Autowired
    public ZadanieServiceImpl( ZadanieRepository zadanieRepository){
        this.zadanieRepository = zadanieRepository;
    }


    @Override
    public Optional<Zadanie> getZadanie(Integer zadanieId) {
        return zadanieRepository.findById(zadanieId);
    }

    @Override
    public Zadanie setZadanie(Zadanie zadanie) {
        zadanieRepository.save(zadanie);
        return zadanie;
    }

    @Override
    @Transactional
    public void deleteZadanie(Integer zadanieId) {
        zadanieRepository.deleteById(zadanieId);
    }

    @Override
    public Page<Zadanie> getZadania(Pageable pageable) {
        return zadanieRepository.findAll(pageable);
    }

    @Override
    public Page<Zadanie> getProjektZadania(Integer projektId, Pageable pageable) {
        return zadanieRepository.findZadaniaProjektu(projektId, pageable);
    }
}
