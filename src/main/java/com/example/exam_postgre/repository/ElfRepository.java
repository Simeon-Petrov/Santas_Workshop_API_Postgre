package com.example.exam_postgre.repository;

import com.example.exam_postgre.model.Elf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElfRepository extends JpaRepository <Elf, Long> {

}
