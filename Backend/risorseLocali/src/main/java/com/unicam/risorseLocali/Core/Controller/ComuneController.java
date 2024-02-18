package com.unicam.risorseLocali.Core.Controller;

import com.unicam.risorseLocali.Core.Model.Interface.ElementiMappa;
import com.unicam.risorseLocali.Core.Service.ItinerariService;
import com.unicam.risorseLocali.Core.Service.MappaService;
import com.unicam.risorseLocali.Core.Service.POIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mappa")
public class ComuneController {
    private final MappaService mappaService;

    public ComuneController(MappaService mappaService) {
        this.mappaService = mappaService;
    }

    @GetMapping("/all")
    public Iterable<ElementiMappa> getAllElement() {
        return this.mappaService.getAllElementInComune();
    }

    @GetMapping("/poi")
    public Iterable<ElementiMappa> getAllPOI(){
       return this.mappaService.getAllTypedElement(POIService.class);
    }

    @GetMapping("/itinerari")
    public Iterable<ElementiMappa> getAllitinerari(){
        return this.mappaService.getAllTypedElement(ItinerariService.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ElementiMappa> getSpecific(@PathVariable String id) {
        return this.mappaService.getSpecificElement(id);
    }

}