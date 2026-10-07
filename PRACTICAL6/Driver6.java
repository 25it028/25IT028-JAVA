package PRACTICAL6;
interface Switchable {
    void on();
    void off();

    default void toggle() {
        off();
        on();
    }
}
class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

interface DevicePolicy {
    boolean maySwitchOn(Switchable device, int hour);
}

public class Driver6 {
    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        for (Switchable device : devices) {
            device.toggle();
        }

        int hour = 20;
        DevicePolicy anonymousPolicy = new DevicePolicy() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        System.out.println("Anonymous Class:");

        for (Switchable device : devices) {
            System.out.println(
                anonymousPolicy.maySwitchOn(device, hour)
                ? "Device can switch ON"
                : "Device cannot switch ON"
            );
        }

        DevicePolicy lambdaPolicy =
            (device, time) -> time >= 6 && time <= 22;

        System.out.println("Lambda:");

        for (Switchable device : devices) {
            System.out.println(
                lambdaPolicy.maySwitchOn(device, hour)
                ? "Device can switch ON"
                : "Device cannot switch ON"
            );
        }
    }
}