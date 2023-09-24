package ru.travin.pastebox.repository;

import ru.travin.pastebox.service.PasteboxService;

import java.util.List;

public interface PasteboxRepository {
PasteboxEntity getByHash(String hash);
List<PasteboxEntity> getListOfPublicAndALive(int amount);
void add(PasteboxEntity pasteboxEntity);

}
