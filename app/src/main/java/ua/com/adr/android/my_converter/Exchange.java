package ua.com.adr.android.my_converter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;


@Root(name = "currency")
public class Exchange {
    @Element(name = "r030")
    String r030;

    @Element(name = "txt")
    String txt;

    @Element(name = "rate")
    String rate;

    @Element(name = "cc")
    String cc;

    @Element(name = "exchangedate")
    String exchangedate;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
