package com.xmiyo.base.ui.data.service;

import com.xmiyo.base.ui.data.entity.SamplePerson;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SamplePersonRepository extends JpaRepository<SamplePerson, Integer> {

}