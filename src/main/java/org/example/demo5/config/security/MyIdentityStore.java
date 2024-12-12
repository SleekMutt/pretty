package org.example.demo5.config.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import jakarta.security.enterprise.identitystore.IdentityStore;

@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "jdbc/PostgresPool",
        callerQuery = "SELECT password FROM users WHERE username = ?",
        groupsQuery = "SELECT r.role FROM roles r JOIN users u ON u.role_id = r.id WHERE u.username = ?"
)
@ApplicationScoped
public class MyIdentityStore implements IdentityStore {
}