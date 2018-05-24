package com.example.prabh.jsoup;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private EditText editText;
    String name;
    String link;
    int found=0;
    StringBuilder stringBuilder= new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.b1);
        textView=(TextView)findViewById(R.id.t2);
        editText=(EditText)findViewById(R.id.e1);

        button.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {

               new Thread(new Runnable() {
                   @SuppressLint("WrongConstant")
                   @Override
                   public void run() {
                       try {
                           Document document=Jsoup.connect("http://www.gogoanime.io").get();
                           Elements latest=document.getElementsByTag("a");
                           name=editText.getText().toString().toLowerCase();
                           //found=latest.size();
                           /*for(Element items : latest)
                           {
                               stringBuilder.append(items.attr("title"));
                               //Toast.makeText(getApplicationContext(), "Hi", Toast.LENGTH_LONG).show();
                               //if(items.attr("title").toLowerCase().startsWith(name)||items.attr("title").toLowerCase().endsWith(name))
                               //String ref=items.attr("title");
                               /*if(ref.contains(name))
                               {
                                  // Toast.makeText(getApplicationContext(), "Latest episode of the anime is out,To watch click on the link below", Toast.LENGTH_SHORT).show();
                                   link=items.attr("href");
                                   editText.setVisibility(0);
                                   found=1;
                                   break;
                               }*/
                            int i;
                           //textView.setText(stringBuilder);
                           for (i=0;i<latest.size();i++)
                            {
                                Element item=latest.get(i);

                                //stringBuilder.append(item.attr("title").toLowerCase());

                                if (item.attr("title").toLowerCase().contains(name))
                                {
                                    found=1;
                                    stringBuilder.append(item.attr("title")).append(":").append("http://www2.gogoanime.se").append(item.attr("href")).append("\n");

                                }
                            }
                           textView.setClickable(true);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               }).start();
               textView.setText(stringBuilder);
               if(found==1) {
                   Toast.makeText(getApplicationContext(), "Latest episode of the anime is out,To watch click on the link", Toast.LENGTH_SHORT).show();
                   stringBuilder.delete(0, stringBuilder.length());
               }
               else
                   Toast.makeText(getApplicationContext(), "Not yet released,Search for other anime", Toast.LENGTH_SHORT).show();
               //Toast.makeText(getApplicationContext(),"Titles: , "+stringBuilder, Toast.LENGTH_LONG).show();
            }
        });

    }
}
