package fr.snake.dto;

import java.io.Serial;
import java.io.Serializable;

public class UserIDDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1900977201604300001L;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
