# final-project-twitterdemocraticpartydebates
final-project-twitterdemocraticpartydebates created by GitHub Classroom
591 Final Project Design

Project landing page: https://upenn-cit599.github.io/final-project-twitterdemocraticpartydebates/

## Team members 

Joanne Crean [ [creanj@seas.upenn.edu](mailto:creanj@seas.upenn.edu) ]

Juan Goleniowski [ [juangole@seas.upenn.edu](mailto:juangole@seas.upenn.edu) ]

Federica Pelzel [ [fpelzel@seas.upenn.edu](mailto:fpelzel@seas.upenn.edu) ]

## Project Idea
Analyze tweets mentioning top democratic primary candidates to get the average sentiment. A static analysis was done to analyze tweets around a given event, the 5th Democratic debate of November 20th.
This was to demonstrate the output of the program. 

The program can be used in real-time to get the sentiment around a candidate on a given date.

## Limitations
* We are using a free Twitter account so: 
  * The Twitter API will only give data for the last seven days. 
  * The Twitter API will only return 100 tweets for a search. 
* Stanford CoreNLP API was trained on movie reviews so may not be as accurate for Tweets. 
* Tweets often have slang, misspellings and emojis that cannot be accurately analyzed.

## Set-up the program
* You will need a Maven plugin in your IDE to build the project. 
  * For Eclipse: 
    * Install the plug in: m2eclipse
    * Convert to maven project
* The Runner class contains the main method for the program. 
* Dependencies:
  * Twitter4j library: need to create and account and store a twitter4j.properties file in the main project folder to run.
  * Standford CoreNLP API: no credentials needed.
  
## Real-time user flow
1. User input
    1. User asked to enter keyword to search for.
2. Query for keyword.
    1. Will get the user up to 18,000 tweets. Analysis starts today and then keeps scanning back over the last 7 days until          it's reached the query limit. 
4. Read response and store to text file.
5. Store variables for each tweet in a tweet object:
    1. Date of tweet
    2. Candidate mentioned
    3. Tweet text
    4. User who created tweet
    5. Number of followers that user had
    6. Location of user
    7. Retweet count
    8. Influence score (weight tweets based on the num of followers and number of retweets that the tweet had)
5. Add tweets to an ArrayList.
6. Pre-process each tweet's text for sentiment analysis: remove urls, hashtags, user mentions.
7. Get sentiment for tweet using Stanford CoreNlP:
    1. Prepare tweet for analysis: tokenise, point-of-speech tagging, split into sentences
    2. Get sentiment score for tweet
    3. Add sentiment score to tweet object
8. Get the adjectives in the tweet, alongside their sentiment score and store in a HashMap. 
    1. Removes commonly used words that don't support the analysis
    2. Add to the tweet object
9. Analyser pulls in ArrayList of tweets to answer questions:
    1. What is the average sentiment of the tweets for this candidate on this date?
    2. What are the most used positive adjectives in tweets mentioning the candidate on this date?
    3. What are the most used negative adjectives in tweets mentioning the candidates on this date?
    4. Which are the 5 states with the highest sentiment score on this date?
    5. Which are the 5 states with the lowest sentiment score on this date?
    6. Total number of tweets about the candidate?
10. Output results: 
    1. Display in console
    2. Text file of tweets

## Static analysis around Nov 20th debate
* The static analysis was done on tweets from Nov 13 - Nov 20th and Nov 22nd - 30th.  
  * Batches of tweets were collected and added to a TweetArchive text file
* Takes in file and parses each row into a Tweet object.  
* ArrayList of tweets formed for each candidate.
* Tweet location is analyzed using natural language sorting and stored into state ArrayLists.
* Analysis is run at the candidate level include state analysis.
* The results of the static analysis can be viewed in two csvs files and a txt report:
 * DataByStates.csv
 * DataByCandidate.csv
 * report.txt
* The following questions were answered for each candidate:
   * Total number of tweets
   * Number of tweets with mathced location
   * What is the average sentiment of the tweets for this candidate?
   * What percent of tweets have a positive sentiment, or negative sentiment?
   * What is the median sentiment of the tweets for this candidate?
   * What is the mode sentiment of the tweets for this candidate?
   * What are the most used positive adjectives in tweets mentioning the candidate on this date?
   * What are the most used negative adjectives in tweets mentioning the candidates on this date?
   * Which are the 5 states with the highest sentiment score on this date?
   * Which are the 5 states with the lowest sentiment score on this date?

