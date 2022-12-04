package com.keyin.tournaments;

import com.keyin.members.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @SequenceGenerator(name = "tourney_sequence", sequenceName = "tourney_sequence", allocationSize = 1)
    @GeneratedValue(generator = "tourney_sequence")
    private long id;
    private Date startDate;
    private Date endDate;
    private String name;
    private String location;
    private double entryFee;
    private double cashPrize;
    @ManyToMany
    private List<Member> participants;

    public Tournament() {
    }

    public Tournament(Date startDate, Date endDate, String name, String location, double entryFee, double cashPrize) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.location = location;
        this.entryFee = entryFee;
        this.cashPrize = cashPrize;
        this.participants = null;
    }

    public long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public double getCashPrize() {
        return cashPrize;
    }

    public void setCashPrize(double cashPrize) {
        this.cashPrize = cashPrize;
    }

    public void register(Member participant){
        participants.add(participant);
    }

    public void deRegister(Member participant){
        participants.remove(participant);
    }

    public List<Member> getParticipants(){
        return participants;
    }

}
