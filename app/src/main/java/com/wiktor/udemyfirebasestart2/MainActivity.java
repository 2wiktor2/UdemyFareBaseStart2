package com.wiktor.udemyfirebasestart2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Получаем экземпляр базы данных
        db = FirebaseFirestore.getInstance();

/*        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        //Создание объекта
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Viktor");
        user.put("last", "Sidorov");
        user.put("born", 1982);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                    }
                });*/

/*        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Получили данные", Toast.LENGTH_SHORT).show();
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot == null) {
                        return;
                    }
                    for (QueryDocumentSnapshot documentSnapshot : querySnapshot) {
                        Map<String, Object> user = documentSnapshot.getData();
                        Log.i("qwertyu", user.get("first").toString());
                        Log.i("qwertyu", user.get("last").toString());
                        Log.i("qwertyu", user.get("born").toString());
                    }
                } else {
                    Toast.makeText(MainActivity.this, " Не получили данные " + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        //Прослушивание изменений
        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    for (QueryDocumentSnapshot documentSnapshot : value) {
                        Map<String, Object> user = documentSnapshot.getData();
                        Log.i("qwertyu", user.get("first").toString());
                        Log.i("qwertyu", user.get("last").toString());
                        Log.i("qwertyu", user.get("born").toString());
                    }

                } else {

                    Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}