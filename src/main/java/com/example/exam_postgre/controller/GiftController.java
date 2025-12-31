package com.example.exam_postgre.controller;

import com.example.exam_postgre.dto.GiftRequest;
import com.example.exam_postgre.model.Category;
import com.example.exam_postgre.model.Gift;
import com.example.exam_postgre.model.Status;
import com.example.exam_postgre.service.GiftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gifts")
@RequiredArgsConstructor
public class GiftController {

    private final GiftService giftService;

    @PostMapping
    public ResponseEntity<Gift> createGift(@Valid @RequestBody GiftRequest request) {
        Gift createdGift = giftService.createGift(request);
        return new ResponseEntity<>(createdGift, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Gift>> getAllGifts(
            @RequestParam(required = false)Status status,
            @RequestParam(required = false)Category category,
            @RequestParam(required = false)Boolean wrapped) {
        List<Gift> gifts = giftService.getGiftsFiltered(status, category, wrapped);
        return  ResponseEntity.ok(gifts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Gift>> searchGifts(@RequestParam String name) {
        List<Gift> results = giftService.searchGiftsByName(name);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gift> getGiftById(@PathVariable Long id) {
        return ResponseEntity.ok(giftService.getGiftById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGift(@PathVariable Long id) {
        giftService.deleteGift(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/wrap")
    public ResponseEntity<Gift> wrapGift(@PathVariable Long id) {
        Gift wrappedGift = giftService.wrapGift(id);
        return ResponseEntity.ok(wrappedGift);
    }
}
