package com.shot.community.go.Photo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.shot.community.go.R;
import com.shot.community.go.Template.Template;

import java.io.File;
import java.util.ArrayList;
import android.Manifest;

/**
 * Created by user on 2017/11/16.
 */

public class photo_add extends AppCompatActivity{
    private static String[] CHOOSE_FILE = {"點選相片"};
    private Button mAdd, mUpload;
    private ImageView mImage, mImage2, mImage3, mImage4, mImage5, mImage6, mImage7, mImage8, mImage9, mController;
    private VideoView mVideo;
    private TextView mInfo, mResponse;
    private ProgressBar mProgress;
    private Uri mOutputUri;
    private ArrayList<File> mFile = new ArrayList<File>();
    private RequestQueue mRequest;
    private MultiPartRequest mMultiPartRequest;
    private MediaPlayer mMediaPlayer;
    private boolean mIsLoad = false;
    private final String PERMISSION_WRITE_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_upload);
        Toolbar toolbar = (Toolbar)findViewById(R.id.packge_look_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent2 = getIntent();
        String Name111 = intent2.getStringExtra("name");


        toolbar.setTitle(Name111);


        VolleySingleton volleySingleton = new VolleySingleton(photo_add.this);
        mRequest = volleySingleton.getInstance().getRequestQueue();
        mAdd = (Button) findViewById(R.id.add);
        mUpload = (Button) findViewById(R.id.upload);
        mImage = (ImageView) findViewById(R.id.image);
        mImage2 = (ImageView) findViewById(R.id.image2);
        mImage3 = (ImageView) findViewById(R.id.image3);
        mImage4 = (ImageView) findViewById(R.id.image4);
        mImage5 = (ImageView) findViewById(R.id.image5);
        mImage6 = (ImageView) findViewById(R.id.image6);
        mImage7 = (ImageView) findViewById(R.id.image7);
        mImage8 = (ImageView) findViewById(R.id.image8);
        mImage9 = (ImageView) findViewById(R.id.image9);


        mController = (ImageView) findViewById(R.id.controller);
        mVideo = (VideoView) findViewById(R.id.video);
        mProgress = (ProgressBar) findViewById(R.id.progress);

        //Set video view untuk looping video
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        mInfo = (TextView) findViewById(R.id.file_info);
        mResponse = (TextView) findViewById(R.id.response);
        resetView();

        //Set add button listener
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((TextView) view).getText().equals("Delete")) {
                    resetView();
                    if (mIsLoad) {
                        mRequest.cancelAll("MultiRequest");
                        mRequest.stop();
                        mIsLoad = false;
                    }

                } else {



                    if (ContextCompat.checkSelfPermission(photo_add.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(photo_add.this,
                                Manifest.permission.CAMERA)) {


                        } else {
                            ActivityCompat.requestPermissions(photo_add.this,
                                    new String[]{Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE},
                                    10);
                        }
                    }else
                    {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/* video/*");
                        startActivityForResult(intent, Template.Code.FILE_MANAGER_CODE);
                    }



                }

            }
        });

        //Set upload button listener
        mUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
                mUpload.setVisibility(Button.INVISIBLE);
                mProgress.setVisibility(ProgressBar.VISIBLE);
                mIsLoad = true;

                finish();
            }
        });
    }

    //Respon dari add button ketika diklik, untuk memunculkan dialog
    void showDialog() {
        new MaterialDialog.Builder(photo_add.this).title("選擇")
                .items(CHOOSE_FILE)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
//                        if (i == 0) {
//                            //Mengambil foto dengan camera
//                            int permissionCheck = ContextCompat.checkSelfPermission(photo_add.this,Manifest.permission.CAMERA);
//                            if (permissionCheck != PackageManager.PERMISSION_GRANTED){
//                                ActivityCompat.requestPermissions(photo_add.this,new String[]{Manifest.permission.CAMERA},1);
//                            }
//                            else {
//
//                                Toast.makeText(photo_add.this, "已經拿到權限囉", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//                                mOutputUri = FileManager.getOutputMediaFileUri(Template.Code.CAMERA_IMAGE_CODE);
//                                intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputUri);
//
//
//                                startActivityForResult(intent, Template.Code.CAMERA_IMAGE_CODE);
//                            }
//                        } else if (i == 1) {
//                            //Mengambil video dengan camera
//                            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//
//                            mOutputUri = FileManager.getOutputMediaFileUri(Template.Code.CAMERA_VIDEO_CODE);
//
//
//                            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputUri);
//                            startActivityForResult(intent, Template.Code.CAMERA_VIDEO_CODE);
//                        }
// Mendapatkan file dari storage
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/* video/*");
                            startActivityForResult(intent, Template.Code.FILE_MANAGER_CODE);
                    }
                }).show();
    }

    void uploadFile() {
        mRequest.start();
        Intent intent = getIntent();
        String abId = intent.getStringExtra("Ab_id");
        mMultiPartRequest = new MultiPartRequest(new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error) {
                mUpload.setVisibility(Button.VISIBLE);
                mProgress.setVisibility(ProgressBar.GONE);
                mIsLoad = false;
                setResponse(null, error);
            }
        }, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                mUpload.setVisibility(Button.VISIBLE);
                mProgress.setVisibility(ProgressBar.GONE);
                mIsLoad = false;
                setResponse(response, null);

            }
        }, mFile, mFile.size() , abId);
        //Set tag, diperlukan ketika akan menggagalkan request/cancenl request
        mMultiPartRequest.setTag("MultiRequest");
        //Set retry policy, untuk mengatur socket time out, retries. Bisa disetting lewat template
        mMultiPartRequest.setRetryPolicy(new DefaultRetryPolicy(Template.VolleyRetryPolicy.SOCKET_TIMEOUT,
                Template.VolleyRetryPolicy.RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        //Menambahkan ke request queue untuk diproses
        mRequest.add(mMultiPartRequest);
    }

    void setFile(int type, Uri uri) {
        mFile.add(new File(FileManager.getPath(getApplicationContext(), type, uri)));
    }

    void setView(int type, Uri uri) {
        mUpload.setVisibility(Button.VISIBLE);
//        if (mFile.size() == 3) {y selec
//            Toast.makeText(MainActivity.this, "3 photos alreadted", Toast.LENGTH_SHORT).show();
//            //mAdd.setText("Delete");
//            return ;
//        }
        //mInfo.setVisibility(TextView.VISIBLE);
        //mInfo.setText("File info\n" + "Name : " + mFile.getName() + "\nSize : " +
        //      FileManager.getSize(mFile.length(), true));
        if (type == Template.Code.CAMERA_IMAGE_CODE) {
            if (mFile.size() == 1) {
                mImage.setVisibility(ImageView.VISIBLE);
                mImage.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 2) {
                mImage2.setVisibility(ImageView.VISIBLE);
                mImage2.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 3) {
                mImage3.setVisibility(ImageView.VISIBLE);
                mImage3.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 4) {
                mImage4.setVisibility(ImageView.VISIBLE);
                mImage4.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 5) {
                mImage5.setVisibility(ImageView.VISIBLE);
                mImage5.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 6) {
                mImage6.setVisibility(ImageView.VISIBLE);
                mImage6.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 7) {
                mImage7.setVisibility(ImageView.VISIBLE);
                mImage7.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 8) {
                mImage8.setVisibility(ImageView.VISIBLE);
                mImage8.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            } else if (mFile.size() == 9) {
                mImage9.setVisibility(ImageView.VISIBLE);
                mImage9.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
            }

        } else if (type == Template.Code.CAMERA_VIDEO_CODE) {
            mVideo.setVisibility(VideoView.VISIBLE);
            mVideo.setVideoPath(FileManager.getPath(getApplicationContext(), type, uri));
            mController.setVisibility(ImageView.VISIBLE);
            mController.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mVideo.isPlaying()) {
                        mController.setImageResource(R.drawable.ic_play);
                        mVideo.pause();
                    } else {

                        mController.setImageResource(R.drawable.ic_pause);
                        mVideo.start();
                    }
                }
            });
            mVideo.start();
        } else {

            File file = new File(FileManager.getPath(getApplicationContext(), type, uri));
            int fileType = FileManager.fileType(file);
            if (fileType == Template.Code.CAMERA_IMAGE_CODE) {
                if (mFile.size() == 1) {
                    mImage.setVisibility(ImageView.VISIBLE);
                    mImage.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 2) {
                    mImage2.setVisibility(ImageView.VISIBLE);
                    mImage2.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 3) {
                    mImage3.setVisibility(ImageView.VISIBLE);
                    mImage3.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 4) {
                    mImage4.setVisibility(ImageView.VISIBLE);
                    mImage4.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 5) {
                    mImage5.setVisibility(ImageView.VISIBLE);
                    mImage5.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 6) {
                    mImage6.setVisibility(ImageView.VISIBLE);
                    mImage6.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 7) {
                    mImage7.setVisibility(ImageView.VISIBLE);
                    mImage7.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 8) {
                    mImage8.setVisibility(ImageView.VISIBLE);
                    mImage8.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                } else if (mFile.size() == 9) {
                    mImage9.setVisibility(ImageView.VISIBLE);
                    mImage9.setImageBitmap(BitmapFactory.decodeFile(FileManager.getPath(getApplicationContext(), type, uri)));
                }
            } else if (fileType == Template.Code.CAMERA_VIDEO_CODE) {
                mVideo.setVisibility(VideoView.VISIBLE);
                mVideo.setVideoPath(FileManager.getPath(getApplicationContext(), type, uri));
                mController.setVisibility(ImageView.VISIBLE);
                mController.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mVideo.isPlaying()) {
                            mController.setImageResource(R.drawable.ic_play);
                            mVideo.pause();
                        } else {

                            mController.setImageResource(R.drawable.ic_pause);
                            mVideo.start();
                        }
                    }
                });
                mVideo.start();
            } else if (fileType == Template.Code.AUDIO_CODE) {
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), uri);
                mMediaPlayer.setLooping(true);
                mController.setVisibility(ImageView.VISIBLE);
                mController.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (mMediaPlayer.isPlaying()) {
                            mController.setImageResource(R.drawable.ic_play);
                            mMediaPlayer.pause();
                        } else {

                            mController.setImageResource(R.drawable.ic_pause);
                            mMediaPlayer.start();
                        }
                    }
                });
                mMediaPlayer.start();
            } else {
                mImage.setVisibility(ImageView.VISIBLE);
                mImage.setImageResource(R.drawable.ic_android_green_500_48dp);
            }

        }
    }

    void resetView() {
        mUpload.setVisibility(Button.GONE);
        mImage.setVisibility(ImageView.GONE);
        mVideo.setVisibility(VideoView.GONE);
        mInfo.setVisibility(TextView.GONE);
        mInfo.setText("");
        mResponse.setText("");
        mAdd.setText("選擇相片");
        mProgress.setVisibility(ProgressBar.GONE);
        mController.setVisibility(ImageView.GONE);
        mController.setImageResource(R.drawable.ic_pause);
        if (mVideo.isPlaying())
            mVideo.pause();
        if (mMediaPlayer != null && mMediaPlayer.isPlaying())
            mMediaPlayer.pause();
    }

    void setResponse(Object response, VolleyError error) {
        if (response == null) {
            mResponse.setText("Error\n" + error);
        } else {
            if (StringParser.getCode(response.toString()).equals(Template.Query.VALUE_CODE_SUCCESS))
                mResponse.setText("Success\n" + StringParser.getMessage(response.toString()));
            else
                mResponse.setText("Error\n" + StringParser.getMessage(response.toString()));
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {



        // If request is cancelled, the result arrays are empty.
        if(requestCode==10)
        {

            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/* video/*");
                startActivityForResult(intent, Template.Code.FILE_MANAGER_CODE);

            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }else  {


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == Template.Code.FILE_MANAGER_CODE) {
                setFile(requestCode, data.getData());
                setView(requestCode, data.getData());
            } else {
                setFile(requestCode, mOutputUri);
                setView(requestCode, mOutputUri);
            }

        } else {
            resetView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dicuss_content_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();

        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
