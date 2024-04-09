// Copyright 2013 The Flutter Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package io.flutter.plugins.camera.media;

import android.media.CamcorderProfile;
import android.media.EncoderProfiles;
import android.media.MediaRecorder;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import java.io.IOException;

public class MediaRecorderBuilder {
  @SuppressWarnings("deprecation")
  static class MediaRecorderFactory {
    MediaRecorder makeMediaRecorder() {
      return new MediaRecorder();
    }
  }

  private final String outputFilePath;
//  private final CamcorderProfile camcorderProfile;
//  private final EncoderProfiles encoderProfiles;
  private final MediaRecorderFactory recorderFactory;

  private boolean enableAudio;
  private int mediaOrientation;

  public MediaRecorderBuilder(
//      @NonNull CamcorderProfile camcorderProfile,
       @NonNull String outputFilePath) {
    this(
//      camcorderProfile,
     outputFilePath, new MediaRecorderFactory());
  }

//  public MediaRecorderBuilder(
////      @NonNull EncoderProfiles encoderProfiles,
//      @NonNull String outputFilePath) {
//    this(
//            encoderProfiles,
//            outputFilePath, new MediaRecorderFactory());
//  }

//  MediaRecorderBuilder(
////      @NonNull CamcorderProfile camcorderProfile,
//      @NonNull String outputFilePath,
//      MediaRecorderFactory helper) {
//    this.outputFilePath = outputFilePath;
////    this.camcorderProfile = null;
////    this.encoderProfiles = null;
//    this.recorderFactory = helper;
//  }

  MediaRecorderBuilder(
//      @NonNull EncoderProfiles encoderProfiles,
      @NonNull String outputFilePath,
      MediaRecorderFactory helper) {
    this.outputFilePath = outputFilePath;
//    this.encoderProfiles = encoderProfiles;
//    this.camcorderProfile = null;
    this.recorderFactory = helper;
  }

  public MediaRecorderBuilder setEnableAudio(boolean enableAudio) {
    this.enableAudio = enableAudio;
    return this;
  }

  public MediaRecorderBuilder setMediaOrientation(int orientation) {
    this.mediaOrientation = orientation;
    return this;
  }

  public MediaRecorder build() throws IOException, NullPointerException, IndexOutOfBoundsException {
    MediaRecorder mediaRecorder = recorderFactory.makeMediaRecorder();

    // There's a fixed order that mediaRecorder expects. Only change these functions accordingly.
    // You can find the specifics here: https://developer.android.com/reference/android/media/MediaRecorder.
    if (enableAudio) mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);

    if (Build.VERSION.SDK_INT >= 31) {
//      EncoderProfiles.VideoProfile videoProfile = encoderProfiles.getVideoProfiles().get(0);
//      EncoderProfiles.AudioProfile audioProfile = encoderProfiles.getAudioProfiles().get(0);
      String vvvww =" WIN == " ;
//      vvvww = vvvww + " getRecommendedFileFormat "+ encoderProfiles.getRecommendedFileFormat() ;
//      if (enableAudio) {
//        vvvww = vvvww + " getCodec "+ audioProfile.getCodec();
//        vvvww = vvvww + " getBitrate "+ audioProfile.getBitrate();
//        vvvww = vvvww + " getSampleRate "+ audioProfile.getSampleRate();
//      }
//      vvvww = vvvww + " videoProfile.getCodec "+ videoProfile.getCodec();
//      vvvww = vvvww + " videoProfile.getBitrate "+ videoProfile.getBitrate();
//      vvvww = vvvww + " videoProfile.getFrameRate "+ videoProfile.getFrameRate();
//
//      vvvww = vvvww + " videoProfile.getWidth "+ videoProfile.getWidth();
//      vvvww = vvvww + " videoProfile.getHeight "+ videoProfile.getHeight();


      Log.e("EncoderProfilesWWWW", vvvww);

//      MediaRecorder.OutputFormat.DEFAULT  // encoderProfiles.getRecommendedFileFormat()
      mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
//      if (enableAudio) {
//        // 0 - 7 // 2
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT); //audioProfile.getCodec());
////        mediaRecorder.setAudioEncodingBitRate(audioProfile.getBitrate());
////        mediaRecorder.setAudioSamplingRate(audioProfile.getSampleRate());
//      }
      // 0 - 5 // 3
      mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT); // mediaRecorder.setVideoEncoder(videoProfile.getCodec());
      // ??
//      mediaRecorder.setVideoEncodingBitRate(videoProfile.getBitrate());
      // 0 , 90, ...
//      mediaRecorder.setVideoFrameRate(videoProfile.getFrameRate());
      // me size
      mediaRecorder.setVideoSize(900, 900); // videoProfile.getWidth(), videoProfile.getHeight());
      mediaRecorder.setVideoSize(900, 900);
    } else {
      /// MediaRecorder.OutputFormat.RAW_AMR
      // mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);  //camcorderProfile.fileFormat);
      // if (enableAudio) {
      //   mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);  //camcorderProfile.audioCodec);
      //   mediaRecorder.setAudioEncodingBitRate(16); //camcorderProfile.audioBitRate
      //   mediaRecorder.setAudioSamplingRate(camcorderProfile.audioSampleRate);
      // }
      // mediaRecorder.setVideoEncoder(camcorderProfile.videoCodec);
      // mediaRecorder.setVideoEncodingBitRate(camcorderProfile.videoBitRate);
      // mediaRecorder.setVideoFrameRate(camcorderProfile.videoFrameRate);
      // mediaRecorder.setVideoSize(
      //     camcorderProfile.videoFrameWidth, camcorderProfile.videoFrameHeight);
    }

    mediaRecorder.setOutputFile(outputFilePath);
    mediaRecorder.setOrientationHint(this.mediaOrientation);

    mediaRecorder.prepare();

    return mediaRecorder;
  }
}
