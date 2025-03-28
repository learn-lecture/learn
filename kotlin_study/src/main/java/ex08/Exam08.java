package ex08;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Exam08 {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy MM dd");
    private final String stringRegisterAt;

    public Exam08(Store store){
        this.stringRegisterAt = toLocalDateTimeString(store.getRegisterAt());
    }

    public String toLocalDateTimeString(LocalDateTime localDateTime){
        return Optional.ofNullable(localDateTime)
            .orElseGet(LocalDateTime::now)
            .format(FORMATTER);
    }

    public String getFormattedRegisterAt() {
        return stringRegisterAt;
    }

    public static void main(String[] args) {
        var registerDto = new Store();
        Exam08 exam08 = new Exam08(registerDto);
        System.out.println(exam08.getFormattedRegisterAt());
    }

}

class Store {

    private LocalDateTime registerAt;

    public LocalDateTime getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(LocalDateTime registerAt) {
        this.registerAt = registerAt;
    }

}