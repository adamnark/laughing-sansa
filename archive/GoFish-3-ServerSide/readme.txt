300232113 ADAM NARKUNSKI

							GO FISH EXERCISE 2 - SERVER-SIDE PROGRAMMING
											 NOTES

	The NetBeans project is the included directory, GoFish.
	Dependencies: Google's guava library, guava-14.0.1.jar. It is included in the project
	tree under lib/. 
	
	You should be able to unzip the project, open in Netbeans and hit Run if you have 
	Tomcat integration. 
	
	Known Issues:
	=============
	1. If a user starts a new game, does not finish it and loads a game from XML, and then opts
	for starting the game over with the same settings, the manual game will be started and not 
	the xml game. This is a bug I found on the last day and I havn't had the time to patch it 
	up.
	
	2. Upon each click on a card, an HTTP request is made to the server. This might cause the 
	application	to be less responsive than any normal web application should. sorry. 
	
	3. In the New Game page I havn't implemented an option to remove a player from the players 
	list. The user must reload the page to clear all players.
	
	4. In the New Game page, when adding a player, the form resets the game options (allow
	multiple requests/force show of hand).
	
	5. Sometimes, when trying to start a game, you might get a duplicate card error. Just click
	Start Game again. 

	Assumptions:
	============
	1. Each card should have the same number of series as all the other cards. For example, 
	if one card is a member of two series, then we assume all other cards are
	members of two series.
	
	2. When the user opts for a manual game, he can choose settings, choose player types
	and names, but cards are automatically generated. I didn't think it's
	reasonable to specify 28+ cards (each with different names etc) every time you want
	to start a new game.

	Thanks.
	Adam
