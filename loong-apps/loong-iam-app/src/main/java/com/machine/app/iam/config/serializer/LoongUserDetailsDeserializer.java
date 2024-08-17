package com.machine.app.iam.config.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.machine.app.iam.config.LoongUserDetails;
import com.machine.client.iam.user.dto.LoongUserAuthDetailDto;

import java.io.IOException;

public class LoongUserDetailsDeserializer extends JsonDeserializer<LoongUserDetails> {
    @Override
    public LoongUserDetails deserialize(JsonParser jsonParser,
                                        DeserializationContext ctxt) throws IOException, JacksonException {
        // 解析JSON节点
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        String userId = node.get("userId").asText();
        String username = node.get("username").asText();
        String password = node.get("password").asText();
        boolean accountNonExpired = node.get("accountNonExpired").asBoolean();
        boolean credentialsNonExpired = node.get("credentialsNonExpired").asBoolean();
        boolean accountNonLocked = node.get("accountNonLocked").asBoolean();
        boolean enabled = node.get("enabled").asBoolean();

        LoongUserAuthDetailDto userDetailDto = new LoongUserAuthDetailDto();

        JsonNode authNodeList = node.get("authorities");
        for (JsonNode authNode : authNodeList.get(1)) {
            String authority = authNode.get("authority").asText();
            userDetailDto.addPermissionCode(authority);
        }

        userDetailDto.setUserId(userId);
        userDetailDto.setUserName(username);
        userDetailDto.setPassword(password);
        userDetailDto.setEnabled(enabled);
        return new LoongUserDetails(userDetailDto);
    }
}
