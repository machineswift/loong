package com.machine.app.iam.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

public class LoongRememberMeServices extends PersistentTokenBasedRememberMeServices {

//    private PersistentTokenRepository tokenRepository;
//
//    private SecureRandom random;
//
//    public static final int DEFAULT_SERIES_LENGTH = 16;
//
//    public static final int DEFAULT_TOKEN_LENGTH = 16;
//
//    private int seriesLength = DEFAULT_SERIES_LENGTH;
//
//    private int tokenLength = DEFAULT_TOKEN_LENGTH;

    public LoongRememberMeServices(String key,
                                   UserDetailsService userDetailsService,
                                   PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
//        super(key, userDetailsService);
//        this.random = new SecureRandom();
//        this.tokenRepository = tokenRepository;
    }


    @Override
    public boolean rememberMeRequested(final HttpServletRequest request, final String parameter) {
        boolean result = super.rememberMeRequested(request, parameter);
        if (result) {
            return result;
        }

        final String paramValue = (String) request.getAttribute("rememberMe");
        if (paramValue.equalsIgnoreCase("true") || paramValue.equalsIgnoreCase("on")
                || paramValue.equalsIgnoreCase("yes") || paramValue.equals("1")) {
            return true;
        }
        return false;
    }
//
//    @Override
//    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request,
//                                                 HttpServletResponse response) {
//        if (cookieTokens.length != 2) {
//            throw new InvalidCookieException("Cookie token did not contain " + 2 + " tokens, but contained '"
//                    + Arrays.asList(cookieTokens) + "'");
//        }
//        String presentedSeries = cookieTokens[0];
//        String presentedToken = cookieTokens[1];
//        PersistentRememberMeToken token = this.tokenRepository.getTokenForSeries(presentedSeries);
//        if (token == null) {
//            // No series match, so we can't authenticate using this cookie
//            throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
//        }
//        // We have a match for this user/series combination
//        if (!presentedToken.equals(token.getTokenValue())) {
//            // Token doesn't match series value. Delete all logins for this user and throw
//            // an exception to warn them.
//            this.tokenRepository.removeUserTokens(token.getUsername());
//            throw new CookieTheftException(this.messages.getMessage(
//                    "PersistentTokenBasedRememberMeServices.cookieStolen",
//                    "Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."));
//        }
//        if (token.getDate().getTime() + getTokenValiditySeconds() * 1000L < System.currentTimeMillis()) {
//            throw new RememberMeAuthenticationException("Remember-me login has expired");
//        }
//        // Token also matches, so login is valid. Update the token value, keeping the
//        // *same* series number.
//        this.logger.debug(LogMessage.format("Refreshing persistent login token for user '%s', series '%s'",
//                token.getUsername(), token.getSeries()));
//        PersistentRememberMeToken newToken = new PersistentRememberMeToken(token.getUsername(), token.getSeries(),
//                generateTokenData(), new Date());
//        try {
//            this.tokenRepository.updateToken(newToken.getSeries(), newToken.getTokenValue(), newToken.getDate());
//            addCookie(newToken, request, response);
//        } catch (Exception ex) {
//            this.logger.error("Failed to update token: ", ex);
//            throw new RememberMeAuthenticationException("Autologin failed due to data access problem");
//        }
//        return getUserDetailsService().loadUserByUsername(token.getUsername());
//    }
//
//    /**
//     * Creates a new persistent login token with a new series number, stores the data in
//     * the persistent token repository and adds the corresponding cookie to the response.
//     */
//    @Override
//    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response,
//                                  Authentication successfulAuthentication) {
//        String username = successfulAuthentication.getName();
//        this.logger.debug(LogMessage.format("Creating new persistent login for user %s", username));
//        PersistentRememberMeToken persistentToken = new PersistentRememberMeToken(username, generateSeriesData(),
//                generateTokenData(), new Date());
//        try {
//            this.tokenRepository.createNewToken(persistentToken);
//            addCookie(persistentToken, request, response);
//        } catch (Exception ex) {
//            this.logger.error("Failed to save persistent token ", ex);
//        }
//    }
//
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//        super.logout(request, response, authentication);
//        if (authentication != null) {
//            this.tokenRepository.removeUserTokens(authentication.getName());
//        }
//    }
//
//    protected String generateSeriesData() {
//        byte[] newSeries = new byte[this.seriesLength];
//        this.random.nextBytes(newSeries);
//        return new String(Base64.getEncoder().encode(newSeries));
//    }
//
//    protected String generateTokenData() {
//        byte[] newToken = new byte[this.tokenLength];
//        this.random.nextBytes(newToken);
//        return new String(Base64.getEncoder().encode(newToken));
//    }
//
//    private void addCookie(PersistentRememberMeToken token, HttpServletRequest request, HttpServletResponse response) {
//        setCookie(new String[]{token.getSeries(), token.getTokenValue()}, getTokenValiditySeconds(), request,
//                response);
//    }
//
//    public void setSeriesLength(int seriesLength) {
//        this.seriesLength = seriesLength;
//    }
//
//    public void setTokenLength(int tokenLength) {
//        this.tokenLength = tokenLength;
//    }
//
//    @Override
//    public void setTokenValiditySeconds(int tokenValiditySeconds) {
//        Assert.isTrue(tokenValiditySeconds > 0, "tokenValiditySeconds must be positive for this implementation");
//        super.setTokenValiditySeconds(tokenValiditySeconds);
//    }
}
