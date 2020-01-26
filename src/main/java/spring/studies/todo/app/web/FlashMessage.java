package spring.studies.todo.app.web;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FlashMessage {

    private String message;
    private Status status;

    public static enum Status {
        SUCCESS,
        INFO,
        FAILURE
    }

    public FlashMessage(String message, Status status) {
        this.message = message;
        this.status = status;
    }
}
