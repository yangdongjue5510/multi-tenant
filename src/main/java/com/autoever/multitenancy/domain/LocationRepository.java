package com.autoever.multitenancy.domain;

import com.autoever.multitenancy.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
