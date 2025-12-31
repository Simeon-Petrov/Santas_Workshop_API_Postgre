package com.example.exam_postgre.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class StatsResponse {
    private Map<String, Long> giftsByStatus;
    private long totalDeliveries;
}
