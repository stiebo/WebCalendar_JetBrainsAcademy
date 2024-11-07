package webCalendarSpring.presentation;

public class CreateEventDTO {
    String event;
    String date;

    public CreateEventDTO() {
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
