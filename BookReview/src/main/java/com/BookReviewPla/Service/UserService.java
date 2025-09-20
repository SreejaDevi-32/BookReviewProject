package com.BookReviewPla.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.BookReviewPla.Repository.UserRepository;
import com.BookReviewPla.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
	
	public void deleteUser(Long userId)
	{
		userRepository.deleteById(userId);
	}

}
