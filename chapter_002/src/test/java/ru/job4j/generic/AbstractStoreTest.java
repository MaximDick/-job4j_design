package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AbstractStoreTest {

    @Test
    public void whenAddSomeUsersThenReplaceOneThenDelete() {
        UserStore userStore = new UserStore(5);
        User user1 = new User("E95");
        User user2 = new User("Y15");
        User user3 = new User("C15");
        User user4 = new User("D05");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        assertThat(userStore.findById("E95"), is(user1));
        userStore.replace(user1.getId(), user4);
        assertThat(userStore.findById("D05"), is(user4));
        assertNull(userStore.findById("E95"));
        userStore.delete("C15");
        assertNull(userStore.findById("C15"));
    }

    @Test
    public void whenAddSomeRolesThenReplaceOneThenDelete() {
        RoleStore roleStore = new RoleStore(5);
        Role role1 = new Role("123");
        Role role2 = new Role("321");
        Role role3 = new Role("555");
        roleStore.add(role1);
        roleStore.add(role2);
        assertThat(roleStore.findById("321"), is(role2));
        assertNull(roleStore.findById("555"));
        roleStore.replace(role2.getId(), role3);
        assertThat(roleStore.findById("555"), is(role3));
        assertNull(roleStore.findById("321"));
        roleStore.delete("123");
        assertNull(roleStore.findById("123"));
    }

}