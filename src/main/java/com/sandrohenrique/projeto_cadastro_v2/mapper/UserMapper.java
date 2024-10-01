package com.sandrohenrique.projeto_cadastro_v2.mapper;

import com.sandrohenrique.projeto_cadastro_v2.domain.User;
import com.sandrohenrique.projeto_cadastro_v2.requests.UserPostRequestBody;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public abstract User toUser(UserPostRequestBody userPostRequestBody);
}
