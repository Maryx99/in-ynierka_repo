package com.MarekLadziak.WebApp.controller;

import com.MarekLadziak.WebApp.models.User;
import com.MarekLadziak.WebApp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
// ta adnotacja sluzy nam do tego ze nie musimy przy kazdym @...Mapping powtarzac tej samej czesci
// np. zwroc uwage ze w metodzie getUsers() w @GetMapping nie podaje zadnej sciezki
public class UserController {

    // wszystkie pola wstrzykiwane powinny byc finalne
    // @Autowired na polach jest niezalecane przez Springa - powoduje to problemy przy testowaniu
    // najlepiej jest to robic przez konstruktor
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    // ta metoda raczej nie bedzie ci potrzebna, rzadko sie tak stosuje ze wyciaga sie tylko jedna dana i zwraca
//    @GetMapping("/api/users/{id}/firstname")
//    public ResponseEntity<String> getUserFirstNameById(@PathVariable Long id) {
//        Optional<User> userOptional = userRepository.findById(id);
//
//        if (userOptional.isPresent()) {
//            String firstName = userOptional.get().getFirstName();
//            return ResponseEntity.ok(firstName);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    //tutaj bardzo wazna rzecz co do nazewnictwa
    // nazwy sciezek powinny byc rzeczownikami najlepiej w liczbie mnogiej
    // jezeli chcesz odroznic update od get to stosuje sie do tego rozne metody HTTP,
    // np GetMapping do pobrania, PostMapping do stworzenia, PutMapping do edycji
    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    } // mysle ze zamiast zwracac "updated" mozna albo zwrocic: id, zaaktualizowanego usera lub void


    // podzial na user/admin mozna osiagnac w inny sposob, ktory pokaze ci jak juz zaimplementujemy security
    // ale robi sie to na podstawie roli, tez sie zastanow czy user moze miec wiecej niz jedna rola
//    @PutMapping(value = "updateadmin/{id}")
//    public String updateAdmin(@PathVariable long id, @RequestBody Admin admin) {
//        Admin updatedAdmin = adminRepository.findById(id).get();
//        updatedAdmin.setRoleAdmin(admin.isRoleAdmin());
//        return "admin updated";
//    }

    @DeleteMapping(value = "/{id}")
    public User deleteUser(@PathVariable long id) {
        return userService.deleteUser(id);
    }
}
