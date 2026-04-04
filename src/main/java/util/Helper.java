package util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Getter
public enum Helper {

    DATE_FORMAT(DateTimeFormatter.ISO_DATE);

    final DateTimeFormatter dateTimeFormatter;
}
