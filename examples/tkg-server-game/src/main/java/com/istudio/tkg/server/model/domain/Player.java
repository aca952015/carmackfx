package com.istudio.tkg.server.model.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.LinkedList;

@Data
public class Player {

    @Id
    private String id;
    private String owner;
    private String name;
    private int gold1;
    private int gold2;
    private long silver;
    private int energy;
    private int token;
    private int level;
    private int experience;
    private int rankLevel;
    private int rankExperience;
    private int leaderCardId;
    private int leaderCardLevel;
    private int vipLevel;
    private int vipExperience;
    private int maxPower;
    private long totalOnlineTime;
    private long leaveTime;

    private LinkedList<Card> cards;
    private LinkedList<Item> items;

    public void init() {

    }
}
