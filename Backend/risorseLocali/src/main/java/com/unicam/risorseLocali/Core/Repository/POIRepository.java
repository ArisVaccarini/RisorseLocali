package com.unicam.risorseLocali.Core.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.unicam.risorseLocali.Core.Model.Entity.POI;
import org.springframework.stereotype.Repository;

@Repository
public interface POIRepository extends JpaRepository<POI, String>{
}
