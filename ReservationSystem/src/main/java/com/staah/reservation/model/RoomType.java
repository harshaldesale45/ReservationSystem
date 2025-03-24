package com.staah.reservation.model;

import lombok.Getter;

import java.util.List;

@Getter
public class RoomType {
    private String code;
    private String description;
    private List<String> amenities;
    private List<String> features;

}
