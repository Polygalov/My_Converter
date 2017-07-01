package ua.com.adr.android.my_converter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private final static String URL = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=";
    EditText summ;
    Button result;
    Spinner valuta, valuta_dest;
    String vlt1, vlt2;
    double UAH = 1.0;
    double RUB, EUR, USD;
    double valut1, valut2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView5);
        result = (Button) findViewById(R.id.button);
        result.setOnClickListener(oclBtnRes);
        initRestXML("RUB");
        initRestXML("EUR");
        initRestXML("USD");

    }

    View.OnClickListener oclBtnRes = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            valuta = (Spinner) findViewById(R.id.spinner);
            valuta_dest = (Spinner) findViewById(R.id.spinner2);
            vlt1 = String.valueOf(valuta.getSelectedItem());
            vlt2 = String.valueOf(valuta_dest.getSelectedItem());
            summ = (EditText) findViewById(R.id.editText);

            if (vlt1.equals("RUB")) {
                valut1 = RUB;
            } else if (vlt1.equals("EUR")) {
                valut1 = EUR;
            } else if (vlt1.equals("USD")) {
                valut1 = USD;
            } else if (vlt1.equals("UAH")) {
                valut1 = UAH;
            }

            if (vlt2.equals("RUB")) {
                valut2 = RUB;
            } else if (vlt2.equals("EUR")) {
                valut2 = EUR;
            } else if (vlt2.equals("USD")) {
                valut2 = USD;
            } else if (vlt2.equals("UAH")) {
                valut2 = UAH;
            }
            String result = Double.toString(Double.parseDouble(summ.getText().toString()) * valut1 / valut2);
            mTextView.setText(result);

        }
    };

    private void initRestXML(String vlt) {

        final String vlta = vlt;
        ApiService service = ApiClient.getClient().create(ApiService.class);
        System.out.println(vlt);
        Call<ExchangeResponse> call = service.getExchange(URL + vlt);


        call.enqueue(new Callback<ExchangeResponse>() {
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                ArrayList<Exchange> obmenList = response.body().getObmenList();
                double curs = Double.valueOf(obmenList.get(obmenList.size() - 1).getRate());
                System.out.println(curs + " curs");
                if (vlta.equals("RUB")) {
                    RUB = curs;
                } else if (vlta.equals("EUR")) {
                    EUR = curs;
                } else if (vlta.equals("USD")) {
                    USD = curs;
                }

            }

            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {

                Log.d("TAG", "onFailure: ");
            }
        });

    }
}