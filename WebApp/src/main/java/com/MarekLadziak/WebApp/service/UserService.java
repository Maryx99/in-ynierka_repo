package com.MarekLadziak.WebApp.service; // nazwa pakietu powinna byc zawsze z malych liter

import com.MarekLadziak.WebApp.exception.UserNotFoundException;
import com.MarekLadziak.WebApp.models.User;
import com.MarekLadziak.WebApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.MarekLadziak.WebApp.exception.message.ErrorMessage.USER_NOT_FOUND_ERROR;

// stworzylem tez service - tutaj dzieje sie logika biznesowa, controller (tak jak miales wczesniej) jest bardziej miejscem
// do komunikacji z swiatem "zewnetrznym" czyli np aplikacja napisana w Angularze
// w dodatku pamietaj by dobrze nazywac klasy, nazwa Controller jest slaba i powinienes doprecyzowac np UserController
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // jeszcze jedna wazna rzecza jest Dto, zamiast encji User dobra praktyka jest stworzyc klase
    // UserDto, tylko z potrzebnymi polami. np nie powinnismy zwracac tej encji poniewaz ma prywatne dane ktore nie powinny
    // byc dostepne dla innych ludzi, np haslo
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR.getMessage(), id)));
    }

    public User updateUser(long id, User user) {
        User savedUser = userRepository.findById(id) // warto stworzyc swoje wyjatki i rzucac je gdy cos pojdzie nie tak
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR.getMessage(), id)));
        // w dodatku jak pobierasz cos z bazy i zwraca to Optional to zawsze musisz sprawdzic czy on istnieje
        // inaczej poleci ci NPE

        //tutaj mozna uzyc np MapStruct do mapowania ale nie chce tez wprowadzac zbyt wiele bibliotek, dlatego na razie
        // zrobimy to recznie i w trakcie gdy baza bedzie powstawac zadbany o mapowanie lacznie z DTO
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword(user.getPassword());
        savedUser.setAge(user.getAge());
        savedUser.setOccupation(user.getOccupation());
        savedUser.setRole(user.getRole());

        return userRepository.save(savedUser);
    }

    // czy powinienes cos zwracac na front w takich sytuacjach? to zalezy - jak aplikacja jest w trakcie developmentu
    // to moim zdaniem warto jest cos zwrocic bo ulatwia ci to prace nad aplikacja, ale nic nie stoi na przeszkodzie
    // bys zawsze w operacjach innych niz getSomething() zwracal void
    public User deleteUser(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND_ERROR.getMessage(), id)));
        userRepository.deleteById(id);
        return user;
    }
}
