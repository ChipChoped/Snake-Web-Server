package fr.snake.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;

@XmlRootElement
public class ExceptionDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2085833835586181536L;
    private String message;

    public ExceptionDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
