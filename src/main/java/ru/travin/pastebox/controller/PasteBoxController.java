package ru.travin.pastebox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.travin.pastebox.api.request.PasteboxRequest;
import ru.travin.pastebox.api.responce.PasteboxResponce;
import ru.travin.pastebox.api.responce.PasteboxUrlResponce;
import ru.travin.pastebox.service.PasteboxService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {
    private final PasteboxService pasteboxService;

    @GetMapping("/")
    public Collection<PasteboxResponce> getPublicPasteList(){
        return pasteboxService.getFirstPublicPastebox();
    }
    @GetMapping("/{hash}")
    public PasteboxResponce getByHash(@PathVariable String hash){
        return pasteboxService.getByHash(hash);
    }

    @PostMapping("/")
    public PasteboxUrlResponce add(@RequestBody PasteboxRequest request){
        return pasteboxService.create(request);
    }
}
