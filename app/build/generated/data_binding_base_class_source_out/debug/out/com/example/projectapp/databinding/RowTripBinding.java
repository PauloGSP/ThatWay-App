// Generated by view binder compiler. Do not edit!
package com.example.projectapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.projectapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RowTripBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView directions;

  @NonNull
  public final ImageButton iconMoney;

  @NonNull
  public final ImageButton iconTime;

  @NonNull
  public final Button moreInfoBtn;

  @NonNull
  public final TextView noselectedtrip;

  @NonNull
  public final TextView scheduleTimeText;

  @NonNull
  public final TextView tickerPriceText;

  @NonNull
  public final ImageView transportTypeIcon;

  @NonNull
  public final TextView transportTypeText;

  @NonNull
  public final ConstraintLayout tripContainer;

  private RowTripBinding(@NonNull ConstraintLayout rootView, @NonNull TextView directions,
      @NonNull ImageButton iconMoney, @NonNull ImageButton iconTime, @NonNull Button moreInfoBtn,
      @NonNull TextView noselectedtrip, @NonNull TextView scheduleTimeText,
      @NonNull TextView tickerPriceText, @NonNull ImageView transportTypeIcon,
      @NonNull TextView transportTypeText, @NonNull ConstraintLayout tripContainer) {
    this.rootView = rootView;
    this.directions = directions;
    this.iconMoney = iconMoney;
    this.iconTime = iconTime;
    this.moreInfoBtn = moreInfoBtn;
    this.noselectedtrip = noselectedtrip;
    this.scheduleTimeText = scheduleTimeText;
    this.tickerPriceText = tickerPriceText;
    this.transportTypeIcon = transportTypeIcon;
    this.transportTypeText = transportTypeText;
    this.tripContainer = tripContainer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RowTripBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RowTripBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.row_trip, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RowTripBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.directions;
      TextView directions = rootView.findViewById(id);
      if (directions == null) {
        break missingId;
      }

      id = R.id.iconMoney;
      ImageButton iconMoney = rootView.findViewById(id);
      if (iconMoney == null) {
        break missingId;
      }

      id = R.id.iconTime;
      ImageButton iconTime = rootView.findViewById(id);
      if (iconTime == null) {
        break missingId;
      }

      id = R.id.moreInfoBtn;
      Button moreInfoBtn = rootView.findViewById(id);
      if (moreInfoBtn == null) {
        break missingId;
      }

      id = R.id.noselectedtrip;
      TextView noselectedtrip = rootView.findViewById(id);
      if (noselectedtrip == null) {
        break missingId;
      }

      id = R.id.scheduleTimeText;
      TextView scheduleTimeText = rootView.findViewById(id);
      if (scheduleTimeText == null) {
        break missingId;
      }

      id = R.id.tickerPriceText;
      TextView tickerPriceText = rootView.findViewById(id);
      if (tickerPriceText == null) {
        break missingId;
      }

      id = R.id.transportTypeIcon;
      ImageView transportTypeIcon = rootView.findViewById(id);
      if (transportTypeIcon == null) {
        break missingId;
      }

      id = R.id.transportTypeText;
      TextView transportTypeText = rootView.findViewById(id);
      if (transportTypeText == null) {
        break missingId;
      }

      id = R.id.trip_container;
      ConstraintLayout tripContainer = rootView.findViewById(id);
      if (tripContainer == null) {
        break missingId;
      }

      return new RowTripBinding((ConstraintLayout) rootView, directions, iconMoney, iconTime,
          moreInfoBtn, noselectedtrip, scheduleTimeText, tickerPriceText, transportTypeIcon,
          transportTypeText, tripContainer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
