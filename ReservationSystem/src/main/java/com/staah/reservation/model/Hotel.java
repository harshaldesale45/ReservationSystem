package com.staah.reservation.model;

import lombok.Getter;

import java.util.List;


@Getter
public class Hotel {
    private String id;
    private String name;
    private List<Room> rooms;
    private List<RoomType> roomTypes;

}
