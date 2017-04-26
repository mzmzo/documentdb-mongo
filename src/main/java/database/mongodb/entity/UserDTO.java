package database.mongodb.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private String id;

    private String name;

    List<UserAwardRecord> awards;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserAwardRecord> getAwards() {
        return awards;
    }

    public void setAwards(List<UserAwardRecord> awards) {
        this.awards = awards;
    }

    @Override
    public String toString() {
        try {
            return String.format("id：%s，name：%s，awards： %s", id, name, new ObjectMapper().writer().writeValueAsString(awards));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
