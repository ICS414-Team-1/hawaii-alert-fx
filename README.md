# hawaii-alert-fx
Implementation of Hawaii Alert System using JavaFX

## Installation
Download or clone this repository to your computer, and put all the files are in the same directory.

## Run
A makefile has been provided.
Before you run, to get the best result. Please remove all the class files (if exist)
```
make clean
```
or
```
rm -rf *.class
```
Then to run the app, all you need to do is compile, and run with the java command line:
```
make
make run
```
## Usage
You will need authentication to run this application. Please use the following credentials:
```
user: jonathan
pass: 123456
```

In `hawaii-alert-fx/config/islandSMS.json`, update fields to your # number and carrier.
This will allow sending to your phone in the usage of the app. Unfortunately, T-Mobile is not compatible with this SMS feature.

In `hawaii-alert-fx/config/islandEmails.json`, update fields to your email address.
This will allow sending to your email in the usage of the app.
