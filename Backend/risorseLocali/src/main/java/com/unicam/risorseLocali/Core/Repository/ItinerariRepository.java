package com.unicam.risorseLocali.Core.Repository;

import com.unicam.risorseLocali.Core.Model.Entity.Itinerari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItinerariRepository extends JpaRepository<Itinerari, String> {
}