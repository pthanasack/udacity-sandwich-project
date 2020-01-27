package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }


        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    //added Sandwich as input to populateUI to pass data
    private void populateUI(Sandwich sandwich) {

        //populate otherNames textView
        TextView tvAlsoKnownAs = findViewById(R.id.also_known_tv);
        for (int i = 0; i < sandwich.getAlsoKnownAs().size(); i++) {
            tvAlsoKnownAs.append(sandwich.getAlsoKnownAs().get(i).toString() + "\n");
        }
        //populate Ingredients textview
        TextView tvIngredients = findViewById(R.id.ingredients_tv);
        for (int i = 0; i < sandwich.getIngredients().size(); i++) {
            tvIngredients.append(sandwich.getIngredients().get(i).toString() + "\n");
        }

        //populate Place of Origin textView
        TextView tvOrigin = findViewById(R.id.origin_tv);
        tvOrigin.setText(sandwich.getPlaceOfOrigin());

        //populate description textView
        TextView tvDescription = findViewById(R.id.description_tv);
        tvDescription.setText(sandwich.getDescription());

    }
}
