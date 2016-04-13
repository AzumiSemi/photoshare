package com.github.shawn.photo_share;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.github.shawn.photo_share.util.Util;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  Uri fileUri;
    private Uri editedFileUri;

    public static final int REQUEST_CODE_CAMERA = 10;
    public static final int REQUEST_CODE_GALLERY = 11;
    public static final int REQEST_CODE_GALLERY_NOTEDITOR = 12;
    public static final int REQUEST_CODE_EDITOR = 13;
    public static final int REQEST_CODE_SHARE = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(this);

        Button gallery = (Button) findViewById(R.id.gallery);
        gallery.setOnClickListener(this);

        Button share = (Button) findViewById(R.id.share);
        share.setOnClickListener(this);
    }

    @Override
        public void onClick(View v) {
            if (v.getId() == R.id.camera) {
            //Toast.makeText(MainActivity.this, "カメラ", Toast.LENGTH_SHORT).show();
                launchCamera();
                //カメラ呼び出し
            } else if (v.getId() == R.id.gallery) {
            //Toast.makeText(MainActivity.this, "ギャラリー", Toast.LENGTH_SHORT).show();
                launchGallery(REQUEST_CODE_GALLERY);
            } else {
             //Toast.makeText(MainActivity.this, "シェア", Toast.LENGTH_SHORT).show();
            launchGallery(REQEST_CODE_GALLERY_NOTEDITOR);
         }
    }
        private void launchCamera() {
            Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            fileUri = Util.getOutputMediaFileUri(Util.MEDIA_TYPE_IMAGE);
            cameraintent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(cameraintent, REQUEST_CODE_CAMERA);
         }
        private void launchGallery(int requestCode){
        Intent galleryintent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryintent, requestCode);
        }
        private void lauchEditor(Uri uri){
            Intent imageEditorIntent = new AdobeImageIntent.Builder(this)
                .setData(uri)
                .build();
            startActivityForResult(imageEditorIntent, REQUEST_CODE_EDITOR);
        }
        private void shareimage(Uri uri){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
            shareIntent.setType("image/ipeg");
            startActivityForResult(Intent.createChooser(shareIntent, getString(R.string.app_share)), REQEST_CODE_SHARE);
        }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            if (requestCode == REQEST_CODE_SHARE) {
                Toast.makeText(MainActivity.this, "失敗しました", Toast.LENGTH_SHORT).show();
        }
        return;
        //shareミスの際の表示コード
    }
    switch(requestCode){
        case REQUEST_CODE_CAMERA:
        /* if (data != null){
            Bundle bundle = data.getExtras();
            if(bundle.get("data") == null){
            return;
            image.setImageBitmap((Bitmap) bundle.get("data"));
              }
            break;  */
             Log.d("Trunk", "fileUri:" + fileUri);
            lauchEditor(fileUri);
            break;
        //cameraタッチの際の表示コード

        case REQUEST_CODE_GALLERY:
            fileUri = data.getData();
                Log.d("TRUNK", "fileUri" + fileUri);
                lauchEditor(fileUri);
                break;
            //Editorありのgallery→share

        case REQEST_CODE_GALLERY_NOTEDITOR:
            fileUri = data.getData();
            Log.d("TRUNK", "fileUri" + fileUri);
            shareimage(fileUri);
            break;
        //Editorなしのgallery→share

        case REQUEST_CODE_EDITOR:
            editedFileUri = data.getData();
            Log.d("TRUNK", "editedFileUri" + editedFileUri);
            //image.setImageURI(editedFileUri);
            shareimage(editedFileUri);
            break;
            //Editorのコード

        case REQEST_CODE_SHARE:
            Toast.makeText(MainActivity.this, "シェアしました", Toast.LENGTH_SHORT).show();
             break;
            //shareをタッチした際の表示コード
        }
    }
}