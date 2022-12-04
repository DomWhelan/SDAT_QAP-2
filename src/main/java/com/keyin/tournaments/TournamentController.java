package com.keyin.tournaments;

import com.keyin.members.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class TournamentController {

    @Autowired
    private TournamentRepository repo;

    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments(){
        return repo.findAll();
    }

    @PostMapping("/tournament")
    public Tournament createTournament(@RequestBody Tournament tournament){
        repo.save(tournament);
        return tournament;
    }

    @PutMapping("/tournament/{id}")
    public Tournament updateTournament(@PathVariable String id, @RequestBody Tournament tournament, HttpServletResponse response) {
        Optional<Tournament> tournamentReturned = Optional.ofNullable(repo.findById(Long.parseLong(id)));
        Tournament tournamentToUpdate;
        if(tournamentReturned.isPresent()){
            tournamentToUpdate = tournamentReturned.get();
            tournamentToUpdate.setCashPrize(tournament.getCashPrize());
            tournamentToUpdate.setStartDate(tournament.getStartDate());
            tournamentToUpdate.setEndDate(tournament.getEndDate());
            tournamentToUpdate.setName(tournament.getName());
            tournamentToUpdate.setLocation(tournament.getLocation());
            tournamentToUpdate.setEntryFee(tournament.getEntryFee());
            repo.save(tournamentToUpdate);
            return tournamentToUpdate;
        } else {
            try {
                response.sendError(404, "Tournament with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @PutMapping("/tournament/register/{id}")
    public Tournament register(@PathVariable String id, @RequestBody Member participant, HttpServletResponse response) {
        Optional<Tournament> tournamentReturned = Optional.ofNullable(repo.findById(Long.parseLong(id)));
        Tournament tournamentToRegister;
        if(tournamentReturned.isPresent()){
            tournamentToRegister = tournamentReturned.get();
            tournamentToRegister.register(participant);
            repo.save(tournamentToRegister);
            return tournamentToRegister;
        } else {
            try {
                response.sendError(404, "Tournament with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @PutMapping("/tournament/deregister/{id}")
    public Tournament deRegister(@PathVariable String id, @RequestBody Member participant, HttpServletResponse response) {
        Optional<Tournament> tournamentReturned = Optional.ofNullable(repo.findById(Long.parseLong(id)));
        Tournament tournamentToDeregister;
        if(tournamentReturned.isPresent()){
            tournamentToDeregister = tournamentReturned.get();
            tournamentToDeregister.deRegister(participant);
            repo.save(tournamentToDeregister);
            return tournamentToDeregister;
        } else {
            try {
                response.sendError(404, "Tournament with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }
    }

    @DeleteMapping("/tournament/{id}")
    public void deleteTournament(@PathVariable String id, HttpServletResponse response) {
        Optional<Tournament> tournamentReturned = Optional.ofNullable(repo.findById(Long.parseLong(id)));
        if (tournamentReturned.isPresent()){
            repo.deleteById(Long.parseLong(id));
        } else {
            try {
                response.sendError(404, "Tournament with id: " + id + " not found.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
