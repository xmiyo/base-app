package com.xmiyo.base.server.repository;

import com.xmiyo.base.server.repository.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findOneByUsername(String username);
    Account findOneByEmail(String email);
    Account findOneByFacebookId(String facebookId);
    Account findOneByGoogleId(String facebookId);
}