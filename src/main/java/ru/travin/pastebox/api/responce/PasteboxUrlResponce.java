package ru.travin.pastebox.api.responce;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteboxUrlResponce {
    private final String url;
}
