package com.atomaszewski.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users_statistics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatistics {

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "request_count")
    private Long requestCount;

    @Column(name = "version")
    @Version
    private Integer version;

    public void increaseRequestCount() {
        this.requestCount++;
    }
}
