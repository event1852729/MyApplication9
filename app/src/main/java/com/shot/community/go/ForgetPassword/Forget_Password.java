package com.shot.community.go.ForgetPassword;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shot.community.go.Creat_comunity.Creat_comunity_http;
import com.shot.community.go.Creat_comunity.Forget_check;
import com.shot.community.go.Creat_comunity.Serach_comunity;
import com.shot.community.go.R;

/**
 * Created by user on 2017/12/2.
 */

public class Forget_Password extends AppCompatActivity {

    Button button , button2;
    EditText editText1 , editText2 ,editText3 ,editText4 , editText5;
    public static int flagfff=0;
    static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgetpassword);
        button = (Button)findViewById(R.id.addadd_creat_comunity_serachbutton);
        button2 = (Button)findViewById(R.id.addcreat_com_button) ;
        editText1 = (EditText)findViewById(R.id.addet_name);
        editText2 = (EditText)findViewById(R.id.addet_cellphone);
        editText3 = (EditText)findViewById(R.id.addet_address);
        editText4 = (EditText)findViewById(R.id.addet_email);
        editText5 = (EditText)findViewById(R.id.addet_com);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forget_Password.this , Serach_comunity.class);
                Serach_comunity.flag_to_search_com = 1;
                startActivity(intent);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check=0;
                        for(int i = 0 ; i<Forget_check.N.size() ; i++)
                        {
                            if(editText1.getText().toString().equals(Forget_check.N.get(i))&&
                                    editText2.getText().toString().equals(Forget_check.P.get(i))&&
                                    editText3.getText().toString().equals(Forget_check.D.get(i))&&
                                    editText4.getText().toString().equals(Forget_check.M.get(i)))
                            {

                                check=1;
                            }

                        }



                        final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Forget_Password.this,R.style.MyAlertDialogStyle);
                        real_delete.setTitle("確定加入社區?");
                final int finalCheck = check;
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(editText1.getText().length()>0 && editText2.getText().length()>0 &&editText3.getText().length()>0 &&
                                        editText4.getText().length()>0 &&editText5.getText().length()>0 ) {

                                    if (finalCheck ==0){

                                        final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Forget_Password.this,R.style.MyAlertDialogStyle);
                                        real_delete.setTitle("資料有誤 ! ");
                                        real_delete.setMessage("請確認您資料填寫是否正確 , 否則將無法進行申請啟動碼動作 !");
                                        real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                editText1.setText(null);
                                                editText2.setText(null);
                                                editText3.setText(null);
                                                editText4.setText(null);
                                                editText5.setText(null);
                                                dialog.dismiss();
                                            }
                                        });
                                        real_delete.show();

                                    }else if(finalCheck ==1) {
                                        Creat_comunity_http creat_comunity_http1 = new Creat_comunity_http(6);
                                        creat_comunity_http1.execute("http://140.136.155.79/member/forget.php" ,editText5.getText().toString() , editText1.getText().toString() ,
                                                editText2.getText().toString() ,editText3.getText().toString() ,editText4.getText().toString(),randomString(20));


                                        final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Forget_Password.this,R.style.MyAlertDialogStyle);
                                        real_delete.setTitle("申請成功 ! ");
                                        real_delete.setMessage("請至信箱確認啟動碼 !");
                                        real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                editText1.setText(null);
                                                editText2.setText(null);
                                                editText3.setText(null);
                                                editText4.setText(null);
                                                editText5.setText(null);
                                                dialog.dismiss();
                                            }
                                        });
                                        real_delete.show();




                                        finish();
                                    }


                                }else {
                                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Forget_Password.this,R.style.MyAlertDialogStyle);
                                    real_delete.setTitle("送出失敗 ! 請確認資料是否填寫完整 !");
                                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    real_delete.show();
                                    editText1.setText(null);
                                    editText2.setText(null);
                                    editText3.setText(null);
                                    editText4.setText(null);
                                    editText5.setText(null);
                                }

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
        if (flagfff==2)
        {
            Intent intent = getIntent();
            String s = intent.getStringExtra("com_name");
            id = intent.getStringExtra("com_id");
            editText5.setText(s);
        }




    }

    @NonNull
    public static String randomString(int len) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int idx = (int)(Math.random() * str.length());
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }

}
