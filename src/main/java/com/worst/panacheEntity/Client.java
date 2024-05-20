package com.worst.panacheEntity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "client")
@ToString
public class Client extends PanacheEntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name ="surname")
    private String surname;

    @Basic
    @Column(name = "age")
    private Integer age;

    @Basic
    @Column(name ="email")
    private String email;

    @Nullable
    @OneToMany(mappedBy = "client")
    @Basic
    private List<Card> cards;

}