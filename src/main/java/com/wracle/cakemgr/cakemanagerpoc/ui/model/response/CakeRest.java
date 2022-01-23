package com.wracle.cakemgr.cakemanagerpoc.ui.model.response;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Data
public class CakeRest {
    private Integer id;
    private String name;
    private String description;
    private String imageUrl;
}
