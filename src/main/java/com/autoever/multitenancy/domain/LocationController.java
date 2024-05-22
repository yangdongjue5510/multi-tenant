package com.autoever.multitenancy.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepository locationRepository;

    @PostMapping("/locations")
    public Location add(@RequestBody Location location) {

        return locationRepository.save(location);
    }

    @GetMapping("/locations")
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
