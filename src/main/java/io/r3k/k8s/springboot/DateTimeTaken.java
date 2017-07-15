package io.r3k.k8s.springboot;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class DateTimeTaken {
    @JsonFormat(pattern="yyyy-MM-dd'T'hh:mm:ss")
    LocalDateTime time;
    String timeTaken;
}
