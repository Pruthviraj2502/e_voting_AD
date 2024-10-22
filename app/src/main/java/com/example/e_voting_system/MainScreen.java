package com.example.e_voting_system;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainScreen extends AppCompatActivity {

    private RadioGroup candidateGroup;
    private Button voteButton, resultButton;
    private int[] votes = {0, 0, 0}; // To count votes for each candidate
    private boolean hasVoted = false; // To track if the user has voted

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        candidateGroup = findViewById(R.id.candidate_group);
        voteButton = findViewById(R.id.vote_button);
        resultButton = findViewById(R.id.result_button);

        // Hide the result button initially
        resultButton.setVisibility(View.GONE);

        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = candidateGroup.getCheckedRadioButtonId();
                if (selectedId != -1 && !hasVoted) {
                    handleVote(selectedId);
                    resultButton.setVisibility(View.VISIBLE); // Show result button after voting
                    hasVoted = true; // Mark the user as having voted
                } else if (hasVoted) {
                    Toast.makeText(MainScreen.this, "You have already voted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainScreen.this, "Please select a candidate", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
            }
        });
    }

    // Handle the voting logic using if-else
    private void handleVote(int selectedId) {
        if (selectedId == R.id.candidate_1) {
            votes[0]++;
            Toast.makeText(this, "You voted for Candidate 1", Toast.LENGTH_SHORT).show();
        } else if (selectedId == R.id.candidate_2) {
            votes[1]++;
            Toast.makeText(this, "You voted for Candidate 2", Toast.LENGTH_SHORT).show();
        } else if (selectedId == R.id.candidate_3) {
            votes[2]++;
            Toast.makeText(this, "You voted for Candidate 3", Toast.LENGTH_SHORT).show();
        }
    }

    // Display the voting results
    private void showResults() {
        String resultMessage = "Results:\n" +
                "Candidate 1: " + votes[0] + " votes\n" +
                "Candidate 2: " + votes[1] + " votes\n" +
                "Candidate 3: " + votes[2] + " votes";
        Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();
    }
}