package com.example.exam_postgre.service;

import com.example.exam_postgre.dto.ElfRequest;
import com.example.exam_postgre.model.Elf;
import com.example.exam_postgre.model.Gift;
import com.example.exam_postgre.model.Status;
import com.example.exam_postgre.repository.ElfRepository;
import com.example.exam_postgre.repository.GiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ElfService {

    private final ElfRepository elfRepository;
    private final GiftRepository giftRepository;

    public ElfService(ElfRepository elfRepository, GiftRepository giftRepository) {
        this.elfRepository = elfRepository;
        this.giftRepository = giftRepository;
    }

    public Elf createElf(ElfRequest request) {
        Elf elf = Elf.builder()
                .name(request.getName())
                .skillLevel(request.getSkillLevel())
                .build();
        return elfRepository.save(elf);
    }

    public List<Elf> getAllElves() {
        return elfRepository.findAll();
    }

    public Elf getElfById(Long id) {
        return elfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Elf not found!"));
    }

    @Transactional
    public Elf assignGiftToElf(Long elfId, Long giftId) {
        Elf elf = getElfById(elfId);
        Gift gift = giftRepository.findById(giftId)
                .orElseThrow(() -> new RuntimeException("Gift not found!"));

        if (gift.getStatus() == Status.DELIVERED) {
            throw new IllegalStateException("Invalid status: Gift is already DELIVERED");
        }

        if (!elf.getAssignedGifts().contains(gift)) {
            elf.getAssignedGifts().add(gift);
        }

        return elfRepository.save(elf);
    }
}