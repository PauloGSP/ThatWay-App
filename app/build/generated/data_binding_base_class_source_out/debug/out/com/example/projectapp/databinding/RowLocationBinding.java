// Generated by view binder compiler. Do not edit!
package com.example.projectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.projectapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RowLocationBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final ConstraintLayout constraintLayout2;

  @NonNull
  public final ConstraintLayout contraintlayoutbreakpoint;

  @NonNull
  public final ImageButton deleteBreakpoint;

  @NonNull
  public final AutoCompleteTextView locationAutoComplete;

  @NonNull
  public final TextView textviewbreakpoint;

  private RowLocationBinding(@NonNull LinearLayout rootView,
      @NonNull ConstraintLayout constraintLayout2,
      @NonNull ConstraintLayout contraintlayoutbreakpoint, @NonNull ImageButton deleteBreakpoint,
      @NonNull AutoCompleteTextView locationAutoComplete, @NonNull TextView textviewbreakpoint) {
    this.rootView = rootView;
    this.constraintLayout2 = constraintLayout2;
    this.contraintlayoutbreakpoint = contraintlayoutbreakpoint;
    this.deleteBreakpoint = deleteBreakpoint;
    this.locationAutoComplete = locationAutoComplete;
    this.textviewbreakpoint = textviewbreakpoint;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RowLocationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RowLocationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.row_location, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RowLocationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.constraintLayout2;
      ConstraintLayout constraintLayout2 = rootView.findViewById(id);
      if (constraintLayout2 == null) {
        break missingId;
      }

      id = R.id.contraintlayoutbreakpoint;
      ConstraintLayout contraintlayoutbreakpoint = rootView.findViewById(id);
      if (contraintlayoutbreakpoint == null) {
        break missingId;
      }

      id = R.id.deleteBreakpoint;
      ImageButton deleteBreakpoint = rootView.findViewById(id);
      if (deleteBreakpoint == null) {
        break missingId;
      }

      id = R.id.locationAutoComplete;
      AutoCompleteTextView locationAutoComplete = rootView.findViewById(id);
      if (locationAutoComplete == null) {
        break missingId;
      }

      id = R.id.textviewbreakpoint;
      TextView textviewbreakpoint = rootView.findViewById(id);
      if (textviewbreakpoint == null) {
        break missingId;
      }

      return new RowLocationBinding((LinearLayout) rootView, constraintLayout2,
          contraintlayoutbreakpoint, deleteBreakpoint, locationAutoComplete, textviewbreakpoint);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
