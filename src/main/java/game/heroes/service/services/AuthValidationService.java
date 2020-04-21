package game.heroes.service.services;

import game.heroes.service.models.auth.RegisterUserServiceModel;

public interface AuthValidationService {
    boolean isValid(RegisterUserServiceModel user);
}
