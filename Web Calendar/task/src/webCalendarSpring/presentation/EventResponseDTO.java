package webCalendarSpring.presentation;

public class EventResponseDTO {
    String message;
    String event;
    String date;

    public EventResponseDTO(String message, String event, String date) {
        this.message = message;
        this.event = event;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
