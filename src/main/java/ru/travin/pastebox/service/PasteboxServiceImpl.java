package ru.travin.pastebox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.travin.pastebox.api.request.PasteboxRequest;
import ru.travin.pastebox.api.request.PublicStatus;
import ru.travin.pastebox.api.responce.PasteboxResponce;
import ru.travin.pastebox.api.responce.PasteboxUrlResponce;
import ru.travin.pastebox.repository.PasteboxEntity;
import ru.travin.pastebox.repository.PasteboxRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteboxService {
    private String host = "http://abc.ru";
    private int publicListSize = 10;

    private final PasteboxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);

    @Override
    public PasteboxResponce getByHash(String hash) {
        PasteboxEntity pasteboxEntity = repository.getByHash(hash);
        return new PasteboxResponce(pasteboxEntity.getData(), pasteboxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponce> getFirstPublicPastebox() {
        List<PasteboxEntity> list = repository.getListOfPublicAndALive(publicListSize);

       return list.stream().map(pasteboxEntity ->
               new PasteboxResponce(pasteboxEntity.getData(), pasteboxEntity.isPublic()))
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponce create(PasteboxRequest request) {

        int hash = generateId();
        PasteboxEntity pasteboxEntity = new PasteboxEntity();
        pasteboxEntity.setData(request.getData());
        pasteboxEntity.setId(hash);
        pasteboxEntity.setHash(Integer.toHexString(hash));
        pasteboxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);

        pasteboxEntity.setLifetime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pasteboxEntity);

        return new PasteboxUrlResponce(host + "/" + pasteboxEntity.getHash());

    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
