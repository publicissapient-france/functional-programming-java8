package fr.xebia.java8.refactoring.step3;

import fr.xebia.java8.refactoring.data.Role;
import fr.xebia.java8.refactoring.data.User;
import fr.xebia.java8.refactoring.data.UsersAgeStatistic;
import fr.xebia.java8.refactoring.other.CurrentDate;
import fr.xebia.java8.refactoring.step2.UserService;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
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
    public void should_return_formatted_address_when_user_with_address_exist() {
        assertThat(userService.retrieveFormattedUserAddressByLogin("dominiquevincent")).isEqualTo("5 6th Street\nLIGHTFOOT 23090");
    }

    @Test
    public void should_return_default_formatted_address_when_user_is_without_address() {
        assertThat(userService.retrieveFormattedUserAddressByLogin("christianemoreau")).isEqualTo("1 rue de Rivoli\n75001 Paris");
    }

    @Test
    public void should_return_default_formatted_address_when_user_not_found() {
        assertThat(userService.retrieveFormattedUserAddressByLogin("ivanbeauvais")).isEqualTo("1 rue de Rivoli\n75001 Paris");
    }

    @Test
    public void should_return_list_ordered_by_firstname_and_lastname() {
        //when
        List<User> usersOrdered = userService.firstFiftyUsers();

        //Then
        assertThat(usersOrdered).hasSize(50);
        assertThat(usersOrdered.toString()).isEqualTo("[User{title='M', firstname='Alain', lastname='André'}, User{title='M', firstname='Alain', lastname='André'}, User{title='M', firstname='Andre', lastname='André'}, User{title='Mme', firstname='Andree', lastname='André'}, User{title='M', firstname='Bruno', lastname='André'}, User{title='Mme', firstname='Caroline', lastname='André'}, User{title='Mme', firstname='Cecile', lastname='André'}, User{title='Mme', firstname='Claire', lastname='André'}, User{title='M', firstname='Claude', lastname='André'}, User{title='Mme', firstname='Denise', lastname='André'}, User{title='M', firstname='Didier', lastname='André'}, User{title='M', firstname='Dominique', lastname='André'}, User{title='Mme', firstname='Francoise', lastname='André'}, User{title='M', firstname='Gerard', lastname='André'}, User{title='Mme', firstname='Isabelle', lastname='André'}, User{title='Mme', firstname='Jacqueline', lastname='André'}, User{title='Mme', firstname='Josette', lastname='André'}, User{title='Mme', firstname='Josette', lastname='André'}, User{title='M', firstname='Julien', lastname='André'}, User{title='M', firstname='Laurent', lastname='André'}, User{title='Mme', firstname='Madeleine', lastname='André'}, User{title='Mme', firstname='Madeleine', lastname='André'}, User{title='M', firstname='Marcel', lastname='André'}, User{title='M', firstname='Marcel', lastname='André'}, User{title='Mme', firstname='Michele', lastname='André'}, User{title='Mme', firstname='Monique', lastname='André'}, User{title='Mme', firstname='Paulette', lastname='André'}, User{title='M', firstname='Serge', lastname='André'}, User{title='M', firstname='Thierry', lastname='André'}, User{title='M', firstname='Andre', lastname='Bernard'}, User{title='M', firstname='Andre', lastname='Bernard'}, User{title='M', firstname='Andre', lastname='Bernard'}, User{title='Mme', firstname='Andree', lastname='Bernard'}, User{title='Mme', firstname='Anne', lastname='Bernard'}, User{title='Mme', firstname='Anne', lastname='Bernard'}, User{title='Mme', firstname='Annie', lastname='Bernard'}, User{title='Mme', firstname='Annie', lastname='Bernard'}, User{title='Mme', firstname='Audrey', lastname='Bernard'}, User{title='Mme', firstname='Aurelie', lastname='Bernard'}, User{title='Mme', firstname='Aurelie', lastname='Bernard'}, User{title='Mme', firstname='Bernadette', lastname='Bernard'}, User{title='Mme', firstname='Caroline', lastname='Bernard'}, User{title='Mme', firstname='Catherine', lastname='Bernard'}, User{title='Mme', firstname='Chantal', lastname='Bernard'}, User{title='Mme', firstname='Chantal', lastname='Bernard'}, User{title='Mme', firstname='Christelle', lastname='Bernard'}, User{title='Mme', firstname='Christine', lastname='Bernard'}, User{title='Mme', firstname='Claire', lastname='Bernard'}, User{title='Mme', firstname='Claudine', lastname='Bernard'}, User{title='Mme', firstname='Colette', lastname='Bernard'}]");
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
        Map<String, User> usersByLogin = userService.retrieveUserWithRoleByLogin(Role.SALES);

        assertThat(usersByLogin).hasSize(340);
        assertThat(usersByLogin.entrySet()).are(keyIsLoginOfValue());
        assertThat(usersByLogin.values()).are(userWithRole(Role.SALES));

    }

    @Test
    public void should_return_users_statistics() {
        mockStatic(CurrentDate.class);
        when(CurrentDate.get()).thenReturn(LocalDate.of(2014, 5, 7));
        Locale.setDefault(Locale.FRANCE);

        assertThat(userService.generateAgeStatistic()).isEqualTo(new UsersAgeStatistic(1000, 15, 105, 60.334));
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