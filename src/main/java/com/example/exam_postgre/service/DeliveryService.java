package com.example.exam_postgre.service;

import com.example.exam_postgre.dto.DeliveryRequest;
import com.example.exam_postgre.model.Delivery;
import com.example.exam_postgre.model.DeliveryStatus;
import com.example.exam_postgre.model.Gift;
import com.example.exam_postgre.model.Status;
import com.example.exam_postgre.repository.DeliveryRepository;
import com.example.exam_postgre.repository.GiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final GiftRepository giftRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, GiftRepository giftRepository) {
        this.deliveryRepository = deliveryRepository;
        this.giftRepository = giftRepository;
    }

    @Transactional
    public Delivery createDelivery(DeliveryRequest request) {
        List<Gift> giftsToDeliver = giftRepository.findAllById(request.getGiftIds());

        if (giftsToDeliver.isEmpty()) {
            throw new RuntimeException("No valid gifts found for delivery!");
        }

        Delivery delivery = Delivery.builder()
                .address(request.getAddress())
                .recipientName(request.getRecipientName())
                .deliveryStatus(DeliveryStatus.PLANNED)
                .estimatedArrival(request.getEstimatedArrival())
                .gifts(giftsToDeliver)
                .build();

        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Transactional
    public Delivery updateDeliveryStatus(Long id, DeliveryStatus newStatus) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found!"));

        delivery.setDeliveryStatus(newStatus);

        if (newStatus == DeliveryStatus.DELIVERED) {
            for (Gift gift : delivery.getGifts()) {
                gift.setStatus(Status.DELIVERED);
            }
        }

        return deliveryRepository.save(delivery);
    }
}