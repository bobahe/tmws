package ru.levin.tmws.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.levin.tmws.server.api.service.ISessionService;
import ru.levin.tmws.server.api.service.IUserService;
import ru.levin.tmws.server.entity.RoleType;
import ru.levin.tmws.server.entity.Session;
import ru.levin.tmws.server.entity.User;
import ru.levin.tmws.server.exception.HashException;
import ru.levin.tmws.server.exception.NullSessionException;
import ru.levin.tmws.server.exception.SessionValidationException;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServiceUtil {

    @NotNull
    public static String md5(@NotNull final String md5) {
        try {
            @NotNull final MessageDigest md = MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(md5.getBytes());
            @NotNull final String hash = DatatypeConverter.printHexBinary(array);
            return hash;
        } catch (NoSuchAlgorithmException nsae) {
            throw new HashException();
        }
    }

    @Nullable
    public static String sign(@Nullable final Object value, @Nullable final String salt, @Nullable final Integer cycle) {
        try {
            @NotNull final ObjectMapper objectMapper = new ObjectMapper();
            @NotNull final String json = objectMapper.writeValueAsString(value);
            return sign(json, salt, cycle);
        } catch (final JsonProcessingException e) {
            return null;
        }
    }

    @Nullable
    private static String sign(@Nullable final String value, @Nullable final String salt, @Nullable final Integer cycle) {
        if (value == null || salt == null || cycle == null) return null;
        @Nullable String result = value;
        for (int i = 0; i < cycle; i++) {
            result = md5(salt + result + salt);
        }
        return result;
    }

    @NotNull
    public static Session checkSession(@Nullable final Session session, @NotNull final ISessionService service) {
        if (session == null) throw new NullSessionException();
        if (session.getSignature() == null || session.getSignature().isEmpty()) throw new SessionValidationException();
        if (session.getUserId() == null || session.getUserId().isEmpty()) throw new SessionValidationException();
        @Nullable final Session serverSession = service.findById(session.getId());
        if (serverSession == null) throw new SessionValidationException();
        if (serverSession.getSignature() == null || serverSession.getSignature().isEmpty()) {
            throw new SessionValidationException();
        }
        if (!serverSession.getSignature().equals(session.getSignature())) throw new SessionValidationException();
        return serverSession;
    }

    public static boolean isAdmin(@Nullable final String userId, @NotNull final IUserService service) {
        if (userId == null) return false;
        @Nullable final User user = service.findById(userId);
        return user != null && user.getRoleType() == RoleType.ADMIN;
    }

}
