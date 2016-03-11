In order to run this program please do the following.

1. Download the source from github: 
	https://github.com/MichaelStergianis/csci2020u.git

2. Navigate to the root directory of the assignment: 
	cd assignments/assignment01/

3. Execute the command
	gradle run

4. Select your data, maintaining the proper directory structure. We have provided data for you if you do not have your own.

5. View the results


Our Proposed optimizations for part to are as follows.
1. Implement 3-grams. We believe that looking at groups of words would give more
	 context to our system. For instance, one could receive an email from a friend
	 pertaning to their trip to nigeria and recently seeing a Prince concert. 
	 It is unlikely the phrase "Prince of Nigeria" would appear in the message, but
	 the probability would be raised based on our model.
2. Implement weighting for increased number of words in caps. Spam will often
	 try to get your attention by using words in capitals. While wanted messages
	 are not as likely to have long strings in capitals. This can be used to 
	 modify probability weightings for files.
