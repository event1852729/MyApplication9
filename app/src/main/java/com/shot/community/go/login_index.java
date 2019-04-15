package com.shot.community.go;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.shot.community.go.Creat_comunity.Add_Comunity;
import com.shot.community.go.Creat_comunity.Creat_community;
import com.shot.community.go.Creat_comunity.Creat_comunity_http;
import com.shot.community.go.Creat_comunity.Serach_comunity;
import com.shot.community.go.ForgetPassword.Forget_Password;
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class login_index extends AppCompatActivity {
    ArrayList<String> mb_id;
     AsyncTaskUploadClass asyncTaskUploadClass;
  static   AsyncTaskUploadClass2 asyncTaskUploadClass2;
    Spinner spinner;
    static EditText home_select_edit;
    String line="";
    public ArrayList<String> getCommunity_id() {
        return community_id;
    }

   public static SigninActivity signinActivity3;
    public static SigninActivity signinActivity4;
    ArrayList<String> community_id;
   static AutoCompleteTextView start_code;

    public ArrayList<String> getCommunity_name() {
        return community_name;
    }

    ArrayList<String> community_name;

    public ArrayList<String> getMb_name() {
        return mb_name;
    }

    public ArrayList<String> getMb_manager() {
        return mb_manager;
    }

    public ArrayList<String> getMb_self() {
        return mb_self;
    }

    ArrayList<String> mb_name;
    ArrayList<String> mb_manager;
    ArrayList<String> mb_pw;

    public ArrayList<String> getMb_id() {
        return mb_id;
    }

    ArrayAdapter arrayAdapter;
    ArrayList<String> mb_self;

    SigninActivity signinActivity;
    TextView textView;
    List<String> userLogin_name = new ArrayList<String>();
    private ArrayAdapter<String> listAdapter;
    String getUsetName = "";
    ProgressDialog progressDialog ;
    Spinner sp1;
    String[] user_login_name= {"A棟1號1樓","ha"};
    private static final int MAX_HISTORY_COUNT = 50;                    // 最大的历史记录数
    private static final String SP_NAME = "recent_history";             //
    private static final String SP_KEY_SEARCH = "history_search";       //
    private static final String SP_KEY_CUSTOM = "history_custom";       //
    private static final String SP_SEPARATOR = ":-P";                   // 分隔每条历史记录"/-_0_-\\\\"
    private static final String SP_EMPTY_TAG = "<empty>";
    AutoCompleteTextView editText;
    private ArrayAdapter<String> mSearchAdapter;
    Button selectnumber;
    public static RecyclerView recyclerView;
    public static View view2;
    private DividerItemDecoration mDivider;
    int loginflag = 0;
    String[] mSearchHistoryArray;

    class httpThread extends Thread
    {
        @Override
        public void run() {
            super.run();
//            userLogin_name = signinActivity.getUsername();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        httpThread httpThread = new httpThread();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button button = (Button)findViewById(R.id.login_button) ;
        Button button2 = (Button)findViewById(R.id.forget_passord) ;
        Button button3 = (Button)findViewById(R.id.creat_comunity_serachbutton);
        TextView button4 = (TextView)findViewById(R.id.forget);
        selectnumber = (Button) findViewById(R.id.login_button2);
        editText = (AutoCompleteTextView)findViewById(R.id.passord_editText);
        mSearchHistoryArray = getHistoryArray(SP_KEY_SEARCH);
        TextView clear = (TextView)findViewById(R.id.forget1);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearHistoryInSharedPreferences();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });


        selectnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(login_index.this,R.style.MyAlertDialogStyle);
                view2 = getLayoutInflater().inflate(R.layout.search_home_number, null , false);
                recyclerView = (RecyclerView)view2.findViewById(R.id.search_package_recycle_dio);
                recyclerView.setHasFixedSize(true);
                home_select_edit = (EditText)view2.findViewById(R.id.serach_package_dio);

                if(mSearchHistoryArray[0].equals("<empty>"))
                {
                    String[] s = {"暫無用戶 ! \n請使用啟動碼進行開通!"};
                    Home_selectnumber_recycleAdatpar home_selectnumber_recycleAdatpar = new Home_selectnumber_recycleAdatpar(login_index.this ,s );
                    mDivider = new DividerItemDecoration(login_index.this,DividerItemDecoration.VERTICAL);
                    recyclerView.setAdapter(home_selectnumber_recycleAdatpar);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(login_index.this,LinearLayoutManager.VERTICAL ,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addItemDecoration(mDivider);
                }else
                {
                    Home_selectnumber_recycleAdatpar home_selectnumber_recycleAdatpar = new Home_selectnumber_recycleAdatpar(login_index.this ,mSearchHistoryArray );
                    mDivider = new DividerItemDecoration(login_index.this,DividerItemDecoration.VERTICAL);
                    recyclerView.setAdapter(home_selectnumber_recycleAdatpar);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(login_index.this,LinearLayoutManager.VERTICAL ,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.addItemDecoration(mDivider);
                }

                real_delete.setView(view2);
                real_delete.setPositiveButton("登入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loginflag=1;
                        Log.d("geriobr" , "gagaga");
                        asyncTaskUploadClass2 = new AsyncTaskUploadClass2();
                        asyncTaskUploadClass2.execute(" http://140.136.155.79/member/name_content.php" , home_select_edit.getText().toString());
                        dialog.dismiss();
                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                real_delete.show();
            }
        });



        Drawable dr4 = getResources().getDrawable(R.drawable.ic_home_login_icon);
        dr4.setBounds(0, 0, 80, 80); //Left,Top,Right,Bottom
        selectnumber.setCompoundDrawables(dr4 , null , null , null);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(login_index.this,R.style.MyAlertDialogStyle);
                view2 = getLayoutInflater().inflate(R.layout.home_start_code_dio, null , false);
                start_code = (AutoCompleteTextView)view2.findViewById(R.id.passord_editText);
                real_delete.setView(view2);
                real_delete.setPositiveButton("啟動", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        asyncTaskUploadClass = new AsyncTaskUploadClass();
                        asyncTaskUploadClass.execute("http://140.136.155.79/member/content.php" , start_code.getText().toString());
                        loginflag=0;
                        dialog.dismiss();
                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                real_delete.show();




            }
        });

        Drawable dr = getResources().getDrawable(R.drawable.ic_login_icon);
        dr.setBounds(0, 0, 80, 80); //Left,Top,Right,Bottom
        button.setCompoundDrawables(dr , null , null , null);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(login_index.this,"創建社區",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(login_index.this , Creat_community.class);
                startActivity(intent2);
                Creat_comunity_http creat_comunity_http1 = new Creat_comunity_http(9);
                creat_comunity_http1.execute("http://140.136.155.79/community/content.php" );
            }
        });

        Drawable dr3 = getResources().getDrawable(R.drawable.ic_login_icon3);
        dr3.setBounds(0, 0, 80, 80); //Left,Top,Right,Bottom
        button2.setCompoundDrawables(dr3 , null , null , null);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(login_index.this , Add_Comunity.class);
                startActivity(intent3);
            }
        });

        Drawable dr2 = getResources().getDrawable(R.drawable.ic_login_icon2);
        dr2.setBounds(0, 0, 80, 80); //Left,Top,Right,Bottom
        button3.setCompoundDrawables(dr2 , null , null , null);



        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(login_index.this,Forget_Password.class);
                startActivity(intent4);
            }
        });


    }



     class AsyncTaskUploadClass2 extends AsyncTask<String,String,String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param strings The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... strings) {
            try{
                int RC;
                Log.d("geriobr" , "gagaga1");
                URL url = new URL(strings[0].toString());
                String namecode = strings[1].toString();
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String PostData = URLEncoder.encode("mb_name","UTF-8")+"="+URLEncoder.encode(namecode,"UTF-8");
                bufferedWriter.write(PostData);

                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
                StringBuilder sb1 = new StringBuilder("");


                RC = httpURLConnection.getResponseCode();
                if(RC==200)
                {
                    mb_pw = new ArrayList<>();
                    mb_id = new ArrayList<>();
                    community_id= new ArrayList<>();
                    community_name= new ArrayList<>();
                    mb_name= new ArrayList<>();
                    mb_manager= new ArrayList<>();
                    mb_self= new ArrayList<>();

                    while ((line = bufferedReader.readLine()) != null) {
                        sb1.append(line);
                    }
                    String results = sb1.toString();
                    Log.d("geriobr" , results);
                    JSONArray jArray = new JSONArray(results);

                    for (int i = 0; i <jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        mb_id.add(Integer.toString(json_data.getInt("mb_id")));
                        community_id.add(Integer.toString(json_data.getInt("community_id")));
                        Log.d("geriobr" , community_id.get(0).toString());
                        mb_pw.add(json_data.getString("mb_pw"));
                        Log.d("geriobr" , mb_pw.get(0).toString());
                        mb_manager.add(Integer.toString(json_data.getInt("mb_manager")));
                        mb_self.add(json_data.getString("mb_self"));
                        community_name.add(json_data.getString("community_name"));


                    }

                }

                bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
            }catch (Exception e){
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected void onPostExecute(String string1) {

            int LoginFlag=0;
            int numberId=0;
            if(mb_id.size()>0)
            {
                Log.d("geriobr" , "2");
                asyncTaskUploadClass = new AsyncTaskUploadClass();
                asyncTaskUploadClass.execute("http://140.136.155.79/member/content.php" , mb_pw.get(0).toString());
            }else {
                asyncTaskUploadClass = new AsyncTaskUploadClass();
                asyncTaskUploadClass.execute("http://140.136.155.79/member/content.php" ,"");
            }



        }

    }



    class AsyncTaskUploadClass extends AsyncTask<String,String,String> {

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param strings The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected String doInBackground(String... strings) {
            try{
                int RC;

                URL url = new URL(strings[0].toString());
                String startcode = strings[1].toString();
                Log.d("geriobr" , startcode);
                Log.d("geriobr" , strings[0].toString());
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String PostData = URLEncoder.encode("mb_pw","UTF-8")+"="+URLEncoder.encode(startcode,"UTF-8");
                bufferedWriter.write(PostData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
                StringBuilder sb1 = new StringBuilder("");


                RC = httpURLConnection.getResponseCode();
                if(RC==200)
                {
                    mb_id = new ArrayList<>();
                    community_id= new ArrayList<>();
                    community_name= new ArrayList<>();
                    mb_name= new ArrayList<>();
                    mb_manager= new ArrayList<>();
                    mb_self= new ArrayList<>();

                    while ((line = bufferedReader.readLine()) != null) {
                        sb1.append(line);
                    }
                    String results = sb1.toString();
                    JSONArray jArray = new JSONArray(results);
                    Log.d("geriobr" , results);
                    for (int i = 0; i <jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);
                        mb_id.add(Integer.toString(json_data.getInt("mb_id")));
                        community_id.add(Integer.toString(json_data.getInt("community_id")));
                        mb_name.add(json_data.getString("mb_name"));
                        mb_manager.add(Integer.toString(json_data.getInt("mb_manager")));
                        mb_self.add(json_data.getString("mb_self"));
                        community_name.add(json_data.getString("community_name"));


                    }

                }

                bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
            }catch (Exception e){
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
                    if(loginflag==1) {
                        String s = "登入中";
                        String title = "請稍後";
                        SpannableString ss1 = new SpannableString(title);
                        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, ss1.length(), 0);
                        SpannableString ss2 = new SpannableString(s);
                        ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, ss2.length(), 0);
                        progressDialog = new ProgressDialog(login_index.this);
                        progressDialog.setProgressDrawable(getDrawable(R.drawable.button_dilog));
                        progressDialog = ProgressDialog.show(login_index.this, ss1, ss2, false, false);
                    }else{
                        String s = "開通中";
                        String title = "請稍後";
                        SpannableString ss1 = new SpannableString(title);
                        ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, ss1.length(), 0);
                        SpannableString ss2 = new SpannableString(s);
                        ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, ss2.length(), 0);
                        progressDialog = new ProgressDialog(login_index.this);
                        progressDialog.setProgressDrawable(getDrawable(R.drawable.button_dilog));
                        progressDialog = ProgressDialog.show(login_index.this, ss1, ss2, false, false);
                    }

        }

        @Override
        protected void onPostExecute(String string1) {
            int LoginFlag=0;
            int numberId=0;
            if(mb_id.size()>0)
            {
                if(loginflag==1) {
                    Log.d("geriobr" , "gagaga3");
                    UserData.manger = mb_manager.get(0);
                    UserData.id = mb_id.get(0);
                    UserData.name = mb_name.get(0);
                    UserData.comunity_id = community_id.get(0);
                    UserData.comunity_name = community_name.get(0);
//                    signinActivity3 = new SigninActivity(33);
//                    signinActivity4 = new SigninActivity(34);
                    Log.d("geriobr" , "登入成功 !1");
//                    signinActivity3.execute("http://140.136.155.79/message/sevent.php", UserData.comunity_id, UserData.id, UserData.manger);
//                    signinActivity4.execute("http://140.136.155.79/image/image_content.php", UserData.comunity_id);
                    Intent intent = new Intent();
                    intent.setClass(login_index.this, MainActivity.class);
                    intent.putExtra("number_id", UserData.id + "");//傳用戶id
                    progressDialog.dismiss();
                    startActivity(intent);
                    LoginFlag = 2;
                }
                LoginFlag = 1;
            }else if(loginflag==1)
            {
                Toast.makeText(login_index.this,   "登入失敗 ! ", Toast.LENGTH_SHORT).show();
            }


            if(LoginFlag==2)
            {
                Log.d("geriobr" , "登入成功 !");
                Toast.makeText(login_index.this,   "登入成功 ! 歡迎" + UserData.name, Toast.LENGTH_SHORT).show();
            }
            if(LoginFlag==0)
            {
                if(loginflag==1)
                {

                }else
                   Toast.makeText(login_index.this,   "開通失敗 ! 請確認啟動碼是否正確", Toast.LENGTH_SHORT).show();
            }else if(LoginFlag==1)
            {
                if(loginflag==1) {

                    Creat_comunity_http creat_comunity_http = new Creat_comunity_http(7);
                    creat_comunity_http.execute("http://140.136.155.79/member/all_content.php", UserData.comunity_id);
                Toast.makeText(login_index.this,     UserData.name + "  登入成功!", Toast.LENGTH_SHORT).show();
                }else if(loginflag==0) {
                    saveHistory(start_code, SP_KEY_SEARCH, "Search");
                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(login_index.this,R.style.MyAlertDialogStyle);
                    real_delete.setTitle("開通成功 ! 您已可使用用戶登入功能");
                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    });
                    real_delete.show();

                }
            }


        progressDialog.dismiss();


        }

    }



    private void saveHistory(AutoCompleteTextView view, String key, String tip) {
        String text = mb_name.get(0).trim();
        if (TextUtils.isEmpty(text)) {      // null or ""
            Toast.makeText(this, "Please type something again.", Toast.LENGTH_SHORT).show();
            return;
        }

        String old_text = getHistoryFromSharedPreferences(key);    // 获取SP中保存的历史记录
        StringBuilder sb;
        if (SP_EMPTY_TAG.equals(old_text)) {
            sb = new StringBuilder();
        } else {
            sb = new StringBuilder(old_text);
        }
        sb.append(text + SP_SEPARATOR);      // 使用逗号来分隔每条历史记录

        // 判断搜索内容是否已存在于历史文件中，已存在则不再添加
        if (!old_text.contains(text + SP_SEPARATOR)) {
            saveHistoryToSharedPreferences(key, sb.toString());  // 实时保存历史记录
            if ("Search".equals(tip)) {
//                mSearchAdapter.add(text);        // 实时更新下拉提示框中的历史记录
            }
        } else {

        }
    }



    private int getCurrentFocusedViewId() {
        return this.getWindow().getDecorView().findFocus().getId();
    }

    private String getHistoryFromSharedPreferences(String key) {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        return sp.getString(key, SP_EMPTY_TAG);
    }

    private void saveHistoryToSharedPreferences(String key, String history) {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, history);
        editor.apply();
    }

    private String[] getHistoryArray(String key) {
        String[] array = getHistoryFromSharedPreferences(key).split(SP_SEPARATOR);
        if (array.length > MAX_HISTORY_COUNT) {         // 最多只提示最近的50条历史记录
            String[] newArray = new String[MAX_HISTORY_COUNT];
            System.arraycopy(array, 0, newArray, 0, MAX_HISTORY_COUNT); // 实现数组间的内容复制
        }
        return array;
    }
    private void clearHistoryInSharedPreferences() {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    private long firstPressedTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
            super.onBackPressed();
        } else {
            Toast.makeText(login_index.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstPressedTime = System.currentTimeMillis();
        }
    }




}
