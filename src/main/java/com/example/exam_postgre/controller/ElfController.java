package com.example.exam_postgre.controller;

import com.example.exam_postgre.dto.ElfRequest;
import com.example.exam_postgre.model.Elf;
import com.example.exam_postgre.service.ElfService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/elves")
@RequiredArgsConstructor
public class ElfController {

    private final ElfService elfService;

    @PostMapping
    public ResponseEntity<Elf> createElf(@Valid @RequestBody ElfRequest request) {
        return new ResponseEntity<>(elfService.createElf(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Elf>> getAllElves() {
        return ResponseEntity.ok(elfService.getAllElves());
    }

    @PostMapping("/{elfId}/assign/{giftId}")
    public ResponseEntity<Elf> assignGift(@PathVariable Long elfId, @PathVariable Long giftId) {
        return ResponseEntity.ok(elfService.assignGiftToElf(elfId, giftId));
    }

}
