package game.heroes.service.services;

import game.heroes.service.models.LoginUserServiceModel;
import game.heroes.service.models.auth.RegisterUserServiceModel;

public interface AuthService {
    void register(RegisterUserServiceModel model);

    LoginUserServiceModel login(RegisterUserServiceModel serviceModel) throws Exception;
}
