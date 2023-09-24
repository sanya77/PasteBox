package ru.travin.pastebox.service;

import ru.travin.pastebox.api.request.PasteboxRequest;
import ru.travin.pastebox.api.responce.PasteboxResponce;
import ru.travin.pastebox.api.responce.PasteboxUrlResponce;

import java.util.List;

public interface PasteboxService {
    PasteboxResponce getByHash(String hash);
    List<PasteboxResponce> getFirstPublicPastebox();
    PasteboxUrlResponce create(PasteboxRequest request);

}
