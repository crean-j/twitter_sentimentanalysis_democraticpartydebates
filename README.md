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
*   **Nov 2nd Meeting: **Design planning
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
    a. Date of tweet
    b. Candidate mentioned
    c. Tweet text
    d. User who created tweet
    e. Number of followers
    f. Location of user
    g. Retweet count
    h. //Influencer weight (num of followers and number of retweets that the tweet had)
5. ? Store variables in the candidate object - May not be possible
    a. Candidate
    b. Number of followers 7 days before event
    c. Number of followers on day of event
    d. Number of followers 7 days after event
6. Pre-process tweets, e.g. remove urls, hashtags, repeated letters
7. Add pre-processed tweet to tweet object
8. Get sentiment analysis for tweet
    a. Prepare tweet for analysis, e.g. tokenise, lemmatise etc.
    b. Get sentiment score for tweet
    c. Adjectives can these be stored separately?
9. Add sentiment score etc. to tweet object
10. Add tweet object to an ArrayList
11. Analyser uses ArrayList of tweets to answer questions, e.g. creates relevant hash maps etc.
12. Output results, e.g. console, visualisation - runner would create a text file
    a. Average pos and neg sentiment/day over time for each candidate
    b. Number of pos and neg sentiment tweets/day over time for each candidate
    c. Number of followers on 7 days before, on day of event, 7 days after event for each candidate


## CRC


### Possible classes



1. Tweet (JG)
2. ? Candidate (All)
3. TwitterSearch (JG)
4. TweetProcessor (JC)
5. SentimentAnalyser (JC)
6. ? CandidateAnalyser (FP)
7. TweetAnalyser (FP)
8. Runner - gets user input, answer writer (ALL)


### Class: Tweet


#### Responsibilities



*   Has date of the tweet
*   Has candidate in the text
*   Has meta data like user location
*   Has weight (num of followers, num of retweets)
*   Has sentiment score
*   (Has adjectives array?)


#### Collaborators

*   TweetReader
*   UserInteraction
*   SentimentAnalyser


### Class: Candidate


#### Responsibilities



*   Has name
*   Has number of followers on x date before debate
*   Has number of followers on date of debate
*   Has number of followers on x date after debate
*   Air time during debate


#### Collaborators



*   TweetReader
*   CandidateAnalyser


### Class: SentimentAnalyser 


### Responsibilities



*   Prepares pre-processed tweet for analysis
*   Gets a sentiment score


### Collaborators



*   Tweet
*   TweetReader

<table>
  <tr>
   <td colspan="2" >

<h2><strong>Class: </strong>DataAnalysis.java</h2>


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
 
<li>Most retweeted tweets
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

<li>Int numTweets()

<li>Int sentimentScore()

<li>HashMap(String, Integer) positiveWordCount ()

<li>HashMap(String, Integer) negativeWordCount ()

<li>HashMap(String, Integer) tweetsByState()

<li>Int followersBefore<em>(date?)</em>

<li>Int followersAfter(date?)
</li>
</ul>
   </td>
  </tr>
  <tr>
   <td colspan="2" >
<h3>Notes/questions:</h3>

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
      <h2>Class:Runner.java</h2>
   </td>
  </tr>
  <tr>
   <td>
      <h3>Responsibilities:</h3>
<ul>

<li>Takes in user input from the user with keyword to analyze<em> (can be a word, username, or hashtag? Can it take in multiple?)</em>

<li>Takes input from the user on a date to analyze around 

<li>Executes methods in Data Analysis and prints out results to console in a user-friendly way

<li>Print out file with analyisis results
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



## Questions



1. Will the program let a user interact with it, or will we output static reports, e.g. analysis of tweets before, during and post primary?
    1. User input?
        1. Date of event, candidate
2. What do we do with:
    2. Tweets that mention two candidates.
        2. Disqualify?
        3. Will decide later on
    3. Re-tweets.
        4. Disqualify?
        5. Endorsements - adds a vote for the candidate
        6. Influencers - more followers 
    4. Cases where there are big differences in the number of tweets mentioning a candidate, how do we compare? 
        7. Need to do a weighted analysis
        8. Is there a minimum number of tweets we should use: ‘[Sentiment analysis](https://monkeylearn.com/sentiment-analysis/) needs at least 500 examples per tag (sentiment) to produce good results.’
        9. Look at the top 4 or 5 candidates
3. Should we take a candidates number of followers into account? Could create a candidate class and capture number of followers pre and post debate from Twitter and then report on this as part of the analysis.
4. Should we pass around tweet objects in an array list or a csv file
5. What is required as an output, is a static visualisation enough? How will it be graded?


## Notes on sentiment analysis

**Input: Pre-processed String (Tweet)**

**Output: Positive/Negative score (int or String)**



<p id="gdcalert1" ><span style="color: red; font-weight: bold">>>>>>  gd2md-html alert: inline image link here (to images/591-Final0.png). Store image on your image server and adjust path/filename if necessary. </span><br>(<a href="#">Back to top</a>)(<a href="#gdcalert2">Next alert</a>)<br><span style="color: red; font-weight: bold">>>>>> </span></p>


![alt_text](images/591-Final0.png "image_tooltip")



### Options

1.SentiWordNet



1. This paper gives a good summary of the steps involved: [https://pdfs.semanticscholar.org/2124/bba341a61f0b6cb6141888b789f20b88844b.pdf](https://pdfs.semanticscholar.org/2124/bba341a61f0b6cb6141888b789f20b88844b.pdf)
2. Would need to import a Java library to prepare the tweets for analysis
3. Preparation needed:
    1. Tokenisation: break down the String into words
    2. Stemming : cut words down to their roots
    3. Lemmatizing: a technique that transforms the structural form of a word to the base or dictionary form of word by filtering the affixation or by changing the vowel from the word
    4. POS: Part-of-speech tagging refers to the process of assigning a grammatical category, such as noun, verb, etc. to the tokens that have been detected.
4. Read in SentiWordNet txt file
5. Lookup words in the file to get score

2.Google API



1. [https://cloud.google.com/natural-language/docs/sentiment-tutorial](https://cloud.google.com/natural-language/docs/sentiment-tutorial)
2. [https://medium.com/@cmcorrales3/natural-language-processing-and-tweet-sentiment-analysis-fa1edbb5ddd5](https://medium.com/@cmcorrales3/natural-language-processing-and-tweet-sentiment-analysis-fa1edbb5ddd5)
3. [https://towardsdatascience.com/tweet-analytics-using-nlp-f83b9f7f7349](https://towardsdatascience.com/tweet-analytics-using-nlp-f83b9f7f7349)
4. Feed in the string
5. Methods to get back the score and magnitude

This seems too easy so far, I’m still looking into it. 

3. Stanford API



1. [https://blog.openshift.com/day-20-stanford-corenlp-performing-sentiment-analysis-of-twitter-using-java/](https://blog.openshift.com/day-20-stanford-corenlp-performing-sentiment-analysis-of-twitter-using-java/)
2. Have methods to allow strings to be prepped for analysis
3. Method to get sentiment score
4. Still looking into what they use to get the sentiment score

Training database

[https://www.kaggle.com/crowdflower/first-gop-debate-twitter-sentiment](https://www.kaggle.com/crowdflower/first-gop-debate-twitter-sentiment)

Other resources;

[https://towardsdatascience.com/sentiment-analysis-for-text-with-deep-learning-2f0a0c6472b5](https://towardsdatascience.com/sentiment-analysis-for-text-with-deep-learning-2f0a0c6472b5)

