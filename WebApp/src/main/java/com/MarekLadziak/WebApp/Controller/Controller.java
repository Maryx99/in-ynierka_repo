package com.MarekLadziak.WebApp.Controller;

import com.MarekLadziak.WebApp.Repository.AdminRepository;
import com.MarekLadziak.WebApp.Repository.UserRepository;
import com.MarekLadziak.WebApp.models.Admin;
import com.MarekLadziak.WebApp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@RestController
public class Controller {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping(value = "/")
    @ResponseBody
    public String getPage() throws IOException {
        Resource resource = new ClassPathResource("static/main.html");
        byte[] fileBytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String htmlContent = new String(fileBytes);

        return htmlContent;
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }



    @GetMapping("/api/users/{id}/firstname")
    public ResponseEntity<String> getUserFirstNameById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            String firstName = userOptional.get().getFirstName();
            return ResponseEntity.ok(firstName);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user) {
        User updatedUser = userRepository.findById(id).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAge(user.getAge());
        updatedUser.setOccupation(user.getOccupation());
        updatedUser.setRole(user.getRole());
        userRepository.save(updatedUser);
        return "updated";
    }

    @PutMapping(value = "updateadmin/{id}")
    public String updateAdmin(@PathVariable long id, @RequestBody Admin admin) {
        Admin updatedAdmin = adminRepository.findById(id).get();
        updatedAdmin.setRoleAdmin(admin.isRoleAdmin());
        return "admin updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable long id) {
        User deleteUser = userRepository.findById(id).get();
        userRepository.delete(deleteUser);
        return "delete user with the id: "+id;
    }
}
