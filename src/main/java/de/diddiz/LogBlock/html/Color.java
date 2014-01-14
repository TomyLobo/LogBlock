package de.diddiz.LogBlock.html;

import net.minecraft.server.v1_11_R1.ChatModifier;
import net.minecraft.server.v1_11_R1.EnumChatFormat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.Map;

@XmlRootElement
public class Color extends Element {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String id;

    private static final Map<Character, EnumChatFormat> EnumChatFormat_characterToEnumMap;
    static {
        try {
            Map _EnumChatFormat_characterToEnumMap = null;
            for(Field field : EnumChatFormat.class.getDeclaredFields()) {
                if(field.getType().equals(Map.class)) {
                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    _EnumChatFormat_characterToEnumMap = (Map)field.get(null);
                    Object firstKey = _EnumChatFormat_characterToEnumMap.keySet().iterator().next();
                    if(firstKey instanceof Character)
                        break;
                    else
                        field.setAccessible(isAccessible);
                }
            }
            if(_EnumChatFormat_characterToEnumMap == null)
                throw new Exception("Could not find characterToEnumMap field in EnumChatFormat");
            EnumChatFormat_characterToEnumMap = _EnumChatFormat_characterToEnumMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void modifyStyle(ChatModifier style) {
        if (name != null) {
            style.setColor(EnumChatFormat.b(name.toUpperCase())); // v1_7_R1
        }

        if (id != null && !id.isEmpty()) {
            @SuppressWarnings("unchecked")
            final Map<Character, EnumChatFormat> idToChatFormat = EnumChatFormat_characterToEnumMap; // v1_7_R1
            style.setColor(idToChatFormat.get(id.charAt(0)));
        }
    }
}
