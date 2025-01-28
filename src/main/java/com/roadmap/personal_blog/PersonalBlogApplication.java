package com.roadmap.personal_blog;

import com.roadmap.personal_blog.entities.security.PermissionEntity;
import com.roadmap.personal_blog.entities.security.RoleEntity;
import com.roadmap.personal_blog.entities.security.RoleEnum;
import com.roadmap.personal_blog.entities.security.UserEntity;
import com.roadmap.personal_blog.repositories.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class PersonalBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalBlogApplication.class, args);
	}

}
