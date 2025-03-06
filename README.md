# SentimentAnalysis

The SentimentAnalysis project is a Java-based application designed to analyze tweets' sentiment. The application fetches data from Twitter, processes it, and analyzes the sentiment of the collected tweets. This project leverages basic natural language processing (NLP) principles and provides an easy-to-understand approach to understanding sentiment in textual data.

Features
Twitter Data Collection: Collects tweets using a Twitter API and stores them for analysis.
Sentiment Analysis: Analyzes whether the sentiment of a tweet is positive, negative, or neutral.
Console Formatting: Uses the ConsoleColour enum to format console output with different colors for better readability.
Modular Design: The project is divided into multiple classes with clearly defined responsibilities, promoting readability and maintainability.

Technologies Used
Java: Core programming language.
Twitter API: For collecting tweet data.
Natural Language Processing (NLP): Basic sentiment analysis methods to classify tweets as positive, negative, or neutral.
ANSI Escape Sequences: For color formatting in the console output.

How to Run the Application
Clone this repository:
git clone https://github.com/yourusername/SentimentAnalysis.git

Compile the Java code: Ensure you have Java installed, then compile the code:
javac -d bin src/ie/atu/sw/*.java

Run the SentimentAnalysis application: After compiling, run the main class (Runner) to start the sentiment analysis process:
java -cp bin ie.atu.sw.Runner

Input/Output:
The program will output the sentiment analysis results of the tweets.
The output will be displayed in different colors based on the sentiment (positive, negative, neutral).

Contributions
Feel free to contribute to this project. Fork it, make improvements, and send pull requests. Contributions are welcome!

License
This project is licensed under the MIT License.
