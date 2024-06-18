package api.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AddBookRequestModel {
    String userId;
    List<Isbn> collectionOfIsbns;

    @Data
    @AllArgsConstructor
    public static class Isbn {
        String isbn;
    }
}
