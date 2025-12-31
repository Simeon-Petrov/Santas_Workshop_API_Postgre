package com.example.exam_postgre.service;

import com.example.exam_postgre.dto.GiftRequest;
import com.example.exam_postgre.model.Category;
import com.example.exam_postgre.model.Gift;
import com.example.exam_postgre.model.Status;
import com.example.exam_postgre.repository.GiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;

    public Gift createGift(GiftRequest request) {
        Gift newGift = Gift.builder()
                .name(request.getName())
                .category(request.getCategory())
                .targetAge(request.getTargetAge())
                .isWrapped(false)
                .status(Status.PENDING)
                .build();

        return giftRepository.save(newGift);
    }

    public List<Gift> getAllGifts() {
        return giftRepository.findAll();
    }

    public Gift getGiftById(Long id) {
        return giftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gift not found with ID: " + id));
    }

    public void deleteGift(Long id) {
        if (!giftRepository.existsById(id)) {
            throw new RuntimeException("Gift not found!");
        }
        giftRepository.deleteById(id);
    }

    public Gift wrapGift(Long id) {
        Gift gift = getGiftById(id);
        gift.setWrapped(true);
        gift.setStatus(Status.READY);
        return giftRepository.save(gift);
    }

    public List<Gift> getGiftsFiltered(Status status, Category category, Boolean wrapped) {
        return giftRepository.findAll().stream()
                .filter(g -> status == null || g.getStatus() == status)
                .filter(g -> category == null || g.getCategory() == category)
                .filter(g -> wrapped == null || g.isWrapped() == wrapped)
                .toList();
    }

    public List<Gift> searchGiftsByName(String query) {
        return giftRepository.findAll().stream()
                .filter(gift -> gift.getName().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }
}