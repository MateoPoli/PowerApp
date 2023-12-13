<p align="center">
  <img src="https://i.imgur.com/J632xR0.png" width="100" height="100">
</p>

## Prototype of an Android application employing computer vision and artificial intelligence techniques based on human pose detection for quantifying speed in the concentric phase of a squat

### Abstract
In this project, a prototype Android application was designed that uses computer vision and artificial intelligence techniques to detect human pose and measure velocity during the concentric phase of the squat. The methodology included a review of the state of the art, a comparative study to select tools (MediaPipe was chosen for its features), the development of the application and experimentation in a gym with a test subject. The results of an analog speed measurement device (Encoder) were compared with those of the prototype, obtaining a Mean Squared Error for speed of 0.34295 demonstrating promising speed calculation results achieved by the prototype, implemented through artificial intelligence techniques and deep learning.

![PowerApp](https://github.com/MateoPoli/PowerApp/assets/70857130/d14a2bb9-2d62-4f91-bcc4-db664b6dc819)

### User Pineline
The user enters the application prototype and, through an intuitive interface, has the option to input the reference measurement (measurement of the femur) for camera calibration and subsequently initiate the pose analysis. The application activates the device's camera, allowing the user to adjust model parameters through an expandable menu. In this interface, frames are captured through video streaming. Applying computer vision and artificial intelligence techniques, the application detects human pose in each frame, analyzing joints and displaying the skeletal structure on the camera interface. When the user finishes the detection, the detected poses throughout the video are processed, and the results are presented in a new interface through a responsive table.

### Overview
Human pose estimation from video plays a critical role in various applications such as quantifying physical exercises, sign language recognition, and full-body gesture control. For example, it can form the basis for yoga, dance, and fitness applications. It can also enable the overlay of digital content and information on top of the physical world in augmented reality.

### ML Pipeline
The solution utilizes a two-step detector-tracker ML pipeline. Using a detector, the pipeline first locates the person/pose region-of-interest (ROI) within the frame. The tracker subsequently predicts the pose landmarks and segmentation mask within the ROI using the ROI-cropped frame as input. Note that for video use cases the detector is invoked only as needed, i.e., for the very first frame and when the tracker could no longer identify body pose presence in the previous frame. For other frames the pipeline simply derives the ROI from the previous frame’s pose landmarks.

<p align="center">
  <img src="https://1.bp.blogspot.com/-J66lTDBjlgw/XzVwzgeQJ7I/AAAAAAAAGYM/WBIhbOqzi4ICUswEOHv8r7ItJIOJgL9iwCLcBGAsYHQ/s411/image11.jpg" width="450" height="200">
  <img src="https://1.bp.blogspot.com/-XxKesnBALGM/XzVxSKZNWZI/AAAAAAAAGYc/WOt31icjp_YyjMxz06RSEwTi9K3qviFxwCLcBGAsYHQ/s550/image9.jpg" width="450" height="200">
</p>

## Build the demo using Android Studio

### Prerequisites

*   The **[Android Studio](https://developer.android.com/studio/index.html)** IDE. This sample has been tested on Android Studio Dolphin.

*   A physical Android device with a minimum OS version of SDK 24 (Android 7.0 -
    Nougat) with developer mode enabled. The process of enabling developer mode
    may vary by device.

### Building

*   Open Android Studio. From the Welcome screen, select Open an existing
    Android Studio project.

*   From the Open File or Project window that appears, navigate to and select
    the mediapipe/examples/pose_landmarker/android directory. Click OK. You may
    be asked if you trust the project. Select Trust.

*   If it asks you to do a Gradle Sync, click OK.

*   With your Android device connected to your computer and developer mode
    enabled, click on the green Run arrow in Android Studio.

### Models used

Downloading, extraction, and placing the models into the *assets* folder is
managed automatically by the **download.gradle** file.
