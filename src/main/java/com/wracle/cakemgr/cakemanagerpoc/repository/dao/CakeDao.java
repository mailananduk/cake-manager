package com.wracle.cakemgr.cakemanagerpoc.repository.dao;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CakeDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "NAME", unique = true, nullable = false, length = 100)
    private String name;

    @Column(name = "DESCRIPTION", unique = false, nullable = false, length = 100)
    private String description;

    @Column(name = "IMAGE_URL", unique = false, nullable = false, length = 300)
    private String imageUrl;
}
