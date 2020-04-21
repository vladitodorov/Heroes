package game.heroes.service.services.implementations;

import game.heroes.data.models.User;
import game.heroes.data.repositories.UsersRepository;
import game.heroes.service.models.LoginUserServiceModel;
import game.heroes.service.models.auth.RegisterUserServiceModel;
import game.heroes.service.services.AuthService;
import game.heroes.service.services.AuthValidationService;
import game.heroes.service.services.HashingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthValidationService authValidationService;
    private final UsersRepository usersRepository;
    private final ModelMapper mapper;
    private HashingService hashingService;

    public AuthServiceImpl(AuthValidationService authValidationService,
                           UsersRepository usersRepository,
                           ModelMapper mapper,
                           HashingService hashingService){

        this.authValidationService = authValidationService;
        this.usersRepository = usersRepository;
        this.mapper = mapper;
        this.hashingService = hashingService;
    }

    @Override
    public void register(RegisterUserServiceModel model) {
        if(!authValidationService.isValid(model)){
            return;
        }
        User user = mapper.map(model, User.class);
        user.setPassword(hashingService.hash(user.getPassword()));
        usersRepository.saveAndFlush(user);
    }

    @Override
    public LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception {
        String passwordHash = hashingService.hash(serviceModel.getPassword());
        Optional<User> userOptional = usersRepository.findByUsernameAndPassword(serviceModel.getUsername(), passwordHash);
        if(userOptional.isEmpty()){
            throw new Exception("Invalid user");
        }
        User user = userOptional.get();
        return new LoginUserServiceModel(
                serviceModel.getUsername(),
                user.getHero() ==  null ? null : user.getHero().getName()
        );
    }

}
