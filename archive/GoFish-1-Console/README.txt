ADAM NARKUNSKI 300232113

				            GO FISH EXERCISE 1 - CONSOLE
					                    NOTES

	The project is under the included directory, GoFish.
	
	The class that you should start is console.Main (class Main in package console)
	
	Dependency: Google's guava library, guava-14.0.1.jar. Included with the 
	project.

	A game ends not only when there's no more options to create a four, but also
	when there's only one player left.
	One peculiar scenario is when there are two players left, one with 1 card, the 
	other with 3. The second player requests the only card left from the first player,
	completing a four. 
	The game will end with the second player as the only player left (even though he
	has a four in his hand).

	Each card has the same number of serieses as all the other cards. For example, 
	if one card is a member of two serieses, then we assume all other cards are
	members of two serieses.

	XML settings file is provided as an argument to the program. If there is a problem
	with it's format, or if it does not exist, the game will default to getting 
	settings manually from the user.
	The user can NOT supply a path to an xml path, the default
	
	When the user opts for a manual game, he can choose settings, choose player types
	and names, but cards are automatically generated. sorry, but I didn't think it's
	reasonable to enter 28+ cards (each with different names etc) every time you want
	to start a new game.
	
	
	Thanks.
	Adam

