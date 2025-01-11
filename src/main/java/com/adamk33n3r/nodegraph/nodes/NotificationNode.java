package com.adamk33n3r.nodegraph.nodes;

import com.adamk33n3r.nodegraph.Node;
import com.adamk33n3r.nodegraph.VarInput;
import com.adamk33n3r.runelite.watchdog.notifications.Notification;
import lombok.*;

@Getter
public class NotificationNode extends Node {
    private final Notification notification;

    private final VarInput<Boolean> enabled = new VarInput<>(this, "Enabled", Boolean.class, true);
    private final VarInput<Boolean> fireWhenFocused = new VarInput<>(this, "Fire When Focused", Boolean.class, true);
    private final VarInput<Number> fireWhenAfk = new VarInput<>(this, "Fire When AFK", Number.class, 0);
    private final VarInput<Number> delayMilliseconds = new VarInput<>(this, "Delay (ms)", Number.class, 0);
    private final VarInput<String> alertName = new VarInput<>(this, "Alert Name", String.class, "");
    private final VarInput<String[]> captureGroups = new VarInput<>(this, "Capture Groups In", String[].class, new String[0]);

    // Could maybe output "if fired" or something

    public NotificationNode(Notification notification) {
        this.notification = notification;

        this.fireWhenFocused.setValue(this.notification.isFireWhenFocused());
        this.fireWhenFocused.onChange(this.notification::setFireWhenFocused);
        this.fireWhenAfk.setValue(this.notification.getFireWhenAFKForSeconds());
        this.fireWhenAfk.onChange((val) -> this.notification.setFireWhenAFKForSeconds(val.intValue()));
        this.delayMilliseconds.setValue(this.notification.getDelayMilliseconds());
        this.delayMilliseconds.onChange((val) -> this.notification.setDelayMilliseconds(val.intValue()));
    }

    @Override
    public void process() {
        this.notification.setFireWhenFocused(this.fireWhenFocused.getValue());
        this.notification.setFireWhenAFKForSeconds(this.fireWhenAfk.getValue().intValue());
        this.notification.setDelayMilliseconds(this.delayMilliseconds.getValue().intValue());
    }

    public void fire() {
        this.notification.fire(this.captureGroups.getValue());
    }
}
