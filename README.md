# Job Application Tracker

A beginner-friendly Java command-line application for recording and managing job applications.

## Features

- Add an application with company, role, location, status, and date
- View all saved applications in a readable table
- Update an application's status
- Delete an application
- Save data locally so it remains after the program closes

## Tech

- Java 17
- Object-oriented programming
- File handling with Java serialization
- Console-based user interface

## Run locally

1. Install Java 17 or later.
2. From this folder, compile the files:

   ```
   javac -d out src/com/anmol/jobtracker/*.java
   ```

3. Run the app:

   ```
   java -cp out com.anmol.jobtracker.JobApplicationTracker
   ```

The app creates `applications.dat` in the folder where it is run. This file contains your saved applications.

## Future improvements

- Add a Spring Boot REST API
- Store data in MySQL instead of a local file
- Add authentication and a web interface
