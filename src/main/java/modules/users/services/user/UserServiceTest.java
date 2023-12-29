package modules.users.services.user;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import core.emailservice.SendEmailService;
import core.exceptions.InvalidFormatEmailException;
import core.exceptions.NotNullException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.enumerations.UserType;
import modules.users.structure.dtos.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@DBRider
@QuarkusTest
@DisplayName("Teste de integração da classe de serviço de usuário")
class UserServiceTest {

    @Inject
    UserService service;

    @InjectMocks
    SendEmailService sendEmailService;

    @Test
    @DataSet(value = "datasets/users/userGetById.yml", cleanAfter = true)
    @DisplayName("Deve ser possível buscar um usuário pelo id")
    void shouldBePossibleToGetAUserById() {
        final var expectedId = "a6899a78-f819-4b90-8298-1966485dd90e";
        final var expectedName = "João da Silva";
        final var expectedEmail = "joao@mail.com";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        final UserGetDTO result = service.getById(expectedId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedId, result.getId());
        Assertions.assertEquals(expectedName, result.getName());
        Assertions.assertEquals(expectedEmail, result.getEmail());
        Assertions.assertEquals(expectedStatus, UserStatus.valueOf(result.getStatus()));
        Assertions.assertEquals(expectedRole, UserRoles.valueOf(result.getRole()));
        Assertions.assertEquals(expectedType, UserType.valueOf(result.getType()));
    }