* Output results:
   * 2 csv files
   * txt report
   * Statics analysis in console
  

## Presentation (5 mins)

* Intro to the project 
* e2e experience of running the program in real time (this person shares screen)
* Talk through the code: Twitter, Sentiment, Analysis
* Show the static debate analysis file 

## Work plan

General

*   Weekly meeting Sat 9am EST
*   Mid-week update in the Slack channel on Weds (what are you working on, what’s going well, any issues)
    *   This could be call if needed (5 or 6pm EST?)
*   Ad-hoc calls as needed

Timeline

*   **Oct 26th Meeting**: Scoping
*   Individuals consider design for their focus areas -> figure out inputs/outputs
*   **Nov 2nd Meeting**:Design planning
*   Individuals refine their design, consider milestones, set-up GitHub, maybe start proof of concept
*   **Nov 9th Meeting**: Design proposal submission, confirm milestones
*   Individuals work on code
*   **Nov 16th Meeting:** Milestone one for individual work
*   Finish individual code
*   **Nov 23rd Meeting:** Milestone two individual work completed, ready for peer review
*   Peer review, start to combine the code so final code is ready and working, test
*   **Nov 30th Meeting:** Milestone three work combined so that it works e2e, work tested
*   Refine
*   **Dec 8th Meeting:** Refinements and final submission
    *   Consider project presentation

## CRC

### Planned classes

1. Tweet 
2. TwitterSearch 
3. InfluenceScore 
4. TweetProcessor
5. NLPAnalyser 
6. DataAnalysis 
7. DemDebate
8. UserInteraction 
9. SaveTweets
10. TweetsByState
11. Runner

<table>
  <tr>
   <td colspan="2">
         <h2>Class: Tweet.java</h2>
      </td>
  </tr>
  <tr>
   <td>
       <h3>Responsibilities:</h3>
<ul>
<li>Has date of the tweet</li>
<li>Has candidate in the text</li>
<li>Has user location</li>
<li>Has num of followers</li>
<li>Has num of retweets</li>
<li>Has raw tweet text</li>
<li>Has processed tweet text</li>
<li>Has array list of adjectives</li>
<li>Has array list of words</li>
<li>Has sentiment score</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>
<ul>
<li>TwitterSearch</li>
<li>UserInteraction</li>
<li>TweetProcessor</li>
<li>NLPAnalyser</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2">
<h3>Methods:</h3>
<ul>
<li>Getters and setters</li>
</ul>
</td>
  </tr>
   <tr>
              <td colspan="2" >
            <h3>Notes:</h3>
               </td>
              </tr>
              </table>
     

<table>
  <tr>
   <td colspan="2" >
         <h2>Class: UserInteraction.java</h2>
      </td>
  </tr>
  <tr>
   <td>
       <h3>Responsibilities:</h3>

<ul>
<li>Asks user for candidate to search</li>
<li>Asks user for a date for the query</li>
<li>Asks user to a d</li>
<li>Validates the entered data</li>
<li>Has candidate name</li>
<li>Has date</li>
<li>Has event name</li>
</ul>
   </td>
      <td>
          <h3>Collaborators:</h3>
   
   <ul>
   <li>TwitterSearch</li>
   </ul>
      </td>
      </tr>
        <tr>
         <td colspan="2">
      <h3>Methods:</h3>
      <ul>
      <li>selectSearchTerm</li>
      <li>parseDate</li>
      <li>validateDate</li>
      <li>Getters and setters</li>
      </ul>
      </td>
        </tr>
        
</table>

<table>
<tr>
   <td colspan="2">
         <h2>Class: InfluenceScore.java</h2>
      </td>
  </tr>
  <tr>
   <td>
         <h3>Responsibilities:</h3>
  <ul>
  <li>Calculates influence score for a tweet</li>
  <li>Has max followers</li>
  <li>Has min followers</li>
  <li>Has max retweets</li>
  <li>Has min retweets</li>
  </ul>
     </td>
     <td>
  <h3>Collaborators:</h3>
  <ul>
  <li>Tweet</li>
  </ul>
     </td>
    </tr>
    <tr>
     <td colspan="2">
  <h3>Methods:</h3>
  <ul>
  <li>calculationOfTheInfluenceScore</li>
  </ul>
  </td>
    </tr>
  </table>

