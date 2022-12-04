package com.keyin.members;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController {

    @Autowired
    MemberRepository repo;

    @GetMapping("/members")
    public List<Member> getAllMembers(){
        return (List<Member>) repo.findAll();
    }

    @GetMapping("/member/{id}")
    public Optional<Member> getMemberById(@PathVariable String id, HttpServletResponse response) throws IOException {
        Optional<Member> returnedMember = Optional.ofNullable(repo.findById(Long.parseLong(id)));
        if(returnedMember.isPresent()){
            return returnedMember;
        } else {
            response.sendError(404,"ERROR - Member with ID: " + id + "could not be found");
        }
        return returnedMember;
    }

    @PostMapping("/member")
    public Member addMember(@RequestBody Member memberToAdd){
        repo.save(memberToAdd);
        return memberToAdd;
    }
}
