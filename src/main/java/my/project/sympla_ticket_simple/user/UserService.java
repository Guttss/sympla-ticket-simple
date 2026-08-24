package my.project.sympla_ticket_simple.user;

import lombok.RequiredArgsConstructor;
import my.project.sympla_ticket_simple.user.dto.UserRequestDTO;
import my.project.sympla_ticket_simple.user.dto.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO){

        if(userRepository.existsByEmail(userRequestDTO.email())){
            throw new EmailAlreadyInUseException("O email ja está sendo usado por outro usuario!");
        }

        User user = new User();
        user.setUsername(userRequestDTO.username());
        user.setPassword(passwordEncoder.encode(userRequestDTO.password())); // Criptografa a senha
        user.setEmail(userRequestDTO.email());

        User userSaved = userRepository.save(user);

        return new UserResponseDTO(userSaved.getId(),  userSaved.getUsername(), userSaved.getEmail());
    }

    public UserResponseDTO findById(Long id){

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado!"));

        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail());
    }

    public List<UserResponseDTO> findAll(){
        List<User> users = userRepository.findAll();

        return users.stream().map(user -> new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail())).toList();
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado!"));

        user.setUsername(userRequestDTO.username());
        user.setEmail(userRequestDTO.email());
        User userSaved = userRepository.save(user);

        return new UserResponseDTO(userSaved.getId(),  userSaved.getUsername(), userSaved.getEmail());
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado!"));
        userRepository.delete(user);
    }
}
