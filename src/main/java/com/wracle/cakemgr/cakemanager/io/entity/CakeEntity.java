package com.wracle.cakemgr.cakemanager.io.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "cakes")
public class CakeEntity implements Serializable {

    private static final long serialVersionUID = -2459317720366335598L;

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
