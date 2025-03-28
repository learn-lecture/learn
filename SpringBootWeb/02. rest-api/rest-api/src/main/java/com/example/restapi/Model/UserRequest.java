package com.example.restapi.Model;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    private String userName;
    private Integer userAge;
    private String email;
    private Boolean isKorean;
    /*
    objectMapper.writeValueAsString(object)
    objectMapper는 클래스 변수가 아니라 메서드에 매핑이 됨.
    Lombok의 @Data 어노테이션을 사용하지 않을 시 매핑이 되지 않음.
    매핑메서드는 get 또는 set이 포함되어야 하며 메서드명을 기준으로 (역)직렬화를 함.
    JsonNaming 어노테이션을 사용중이면 메서드명을 JsonNaming하여 전달하게 됨.
    Json으로 전달하지않을 메서드가 있을 경우 @JsonIgnore 어노테이션을 추가하여야함.
    @JsonProperty("newName") 어노테이션은 강제로 파라미터를 객체 이름으로 반환함.
    public int getName() {
        return userName;
    }
    */
    /*
        objectMapper.readValue(json, UserRequest.class)시에는 setter 또는 getter
        getter시 json 오브젝트 명에 맞게 매핑이 되므로 set메서드명을 잘 맞춰야함.
        혹은 @JsonProperty 어노테이션을 사용해도 됨.
    */
}
