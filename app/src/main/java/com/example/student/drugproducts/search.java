package com.example.student.drugproducts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class search extends ActionBarActivity {

    AutoCompleteTextView actv;
    MyDBHandler DbHelper;
    Context context;
    String dbName = "drugs.db";
    Drug drug;
    TextView categorization;
    TextView classNames;
    TextView drug_id;
    TextView pediatric;
    TextView ais;
    TextView comp_names;
    TextView address;
    String str; //hold address
    String str2; //hold the company name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;

        List list;

        DbHelper = new MyDBHandler(context,dbName);
        DbHelper.openDataBase();
        drug = new Drug();

        list = DbHelper.Get_Name();//call the Get_name function

        actv = (AutoCompleteTextView) findViewById(R.id.auto_name);

        ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, list);

        actv.setThreshold(1);

        actv.setAdapter(adaptor);

        actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drug = DbHelper.Get_Details(position + 1);
                //set text to the text view
                categorization = (TextView) findViewById(R.id.cateName) ;
                classNames = (TextView) findViewById(R.id.className) ;
                drug_id = (TextView) findViewById(R.id.drugIdName) ;
                pediatric = (TextView) findViewById(R.id.pediatricName) ;
                ais = (TextView) findViewById(R.id.aisName) ;
                comp_names = (TextView)findViewById(R.id.comp_name);
                address = (TextView) findViewById(R.id.addressName) ;

                if(drug.getCategorization_().length()<1){
                    categorization.setText("None");
                }else {
                    categorization.setText(drug.getCategorization_());
                }
                classNames.setText(drug.getClass_());
                drug_id.setText(drug.getDrug_id_());
                pediatric.setText(drug.getPediatric_());
                ais.setText(drug.getAis_());
                comp_names.setText(drug.getComp_name_());
                str2 = drug.getComp_name_();
                str = drug.getSuite_()+ " " +drug.getStreet_() + ", "+ drug.getCity_() + ", " + drug.getProvince_() + ", " + drug.getCountry_() + " " + drug.getPostal_();
                address.setText(str);
            }
        });
        registerForContextMenu(actv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Go to the location");
        menu.add(0, v.getId(), 0, "Search drug detail online");
    }

    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Go to the location") {
            Toast.makeText(getApplicationContext(), "Open the map", Toast.LENGTH_LONG).show();
            //pass two strings and open the map
            Intent myIntent = new Intent(this,map.class);
            myIntent.putExtra("address",str);
            myIntent.putExtra("name",str2);
            startActivity(myIntent);

        }else if(item.getTitle() == "Search drug detail online"){
            Toast.makeText(getApplicationContext(), "Open the website", Toast.LENGTH_LONG).show();
            //open the web view
            setContentView(R.layout.webview);
            WebView myWebView = (WebView) findViewById(R.id.webView);
            myWebView.loadUrl("http://webprod5.hc-sc.gc.ca/dpd-bdpp/start-debuter.do?lang=eng");

        }
        else{
            return false;
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about ) {
            context = null;
            Intent myIntent = new Intent(this, about.class);
            startActivity(myIntent);
            return true;
        }

        if (id == R.id.action_help) {
            context = null;
            Intent myIntent = new Intent(this, help.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume(){
        super.onResume();
    }

}