package com.example.demo.controller;

import com.example.demo.dto.VoteRequest;
import com.example.demo.model.User;
import com.example.demo.model.Vote;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VoteRepository;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vote")
public class VoteController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public String castVote(@RequestBody VoteRequest voteRequest) {
        String username = jwtUtil.validateToken(voteRequest.getToken());
        if (username == null) {
            return "Invalid or expired token!";
        }

        User user = userRepository.findByUsername(username);

        if (user == null) {
            return "User not found!";
        }

        if (user.isHasVoted()) {
            return "You have already voted!";
        }

        Vote vote = new Vote();
        vote.setCandidate(voteRequest.getCandidate());
        vote.setUser(user);

        voteRepository.save(vote);

        user.setHasVoted(true);
        userRepository.save(user);

        return "Vote cast successfully!";
    }
}
