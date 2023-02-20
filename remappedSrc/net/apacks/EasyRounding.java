package net.apacks;

public class EasyRounding {
    public static Double round(double value){
        return Double.valueOf(String.valueOf(value).split("\\.",2)[0]);
    }
}
