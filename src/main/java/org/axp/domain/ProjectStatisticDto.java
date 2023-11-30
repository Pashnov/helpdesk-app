package org.axp.domain;

public record ProjectStatisticDto(
        String projectId,
        int year,
        int week,
        float arrivalRate,  // Інтенсивність прибуття λ lambda
        float serviceRate, // Інтенсивність обслуговування μ mu

        float loadFactor, // Завантаженість системи `ρ` Rho
        float averageNumberInSystem, // L
        float averageWaitingTime, // W
        float averageQueueLength, // Lq
        float averageQueueWaitingTime // Wq
) {}