    @Test
    @DisplayName("Deve ser possível buscar um usuário pelo email")
    @DataSet(value = "datasets/users/userGetByEmail.yml", cleanAfter = true)
    void shouldBePossibleToGetAUserByEmail() {
        final var expectedId = "a6899a78-f819-4b90-8298-1966485dd90e";
        final var expectedName = "João da Silva";
        final var expectedEmail = "joao@mail.com";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        final UserGetDTO result = service.getByEmail(expectedEmail);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedId, result.getId());
        Assertions.assertEquals(expectedName, result.getName());
        Assertions.assertEquals(expectedEmail, result.getEmail());
        Assertions.assertEquals(expectedStatus, UserStatus.valueOf(result.getStatus()));
        Assertions.assertEquals(expectedRole, UserRoles.valueOf(result.getRole()));
        Assertions.assertEquals(expectedType, UserType.valueOf(result.getType()));
    }

    @Test
    @DisplayName("Não deve ser possível salvar um usuário com o nome nulo")
    void shouldNotBePossibleToSaveAUserWithNullName() {
        final var expectedMessage = "O campo Nome não pode ser nulo";
        final var expectedEmail = "joao@mail.com";
        final var expectedPassword = "123456";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(expectedEmail);
        userDTO.setPassword(expectedPassword);
        userDTO.setStatus(UserStatus.valueOf(expectedStatus));
        userDTO.setRole(UserRoles.valueOf(expectedRole));
        userDTO.setType(UserType.valueOf(expectedType));

        final NotNullException exception = Assertions.assertThrows(NotNullException.class,
            () -> service.save(userDTO));

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Não deve ser possível salvar um usuário com o email nulo")
    void shouldNotBePossibleToSaveAUserWithNullEmail() {
        final var expectedMessage = "O campo Email não pode ser nulo";
        final var expectedName = "João da Silva";
        final var expectedPassword = "123456";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(expectedName);
        userDTO.setPassword(expectedPassword);
        userDTO.setStatus(UserStatus.valueOf(expectedStatus));
        userDTO.setRole(UserRoles.valueOf(expectedRole));
        userDTO.setType(UserType.valueOf(expectedType));

        final NotNullException exception = Assertions.assertThrows(NotNullException.class,
            () -> service.save(userDTO));

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Não deve ser possível salvar um usuário com a senha nula")
    void shouldNotBePossibleToSaveAUserWithNullPassword() {
        final var expectedMessage = "O campo Senha não pode ser nulo";
        final var expectedName = "João da Silva";
        final var expectedEmail = "joaosilva@mail.com";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(expectedName);
        userDTO.setEmail(expectedEmail);
        userDTO.setStatus(UserStatus.valueOf(expectedStatus));
        userDTO.setRole(UserRoles.valueOf(expectedRole));
        userDTO.setType(UserType.valueOf(expectedType));

        final NotNullException exception = Assertions.assertThrows(NotNullException.class,
            () -> service.save(userDTO));

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Não deve ser possível salvar um usuário com o tipo nulo")
    void shouldNotBePossibleToSaveAUserWithNullType() {
        final var expectedMessage = "O campo Tipo de usuário não pode ser nulo";
        final var expectedName = "João da Silva";
        final var expectedPassword = "123456";
        final var expectedEmail = "joaosilva@mail.com";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(expectedName);
        userDTO.setEmail(expectedEmail);
        userDTO.setPassword(expectedPassword);
        userDTO.setStatus(UserStatus.valueOf(expectedStatus));
        userDTO.setRole(UserRoles.valueOf(expectedRole));

        final NotNullException exception = Assertions.assertThrows(NotNullException.class,
            () -> service.save(userDTO));

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    @DisplayName("Não deve ser possível salvar um usuário com o formato de email incorreto")
    void shouldNotBePossibleSaveAUserWithIncorrectEmailFormat() {
        final var expectedMessage = "O campo email não está em um formato válido";
        final var expectedName = "João da Silva";
        final var expectedPassword = "123456";
        final var expectedEmail = "joaosilva";
        final var expectedStatus = UserStatus.ACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(expectedName);
        userDTO.setEmail(expectedEmail);
        userDTO.setPassword(expectedPassword);
        userDTO.setStatus(UserStatus.valueOf(expectedStatus));
        userDTO.setRole(UserRoles.valueOf(expectedRole));
        userDTO.setType(UserType.valueOf(expectedType));

        final InvalidFormatEmailException exception = Assertions.assertThrows(InvalidFormatEmailException.class,
            () -> service.save(userDTO));

        Assertions.assertEquals(exception.getMessage(), expectedMessage);
    }


    @Test
    @DataSet(cleanAfter = true)
    @DisplayName("Deve ser possível salvar um usuário")
    void shouldBePossibleToSaveAUser() {
        final var expectedName = "João da Silva";
        final var expectedEmail = "joao@mail.com";
        final var expectedPassword = "123456";
        final var expectedStatus = UserStatus.INACTIVE;
        final var expectedRole = UserRoles.BASIC;
        final var expectedType = UserType.BOTH;

        UserDTO userDTO = new UserDTO();
        userDTO.setName(expectedName);
        userDTO.setEmail(expectedEmail);
        userDTO.setPassword(expectedPassword);
        userDTO.setStatus(UserStatus.valueOf(expectedStatus));
        userDTO.setRole(UserRoles.valueOf(expectedRole));
        userDTO.setType(UserType.valueOf(expectedType));

        final UserDTO savedUser = service.save(userDTO);

        Assertions.assertNotNull(savedUser);
        Assertions.assertNotNull(savedUser.getId());
        Assertions.assertNotNull(savedUser.getDhRegister());
        Assertions.assertNull(savedUser.getLastLogin());
        Assertions.assertNull(userDTO.getLastUpdate());
        Assertions.assertNotEquals(expectedPassword, savedUser.getPassword());
        Assertions.assertEquals(expectedName, savedUser.getName());
        Assertions.assertEquals(expectedEmail, savedUser.getEmail());
        Assertions.assertEquals(expectedStatus, UserStatus.valueOf(savedUser.getStatus()));
        Assertions.assertEquals(expectedRole, UserRoles.valueOf(savedUser.getRole()));
        Assertions.assertEquals(expectedType, UserType.valueOf(savedUser.getType()));
    }

    @Test
    @DisplayName("Deve ser possível atualizar um usuário")
    @DataSet(value = "datasets/users/userUpdate.yml", cleanAfter = true)
    void shouldBePossibleToUpdateAUser() {
        final var expectedId = UUID.fromString("a6899a78-f819-4b90-8298-1966485dd90e");
        final var expectedName = "José da Costa";
        final var expectedEmail = "josecosta@mail.com";

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setId(String.valueOf(expectedId));
        userUpdateDTO.setName(expectedName);
        userUpdateDTO.setEmail(expectedEmail);
        userUpdateDTO.setCode("1B7F83");

        final UserUpdateDTO result = service.update(userUpdateDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(expectedId, UUID.fromString(result.getId()));
        Assertions.assertEquals(expectedName, result.getName());
        Assertions.assertEquals(expectedEmail, result.getEmail());
    }

    @Test
    @DisplayName("Deve ser possível inativar um usuário")
    @DataSet(value = "datasets/users/userInative.yml", cleanAfter = true)
    void shouldBePossibleToInativeAUser() {
        final var expectedId = UUID.fromString("a6899a78-f819-4b90-8298-1966485dd90e");
        final var expectedCode = "94A0B3";

        UserInativeDTO userInativeDTO = new UserInativeDTO();
        userInativeDTO.setId(String.valueOf(expectedId));
        userInativeDTO.setCode(expectedCode);

        service.inativeUser(userInativeDTO);

        final UserGetDTO result = service.getById(String.valueOf(expectedId));

        Assertions.assertEquals(UserStatus.INACTIVE, UserStatus.valueOf(result.getStatus()));
    }
    @Test
    @DisplayName("Deve ser possível ativar um usuário")
    @DataSet(value = "datasets/users/userActive.yml", cleanAfter = true)
    void shouldBePossibleToActiveAUser() {
        final var expectedId = UUID.fromString("a6899a78-f819-4b90-8298-1966485dd90e");
        final var expectedCode = "94A0B3";

        UserActiveDTO userActiveDTO = new UserActiveDTO();
        userActiveDTO.setId(String.valueOf(expectedId));
        userActiveDTO.setCode(expectedCode);

        service.activeUser(userActiveDTO);

        final UserGetDTO result = service.getById(String.valueOf(expectedId));

        Assertions.assertEquals(UserStatus.ACTIVE, UserStatus.valueOf(result.getStatus()));
    }

    @Test
    @DisplayName("Deve ser possível alterar a senha de um usuário")
    @DataSet(value = "datasets/users/userChangePassword.yml", cleanAfter = true)
    void shouldBePossibleToChangePasswordOfAUser() {
        final var expectedId = UUID.fromString("a6899a78-f819-4b90-8298-1966485dd90e");
        final var oldPassword = "654321";
        final var expectedPassword = "jkQPMTH64P4DD0o7mvP3RcECAuf7ZpAG2du5uyUiWx4=";

        UserChangePasswordDTO userDTO = new UserChangePasswordDTO();
        userDTO.setId(String.valueOf(expectedId));
        userDTO.setPassword(expectedPassword);
        userDTO.setOldPassword(oldPassword);

        final UserChangePasswordDTO result = service.changePassword(userDTO);
        Assertions.assertEquals(expectedId, UUID.fromString(result.getId()));
        Assertions.assertNotEquals(expectedPassword, result.getPassword());
    }
}