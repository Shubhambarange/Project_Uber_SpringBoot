package com.Shubham.project.uber.uberApp.services.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Shubham.project.uber.uberApp.dto.DriverDto;
import com.Shubham.project.uber.uberApp.dto.SignupDto;
import com.Shubham.project.uber.uberApp.dto.UserDto;
import com.Shubham.project.uber.uberApp.entities.User;
import com.Shubham.project.uber.uberApp.entities.enums.Role;
import com.Shubham.project.uber.uberApp.exceptions.RuntimeConflictException;
import com.Shubham.project.uber.uberApp.repositories.UserRepository;
import com.Shubham.project.uber.uberApp.services.AuthService;
import com.Shubham.project.uber.uberApp.services.RiderService;
import com.Shubham.project.uber.uberApp.services.WalletService;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
        User user = userRepository.findByEmail(signupDto.getEmail()).orElse(null);
        if (user != null)
            throw new RuntimeConflictException("Cannot signup, User already exists with email " + signupDto.getEmail());

        User mappedUser = modelMapper.map(signupDto, User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(mappedUser);

        // create user related entities
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId) {
        return null;
    }
}