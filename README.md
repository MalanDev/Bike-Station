Bike Station App
=============================

This is a Bike Station Android App that uses the BikeApi [https://www.poznan.pl/]
to search available bikes and find location where available bikes.


## Building the Bike Station App

First, clone the repo:

`git clone https://github.com/MalanDev/Bike-Station.git`

Building the app then depends on your build tools.

### Android Studio

(These instructions were tested with Android Studio version 
Arctic Fox | 2020.3.1	3.1-7.0 
Bumblebee | 2021.1.1	3.2-7.1
Chipmunk | 2021.2.1	3.2-7.2)

* Open Android Studio and select `File->Open...` or from the Android Launcher select `Import project (Eclipse ADT, Gradle, etc.)` and navigate to the root directory of your project.
* Select the directory or drill in and select the file `build.gradle` in the cloned repo.
* Click 'OK' to open the the project in Android Studio.
* A Gradle sync should start, but you can force a sync and build the 'app' module as needed.

### Gradle (command line)

* Build the APK: `./gradlew build`

## Running the news App

Connect an Android device to your development machine.

### Android Studio

* Select `Run -> Run 'app'` (or `Debug 'app'`) from the menu bar
* Select the device you wish to run the app on and click 'OK'

### Gradle

* Install the debug APK on your device `./gradlew installDebug`
