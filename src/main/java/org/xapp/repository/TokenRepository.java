package org.xapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.xapp.entity.Token;

public interface TokenRepository extends CrudRepository<Token, String> {
}

