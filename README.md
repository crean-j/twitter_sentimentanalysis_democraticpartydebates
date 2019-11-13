# final-project-twitterdemocraticpartydebates
final-project-twitterdemocraticpartydebates created by GitHub Classroom
591 Final Project Design

## Team members 

Joanne Crean [ [creanj@seas.upenn.edu](mailto:creanj@seas.upenn.edu) ]

Juan Goleniowski [ [juangole@seas.upenn.edu](mailto:juangole@seas.upenn.edu) ]

Federica Pelzel [ [fpelzel@seas.upenn.edu](mailto:fpelzel@seas.upenn.edu) ]

## Project Idea
Analyze tweets mentioning top democratic primary candidates and analyze sentiment. We plan to analyze tweets around a given event, initially the 5th Democratic debate of November 20th.


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


## Flow

1. User input
    1. Ask user to select candidate from list, can select more than one (can we predict this?)
    2. Ask user to enter event name (label)
    3. Ask user for date of event
2. Query for tweets sent from 7 days before event to event and from event to 7 days after event that mention any of the selected candidates
3. Read and parse response
4. Store variables in tweet object
    1. Date of tweet
    2. Candidate mentioned
    3. Tweet text
    4. User who created tweet
    5. Number of followers
    6. Location of user
    7. Retweet count
    8. //Influencer weight (num of followers and number of retweets that the tweet had)
5. Pre-process tweets, e.g. remove urls, hashtags, repeated letters
6. Add pre-processed tweet to tweet object
7. Get sentiment analysis for tweet
    1. Prepare tweet for analysis, e.g. tokenise, lemmatise etc.
    2. Get sentiment score for tweet
    3. Adjectives can these be stored separately?
8. Add sentiment score etc. to tweet object
9. Add tweet object to an ArrayList
10. Analyser uses ArrayList of tweets to answer questions, e.g. creates relevant hash maps etc.
11. Output results, e.g. console, visualisation - runner would create a text file

## CRC

### Planned classes

1. Tweet (JG)
2. TwitterSearch (JG)
3. InfluenceScore (JG)
4. TweetProcessor (JC)
5. NLPAnalyser (JC)
6. DataAnalysis (FP)
7. UserInteraction (JG)
8. Runner - gets user input, answer writer (FP)

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
        <tr>
             <td colspan="2" >
          <h3>Notes:</h3>
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
  <li>calculationOfTheInfluenceScor</li>
  </ul>
  </td>
    </tr>
    <tr>
              <td colspan="2" >
           <h3>Notes:</h3>
              </td>
             </tr> </table>

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
  <tr>
            <td colspan="2" >
         <h3>Notes:</h3>
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
<li>Cleans a tweet for analysis by trimming whitespace and converting to lowercase</li>
<li>Removes noise from a tweet: html mark-up, non-ascii characters</li>
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
<li>cleanText</li>
<li>removeNoise</li>
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
   <tr>
     <td colspan="2" >
  <h3>Notes:</h3>
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
 
<li>Overall sentiment score (to be defined) 
 
<li>Overall positive-negative split
 
<li>Top positive words with number of times seen
 
<li>Top negative words with number of times seen
 
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


<li>Can we store in tweet the positive words and negative words (as ArrayLists?) individually from the body of the tweet? Ideally converted to all lowercase.</li>
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


   </td>
  </tr>
</table>
 
## Running the program in Eclipse
1. Install plugin: m2eclipse http://roufid.com/how-to-install-maven-on-eclipse-ide/
2. Import project 
3. Convert project to Maven project (right-click on project name -> click on configure -> click on convert to Maven project)


## Questions


1. Will the program let a user interact with it, or will we output static reports, e.g. analysis of tweets before, during and post primary?
    1. User input?
        1. Date of event, candidate
2. What do we do with:
    2. Tweets that mention two candidates.
        2. Disqualify? Will decide later on
    3. Re-tweets.
        4. Disqualify? No
        5. Endorsements - adds a vote for the candidate
        6. Influencers - more followers 
    4. Cases where there are big differences in the number of tweets mentioning a candidate, how do we compare? 
        7. Need to do a weighted analysis
        8. Is there a minimum number of tweets we should use: ‘[Sentiment analysis](https://monkeylearn.com/sentiment-analysis/) needs at least 500 examples per tag (sentiment) to produce good results.’
3. Should we take a candidates number of followers into account? Could create a candidate class and capture number of followers pre and post debate from Twitter and then report on this as part of the analysis.
4. Should we pass around tweet objects in an array list or a csv file
5. What is required as an output, is a static visualisation enough? How will it be graded?


## Notes on sentiment analysis

**Input: Pre-processed String (Tweet)**

**Output: Positive/Negative score (int or String)**

### Options

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

