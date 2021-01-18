package com.game.service.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.game.service.domain.converter.LocalDateTimeConverter;
import com.game.service.domain.entities.type.LoginType;
import com.game.service.domain.entities.type.UserRole;
import com.game.service.domain.entities.type.UserState;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document(collection = "users")
@NoArgsConstructor
public class User extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private Integer id;

    @Field(name = "username")
    private String username;

    @Field(name = "password")
    @JsonIgnore
    private String password;

    @Field(name = "facebook_id")
    private String facebookId;

    @Field(name = "google_id")
    private String googleId;

    @Field(name = "confirmed_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime confirmedAt;

    @Field(name = "last_login_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime lastLoginAt;

    @Enumerated(EnumType.STRING)
    @Field(name = "state")
    private UserState state = UserState.NOT_VERIFIED;

    @Field(name = "partner_user_id")
    @Indexed
    private String partnerUserId;

    @Enumerated(EnumType.STRING)
    @Field(name = "login_type")
    private LoginType loginType;

    // references table
    @Field(name = "partner_id")
    @Indexed
    private Long partnerId;

    @Field
    private Profile profile;

    @Field
    private Wallet wallet;

    @Field
    private Point point;

    @Field(name = "login_continuous")
    private LoginContinuous loginContinuous;

    @Field(name = "role")
    private UserRole role;


    @Field(name = "is_updated")
    private Boolean isUpdated;

    @Field(name = "is_receive_gift_start") //da nhan qua
    private Boolean isReceiveGiftStart;

    @Field(name = "lucky_spin") // so vong quay
    private Integer luckySpin;

    @Data
    public static class Profile {

        @Field(name = "full_name")
        private String fullName;

        @Field(name = "avatar_url")
        private String avatarUrl;

        @Field(name = "email")
        private String email;

        @Field(name = "phone_number")
        private String phoneNumber;
    }

    @Data
    public static class LoginContinuous {

        @Field(name = "current_date")
        private LocalDate currentDate;

        @Field(name = "before_date")
        private LocalDate beforeDate;

        @Field(name = "count_day")
        private Integer countDay;

        @Field(name = "is_updated")
        private Boolean isCheckIn;
    }
}