<table>
  <tr>
   <td colspan="2">
         <h2>Class: TwitterSearch.java</h2>
      </td>
  </tr>
  <tr>
   <td>
       <h3>Responsibilities:</h3>
<ul>
<li>Has date of the tweet</li>
<li>Has candidate mentioned</li>
<li>Has to look for tweet as per the criteria in UserInteraction</li>
<li>Creates an ArrayList of tweet objects containing the search term</li>
<li>Gets date 7 days before and after search date</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>
<ul>
<li>Tweet</li>
<li>UserInteraction</li>
<li>Twitter API</li>

</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2">
<h3>Methods:</h3>
<ul>
<li>mainSearch</li>
<li>toDate</li>
<li>sinceDate</li>
</ul>
</td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2">
       <h2>Class: TweetProcessor.java</h2>
    </td>
  </tr>
  <tr>
   <td>
       <h3>Responsibilities:</h3>
<ul>
<li>Removes noise from a tweet: urls, user mentions, hash-tags</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>
<ul>
<li>Tweet</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2">
<h3>Methods:</h3>
<ul>
<li>removeNoise</li>
</ul>
</td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2" >
         <h2>Class: NLPAnalyser.java</h2>
      </td>
  </tr>
  <tr>
   <td>
       <h3>Responsibilities:</h3>
<ul>
<li>Creates a pipeline to annotate some text using NLP annotators</li>
<li>Gets the sentiment score for a tweet</li>
<li>Gets an array list of adjectives in a tweet</li>
<li>Gets all words in a tweet</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>
<ul>
<li>Tweet</li>
<li>Stanford CoreNLP API</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2">
<h3>Methods:</h3>
<ul>
<li>nlpPipeline</li>
<li>getSentimentScore</li>
<li>adjectives</li>
<li>getWords</li>
</ul>
</td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2" >
      <h2>Class: DataAnalysis.java</h2>
   </td>
  </tr>
  <tr>
   <td>
      <h3>Responsibilities:</h3>

<ul>

<li>Takes in object of Tweet class and creates an ArrayList

<li>Uses a Keyword to Analyze the following 
<ul>
 
<li>Number of tweets mentioning the keyword
 
<li>Overall sentiment score (average of scores across tweets) 
 
<li>Overall positive-negative split
 
<li>Top positive adjectives
 
<li>Top negative adjectives
 
<li>Top origin states for tweets containing word
 
<li>If keyword is a username:  
<ul>
  
<li>#followers before date
  
<li>#followers after date
</li>  
</ul>
 
<li>Tweets per day sentiment
 
<li>Most re-tweeted tweets
</li> 
</ul>
</li> 
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>


<ul>

<li>Tweet
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Methods:</h3>


<ul>

<li>Constructor (takes in Tweets and makes ArrayList)

<li>int numTweets()

<li>int sentimentScore()

<li>HashMap(String, Integer) positiveWordCount ()

<li>HashMap(String, Integer) negativeWordCount ()

<li>HashMap(String, Integer) tweetsByState()

<li>int followersBefore<em>(date?)</em>

<li>int followersAfter(date?)</li>
<li>int averageSevenDBefore(String date)</li>
<li>int averageSevenDAfter(String date)</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Notes:</h3>

<ul>
<li>For all method that measure ‘Top’ the number of results displayed should be dynamic</li>
</ul>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2" >
      <h2>Class: TweetsByState.java</h2>
   </td>
  </tr>
  <tr>
   <td>
      <h3>Responsibilities:</h3>
<ul>

<li>

<li>

<li>

<li>
</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>


<ul>

<li>Tweet

<li>
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Methods:</h3>
<ul>
<li> TweetsByState(ArrayList<Tweet> tweets)
</li>
</ul>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2" >
      <h2>Class:SaveTweets.java</h2>
   </td>
  </tr>
  <tr>
   <td>
      <h3>Responsibilities:</h3>
<ul>

<li>

