package modules.users.services;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.enumerations.UserType;
import modules.users.structure.dtos.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

        assertNotNull(result);
        assertEquals(expectedId, result.getId());
        assertEquals(expectedName, result.getName());
        assertEquals(expectedEmail, result.getEmail());
        assertEquals(expectedStatus, UserStatus.valueOf(result.getStatus()));
        assertEquals(expectedRole, UserRoles.valueOf(result.getRole()));
        assertEquals(expectedType, UserType.valueOf(result.getType()));
    }

    @Test
    @DataSet(cleanAfter = true)
    @DisplayName("Deve ser possível salvar um usuário")
    void shouldBePossibleToSaveAUser() {
        final var expectedName = "João da Silva";
        final var expectedEmail = "joao@mail.com";
        final var expectedPassword = "123456";
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

        final UserDTO savedUser = service.save(userDTO);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getDhRegister());
        assertNull(savedUser.getLastLogin());
        assertNull(savedUser.getCode());
        assertNull(userDTO.getLastUpdate());
        assertNotEquals(expectedPassword, savedUser.getPassword());
        assertEquals(expectedName, savedUser.getName());
        assertEquals(expectedEmail, savedUser.getEmail());
        assertEquals(expectedStatus, UserStatus.valueOf(savedUser.getStatus()));
        assertEquals(expectedRole, UserRoles.valueOf(savedUser.getRole()));
        assertEquals(expectedType, UserType.valueOf(savedUser.getType()));
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

        final UserUpdateDTO result = service.update(userUpdateDTO);

        assertNotNull(result);
        assertEquals(expectedId, UUID.fromString(result.getId()));
        assertEquals(expectedName, result.getName());
        assertEquals(expectedEmail, result.getEmail());
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

        assertEquals(UserStatus.INACTIVE, UserStatus.valueOf(result.getStatus()));
    }

    @Test
    @DisplayName("Deve ser possível alterar a senha de um usuário")
    @DataSet(value = "datasets/users/userChangePassword.yml", cleanAfter = true)
    void shouldBePossibleToChangePasswordOfAUser() {
        final var expectedId = UUID.fromString("a6899a78-f819-4b90-8298-1966485dd90e");
        final var oldPassword = "654321";
        final var expectedPassword = "123456";

        UserChangePasswordDTO userDTO = new UserChangePasswordDTO();
        userDTO.setId(String.valueOf(expectedId));
        userDTO.setPassword(expectedPassword);
        userDTO.setOldPassword(oldPassword);

        final UserChangePasswordDTO result = service.changePassword(userDTO);
        assertEquals(expectedId, UUID.fromString(result.getId()));
        assertNotEquals(expectedPassword, result.getPassword());
    }
}