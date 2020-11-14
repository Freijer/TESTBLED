package freijer.app.testbled;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.*;
import android.widget.*;
import android.view.*;
import java.util.*;
import java.io.*;
import android.util.*;

public class MainActivity extends AppCompatActivity {


    protected ArrayList<String> MainListWord;// при нажатии кнопки собисрется слово
    protected ArrayList<String> thru_list_1;
    protected ArrayAdapter<String> adapter_thru_1;
    protected ArrayAdapter<String> adapter_wrong_1;
    protected ArrayList<String> wrong_list_1;
    protected ArrayAdapter<String> wrong_thru_1;

    final String filename = "savebled15.txt";
    protected FileOutputStream outputStream;
    protected String  myText;

    protected Button addWord;
    protected Button but_k, but_o, but_t, but_h;
    protected TextView textsee;
    protected ListView listText, wrong_listText;
    String keyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addWord = findViewById(R.id.addWord);
        textsee = findViewById(R.id.textsee);
        listText = findViewById(R.id.listText);
        wrong_listText = findViewById(R.id.wrong_listText);
        thru_list_1 = new ArrayList<>();
        wrong_list_1 = new ArrayList<>();

        but_k = findViewById(R.id.but_k);
        but_o = findViewById(R.id.but_o);
        but_t = findViewById(R.id.but_t);
        but_h = findViewById(R.id.but_h);
        MainListWord = new ArrayList<>();

        ReadFromTxt();
        GetItJonny();
    }

    public void Bt1(View v){
        MainListWord.add(but_k.getText().toString());
    }
    public void Bt2(View v){
        MainListWord.add(but_o.getText().toString());
    }
    public void Bt3(View v){
        MainListWord.add(but_t.getText().toString());
    }
    public void Bt4(View v){
        MainListWord.add(but_h.getText().toString());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void AddWordToList(View v){
        String[] ArrayListWord = MainListWord.toArray(new String[0]);
        keyWord = (String.join("", ArrayListWord));
        Log.d("TEST", keyWord);
        if (thru_list_1.contains(keyWord)){
           textsee.setText("НЭВЕРНО!");
           textsee.setBackgroundColor(Color.RED);
           wrong_list_1.add(keyWord);
            DontGetItJonny();
        } else if (!thru_list_1.contains(keyWord)){
            textsee.setText("ВЕРНО!");
            textsee.setBackgroundColor(Color.CYAN);
            thru_list_1.add(keyWord);
            GetItJonny();
        }


        MainListWord.removeAll(MainListWord);
        WriteToTxt();
    } //чтение слова и добавление в ArrayList


    private String array2str(List<String> strings){
        StringBuilder sb = new StringBuilder();
        for (String s : strings){
            sb.append(s+ "\n");
        }
        return sb.toString();
    } //запись в тхт
    public void WriteToTxt(){
        myText = array2str(thru_list_1);
        try {
            outputStream = openFileOutput(filename, MODE_PRIVATE);
            outputStream.write(myText.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //запись в тхт
    public void ReadFromTxt(){
        try {
            InputStream inputStream = openFileInput(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    thru_list_1.add(receiveString);
                    Log.e("login", String.valueOf(thru_list_1));
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("logme", "File is in ass: " + e.toString());
        } catch (IOException e) {
            Log.e("logmetwice", "do not read, bos: " + e.toString());
        }


    } //чтение из тхт

    public void GetItJonny(){

        adapter_thru_1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, thru_list_1);
        listText.setAdapter(adapter_thru_1); //вывод в ListView
    }

    public void DontGetItJonny(){

        adapter_wrong_1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, wrong_list_1);
        wrong_listText.setAdapter(adapter_wrong_1); //вывод в ListView
    }

}