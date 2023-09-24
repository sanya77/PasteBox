package ru.travin.pastebox.controller;

import org.springframework.web.bind.annotation.*;
import ru.travin.pastebox.api.request.PasteboxRequest;

import java.util.Collection;
import java.util.Collections;

@RestController
public class PasteBoxController {

    @GetMapping("/")
    public Collection<String> getPublicPasteList(){
        return Collections.emptyList();
    }
    @GetMapping("/{hash}")
    public String getByHash(@PathVariable String hash){
        return hash;
    }

    @PostMapping("/")
    public String add(@RequestBody PasteboxRequest request){
        return request.getData();
    }
}
