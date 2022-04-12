// Generated by view binder compiler. Do not edit!
package com.example.android.people.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.android.people.R;
import com.example.android.people.ui.chat.ChatEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ChatFragmentBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final LinearLayout chat;

  @NonNull
  public final ChatEditText input;

  @NonNull
  public final ConstraintLayout inputBar;

  @NonNull
  public final RecyclerView messages;

  @NonNull
  public final ImageView photo;

  @NonNull
  public final ImageButton send;

  @NonNull
  public final ImageButton voiceCall;

  private ChatFragmentBinding(@NonNull LinearLayout rootView, @NonNull LinearLayout chat,
      @NonNull ChatEditText input, @NonNull ConstraintLayout inputBar,
      @NonNull RecyclerView messages, @NonNull ImageView photo, @NonNull ImageButton send,
      @NonNull ImageButton voiceCall) {
    this.rootView = rootView;
    this.chat = chat;
    this.input = input;
    this.inputBar = inputBar;
    this.messages = messages;
    this.photo = photo;
    this.send = send;
    this.voiceCall = voiceCall;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ChatFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ChatFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.chat_fragment, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ChatFragmentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      LinearLayout chat = (LinearLayout) rootView;

      id = R.id.input;
      ChatEditText input = ViewBindings.findChildViewById(rootView, id);
      if (input == null) {
        break missingId;
      }

      id = R.id.input_bar;
      ConstraintLayout inputBar = ViewBindings.findChildViewById(rootView, id);
      if (inputBar == null) {
        break missingId;
      }

      id = R.id.messages;
      RecyclerView messages = ViewBindings.findChildViewById(rootView, id);
      if (messages == null) {
        break missingId;
      }

      id = R.id.photo;
      ImageView photo = ViewBindings.findChildViewById(rootView, id);
      if (photo == null) {
        break missingId;
      }

      id = R.id.send;
      ImageButton send = ViewBindings.findChildViewById(rootView, id);
      if (send == null) {
        break missingId;
      }

      id = R.id.voice_call;
      ImageButton voiceCall = ViewBindings.findChildViewById(rootView, id);
      if (voiceCall == null) {
        break missingId;
      }

      return new ChatFragmentBinding((LinearLayout) rootView, chat, input, inputBar, messages,
          photo, send, voiceCall);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}