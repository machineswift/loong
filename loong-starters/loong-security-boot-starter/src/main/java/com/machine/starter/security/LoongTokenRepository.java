package com.machine.starter.security;

import com.machine.client.iam.auth.ILoongAuthTokenClient;
import com.machine.client.iam.auth.dto.LoongAuthTokenAddDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenDto;
import com.machine.client.iam.auth.dto.LoongAuthTokenUpdateTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoongTokenRepository implements PersistentTokenRepository {

    @Autowired
    private ILoongAuthTokenClient authTokenClient;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        LoongAuthTokenAddDto dto = new LoongAuthTokenAddDto();
        dto.setUsername(token.getUsername());
        dto.setSeries(token.getSeries());
        dto.setToken(token.getTokenValue());
        authTokenClient.add(dto);
    }

    @Override
    public void updateToken(String series,
                            String tokenValue,
                            Date lastUsed) {
        LoongAuthTokenUpdateTokenDto dto = new LoongAuthTokenUpdateTokenDto();
        dto.setSeries(series);
        dto.setToken(tokenValue);
        authTokenClient.updateToken(dto);
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        LoongAuthTokenDto dto = authTokenClient.getBySeries(seriesId);
        if (null == dto) {
            return null;
        }

        return new PersistentRememberMeToken(dto.getUserName(),
                dto.getSeries(), dto.getToken(), new Date(dto.getUpdateTime()));
    }

    @Override
    public void removeUserTokens(String username) {
        authTokenClient.deleteByUserName(username);
    }
}
