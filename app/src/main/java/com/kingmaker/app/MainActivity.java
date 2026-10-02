package com.kingmaker.app;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.kingmaker.app.databinding.ActivityMainBinding;

/** Entry point for the personal, online-first decision workspace. */
public final class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.captureButton.setOnClickListener(view -> createDraft());
        binding.continueButton.setOnClickListener(view -> advanceFraming());
    }

    private void createDraft() {
        String capture = binding.captureInput.getText().toString().trim();
        if (capture.isEmpty()) {
            binding.captureInput.setError("Capture the decision in your own words first.");
            binding.captureInput.requestFocus();
            return;
        }
        // This local feedback intentionally creates no authoritative decision. A repository will
        // submit an idempotent draft command when the server integration is added.
        binding.captureInput.setText("");
        Toast.makeText(this, "Draft captured locally — ready to interpret", Toast.LENGTH_LONG).show();
    }

    private void advanceFraming() {
        binding.stage.setText("CONTEXT · revision 03");
        binding.progress.setProgress(76);
        binding.progressText.setText("4 of 5 consequential questions answered");
        binding.continueButton.setText("Answer final question");
        Toast.makeText(this, "One consequential question remains", Toast.LENGTH_SHORT).show();
    }
}
