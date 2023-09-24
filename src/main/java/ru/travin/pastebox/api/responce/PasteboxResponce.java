package ru.travin.pastebox.api.responce;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.travin.pastebox.api.request.PublicStatus;

@Data
@RequiredArgsConstructor
public class PasteboxResponce {
    private final String data;
    private final boolean isPublic;
}
