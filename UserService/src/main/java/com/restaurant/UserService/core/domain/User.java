package com.restaurant.UserService.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="User")
@ToString(exclude="password")
@EqualsAndHashCode
public class User {
    @Id
    @Getter @Setter
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Getter @Setter
    private String password;
    @Getter @Setter
    private UserRole role;

    @Getter
    private String firstName;
    @Getter
    private String lastName;

    public User() {
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = UserRole.User;
    }
}