<li>

<li>

<li>
</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>


<ul>

<li>Tweet

<li>
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Methods:</h3>
<ul>
<li> void saveToFile() 
<li> ArrayList<Tweet> loadFile()
<li> Tweet tweetsParse(String row)
</li>
</ul>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2" >
      <h2>Class: DemDebate.java</h2>
   </td>
  </tr>
  <tr>
   <td>
      <h3>Responsibilities:</h3>
<ul>

<li>

<li>

<li>

<li>
</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>
<ul>

<li>Tweet

<li>NLP Analyser
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Methods:</h3>
<ul>

<li>Main
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Notes:</h3>
<ul>
<li>This was the main method for running the static sentiment analysis of the debates.</li>
</ul>

   </td>
  </tr>
</table>

<table>
  <tr>
   <td colspan="2" >
      <h2>Class: Runner.java</h2>
   </td>
  </tr>
  <tr>
   <td>
      <h3>Responsibilities:</h3>
<ul>

<li>Takes in user input from the user with keyword to analyze<em> (can be a word, username, or hashtag? Can it take in multiple?)</em>

<li>Takes input from the user on a date to analyze around 

<li>Executes methods in Data Analysis and prints out results to console in a user-friendly way

<li>Print out file with analysis results
</li>
</ul>
   </td>
   <td>
<h3>Collaborators:</h3>


<ul>

<li>Tweet

<li>DataAnalysis
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Methods:</h3>
<ul>

<li>Main
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Notes:</h3>
<ul>
<li>This was the main method for real-time sentiment analysis of tweets for a candidate on a given day.</li>
</ul>
   </td>
  </tr>
</table>

## Future improvements
* Visual representation of output in real-time.
* Train our own NLP model on Tweets to get more accurate results.
* Use a paid Twitter account to get more data back for analysis.


### References

1. SentiWordNet

1. This paper gives a good summary of the steps involved: [https://pdfs.semanticscholar.org/2124/bba341a61f0b6cb6141888b789f20b88844b.pdf](https://pdfs.semanticscholar.org/2124/bba341a61f0b6cb6141888b789f20b88844b.pdf)
2. Would need to import a Java library to prepare the tweets for analysis
3. Preparation needed:
    1. Tokenisation: break down the String into words
    2. Stemming : cut words down to their roots
    3. Lemmatizing: a technique that transforms the structural form of a word to the base or dictionary form of word by filtering the affixation or by changing the vowel from the word
    4. POS: Part-of-speech tagging refers to the process of assigning a grammatical category, such as noun, verb, etc. to the tokens that have been detected.
4. Read in SentiWordNet txt file
5. Lookup words in the file to get score

2. Google API

1. [https://cloud.google.com/natural-language/docs/sentiment-tutorial](https://cloud.google.com/natural-language/docs/sentiment-tutorial)
2. [https://medium.com/@cmcorrales3/natural-language-processing-and-tweet-sentiment-analysis-fa1edbb5ddd5](https://medium.com/@cmcorrales3/natural-language-processing-and-tweet-sentiment-analysis-fa1edbb5ddd5)
3. [https://towardsdatascience.com/tweet-analytics-using-nlp-f83b9f7f7349](https://towardsdatascience.com/tweet-analytics-using-nlp-f83b9f7f7349)
4. Feed in the string
5. Methods to get back the score and magnitude

This seems too easy so far, I’m still looking into it. 

3. Stanford CoreNLP API

1. [https://blog.openshift.com/day-20-stanford-corenlp-performing-sentiment-analysis-of-twitter-using-java/](https://blog.openshift.com/day-20-stanford-corenlp-performing-sentiment-analysis-of-twitter-using-java/)
2. Have methods to allow strings to be prepped for analysis
3. Method to get sentiment score
4. Sentiment measured 0 -> 4

Training database

[https://www.kaggle.com/crowdflower/first-gop-debate-twitter-sentiment](https://www.kaggle.com/crowdflower/first-gop-debate-twitter-sentiment)

Other resources;

[https://towardsdatascience.com/sentiment-analysis-for-text-with-deep-learning-2f0a0c6472b5](https://towardsdatascience.com/sentiment-analysis-for-text-with-deep-learning-2f0a0c6472b5)

