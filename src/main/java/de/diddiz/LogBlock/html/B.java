package de.diddiz.LogBlock.html;

import net.minecraft.server.v1_11_R1.ChatModifier;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class B extends Element {
    @Override
    protected void modifyStyle(ChatModifier style) {
        style.setBold(true);
    }
}
