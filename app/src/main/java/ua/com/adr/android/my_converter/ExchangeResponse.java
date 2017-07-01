package ua.com.adr.android.my_converter;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "exchange")
public class ExchangeResponse {
    @ElementList(inline = true)
    private ArrayList<Exchange> obmenList;

    public ArrayList<Exchange> getObmenList() {
        return obmenList;
    }
}
