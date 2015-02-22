package fr.xebia.java8.refactoring.step3;

import fr.xebia.java8.refactoring.data.Role;
import fr.xebia.java8.refactoring.data.User;
import fr.xebia.java8.refactoring.other.CurrentDate;
import fr.xebia.java8.refactoring.step2.UserService;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CurrentDate.class})
public class UserServiceTest {

    private UserService userService = new UserService();

    @Test
    public void should_return_count_of_Role() {
        assertThat(userService.countUserWithRole(Role.SALES)).isEqualTo(340);
        assertThat(userService.countUserWithRole(Role.ENGINEER)).isEqualTo(367);
        assertThat(userService.countUserWithRole(Role.TRADER)).isEqualTo(293);
    }

    @Test
    public void should_return_true_if_login_exist() {
        assertThat(userService.isLoginAlreadyExist("christianemoreau")).isTrue();
        assertThat(userService.isLoginAlreadyExist("ivanbeauvais")).isFalse();

    }

    @Test
    public void should_return_formated_address_when_user_with_address_exist() {
        assertThat(userService.retrieveFormatedUserAddressByLogin("dominiquevincent")).isEqualTo("5 6th Street\nLIGHTFOOT 23090");
    }

    @Test
    public void should_return_formated_address_when_user_without_address_exist() {
        assertThat(userService.retrieveFormatedUserAddressByLogin("christianemoreau")).isEqualTo("1 rue de Rivoli\n75001 Paris");
    }

    @Test
    public void should_return_throw_exception_when_user_not_exist() {
        assertThat(userService.retrieveFormatedUserAddressByLogin("ivanbeauvais")).isEqualTo("1 rue de Rivoli\n75001 Paris");
    }

    @Test
    public void should_return_list_ordered_by_firstname_and_lastname() {
        //given
        List<User> users = new ArrayList<>();
        users.add(new User("Mme", "Martine", "Mercier"));
        users.add(new User("M.", "Christophe", "Mercier"));
        users.add(new User("Mme", "Pascal", "Dubois"));

        UserService userService = new UserService(users);

        //when
        List<User> usersOrdered = userService.findAll();

        //Then
        assertThat(usersOrdered.toString()).isEqualTo("[User{title='Mme', firstname='Pascal', lastname='Dubois'}, User{title='M.', firstname='Christophe', lastname='Mercier'}, User{title='Mme', firstname='Martine', lastname='Mercier'}]");
    }

    @Test
    public void should_return_active_users_by_role() {
        mockStatic(CurrentDate.class);
        when(CurrentDate.get()).thenReturn(LocalDate.of(2014, 5, 7));

        Map<Role, List<User>> activeUsersByRole = userService.retrieveActiveUserByRole();

        List<User> engineers = activeUsersByRole.get(Role.ENGINEER);
        List<User> traders = activeUsersByRole.get(Role.TRADER);
        List<User> sales = activeUsersByRole.get(Role.SALES);

        assertThat(engineers).hasSize(127);
        assertThat(sales).hasSize(101);
        assertThat(traders).hasSize(98);

        assertThat(engineers).are(activeUser()).are(userWithRole(Role.ENGINEER));
        assertThat(traders).are(activeUser()).are(userWithRole(Role.TRADER));
        assertThat(sales).are(activeUser()).are(userWithRole(Role.SALES));
    }

    @Test
    public void should_return_user_by_login() {
        Map<String, User> usersByLogin = userService.retrieveUserwithRoleByLogin(Role.SALES);

        assertThat(usersByLogin).hasSize(340);
        assertThat(usersByLogin.entrySet()).are(keyIsLoginOfValue());
        assertThat(usersByLogin.values()).are(userWithRole(Role.SALES));

    }

    @Test
    public void should_return_users_statistics() {
        mockStatic(CurrentDate.class);
        when(CurrentDate.get()).thenReturn(LocalDate.of(2014, 5, 7));
        Locale.setDefault(Locale.FRANCE);

        assertThat(userService.generateAgeStatistic()).isEqualTo("Number of user : 1000\n" +
                "Age min : 15\n" +
                "Age max : 105\n" +
                "Age average : 60,33");
    }

    private Condition<Map.Entry<String, User>> keyIsLoginOfValue() {
        return new Condition<Map.Entry<String, User>>() {
            public boolean matches(Map.Entry<String, User> value) {
                return value.getKey().equals(value.getValue().getLogin());
            }
        };
    }

    private Condition<User> userWithRole(Role role) {
        return new Condition<User>() {
            @Override
            public boolean matches(User user) {
                return user.getRole() == role;
            }
        };
    }

    private Condition<User> activeUser() {
        return new Condition<User>() {
            public boolean matches(User user) {
                return !user.isExpired();
            }
        };
    }
}