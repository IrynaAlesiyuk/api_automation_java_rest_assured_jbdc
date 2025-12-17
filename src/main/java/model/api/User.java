package model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor       // нужен для Jackson
@AllArgsConstructor      // для удобства создания через builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String email;
}
