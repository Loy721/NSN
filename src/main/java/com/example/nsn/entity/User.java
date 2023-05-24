package com.example.nsn.entity;

import com.example.nsn.entity.view.Views;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    //@JsonView(Views.RoleUser.class)
    private String uid;
    //@JsonView(Views.RoleUser.class)
    private String name;
    //@JsonView(Views.RoleUser.class)
    private String surname;
    //@JsonView(Views.RoleAdmin.class)
    private String email;
    private String password;
    private String photo;
    //@JsonView(Views.RoleAdmin.class)
    private Boolean isActive;
    //@JsonView(Views.RoleUser.class)
    private LocalDate birthday;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_uid"))
    @Enumerated(EnumType.STRING)
    //@JsonView(Views.RoleAdmin.class)
    private Set<Role> role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name = "subscriber_id")}
    )
    @JsonIgnore
    Set<User> subscribers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = {@JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = {@JoinColumn(name = "channel_id")}
    )
    @JsonIgnore
    Set<User> subscriptions = new HashSet<>();

}
