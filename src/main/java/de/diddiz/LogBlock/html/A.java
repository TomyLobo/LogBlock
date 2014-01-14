package de.diddiz.LogBlock.html;

import net.minecraft.server.v1_11_R1.ChatClickable;
import net.minecraft.server.v1_11_R1.ChatModifier;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class A extends Element {
    @XmlAttribute(required = true)
    private String href;

    @Override
    protected void modifyStyle(ChatModifier style) {
        style.setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.OPEN_URL, href));
    }
}
