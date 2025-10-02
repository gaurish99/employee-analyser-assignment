▶️ How to Run the Project

Once you've cloned the repo, running Employee Analyzer is simple and straightforward.

✅ 1. CSV File Location

The project includes a sample employee CSV file already placed in:

data/employees.csv


You can modify this file to suit your own data, or replace it with another file at the same location.

✅ 2. Running in IntelliJ IDEA

Open the project in IntelliJ.

Navigate to the main class:
src/main/java/org/swissre/Main.java

Right-click on the Main class → Run 'Main.main()'

If prompted, or if you want to set manually:

Go to Run > Edit Configurations...

In Program Arguments, enter:

data/employees.csv


Click Apply and then Run ✅

ℹ️ The application expects the path to the .csv file to be passed as a command-line argument.

✅ 3. Running in Eclipse

Right-click Main.java → Run As > Java Application

Go to Run > Run Configurations...

Select your launch configuration for Main.

In the Arguments tab → under Program arguments, enter:

data/employees.csv


Click Apply and then Run ✅