package com.autoever.multitenancy.controller;

import com.autoever.multitenancy.Location;
import com.autoever.multitenancy.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepository locationRepository;

    @PostMapping("/locations/{name}")
    public Location add(@PathVariable String name) {

        return locationRepository.save(new Location(null, name));
    }

    @GetMapping("/locations")
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
